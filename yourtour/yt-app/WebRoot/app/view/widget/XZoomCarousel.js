Ext.define('YourTour.view.widget.XZoomCarousel', {
    extend: 'Ext.Container',
    xtype: 'xzoomcarousel',
    requires: [
        'Ext.fx.easing.EaseOut',
        'Ext.carousel.Item',
        'Ext.carousel.Indicator',
        'YourTour.view.widget.XTranslatableGroup'
    ],

    config: {
        /**
         * @cfg
         * @inheritdoc
         */
        baseCls: 'x-xzoomcarousel',

        /**
         * @cfg {String} direction
         * The direction of the Carousel, either 'horizontal' or 'vertical'.
         * @accessor
         */
        direction: 'horizontal',

        directionLock: false,

        animation: {
            duration: 250,
            easing: {
                type: 'ease-out'
            }
        },

        /**
         * @cfg draggable
         * @hide
         */

        /**
         * @cfg {Boolean} indicator
         * Provides an indicator while toggling between child items to let the user
         * know where they are in the card stack.
         * @accessor
         */
        indicator: false,

        /**
         * @cfg {String} ui
         * Style options for Carousel. Default is 'dark'. 'light' is also available.
         * @accessor
         */
        ui: 'dark',

        itemConfig: {},

        bufferSize: 2,

        itemLength: null,

        itemHeight: null
    },

    itemLength: 0,

    itemHeight: 0,

    offset: 0,

    flickStartOffset: 0,

    flickStartTime: 0,

    dragDirection: 0,

    count: 0,

    painted: false,

    activeIndex: -1,

    beforeInitialize: function () {
        this.element.on({
            dragstart: 'onDragStart',
            drag: 'onDrag',
            dragend: 'onDragEnd',
            scope: this
        });

        this.element.on('resize', 'onSizeChange', this);

        this.carouselItems = [];

        this.orderedCarouselItems = [];

        this.inactiveCarouselItems = [];

        this.hiddenTranslation = 0;
    },

    updateBufferSize: function (size) {
        var ItemClass = Ext.carousel.Item,
            total = size * 2 + 1,
            isRendered = this.isRendered(),
            innerElement = this.innerElement,
            items = this.carouselItems,
            ln = items.length,
            itemConfig = this.getItemConfig(),
            itemLength = this.getItemLength(),
            direction = this.getDirection(),
            setterName = direction === 'horizontal' ? 'setWidth' : 'setHeight',
            i, item;

        for (i = ln; i < total; i++) {
            item = Ext.factory(itemConfig, ItemClass);

            if (itemLength) {
                item[setterName].call(item, itemLength);
            }
            item.setLayoutSizeFlags(this.LAYOUT_BOTH);
            items.push(item);
            innerElement.append(item.renderElement);

            if (isRendered && item.setRendered(true)) {
                item.fireEvent('renderedchange', this, item, true);
            }
        }

        this.getTranslatable().setActiveIndex(size);
    },

    setRendered: function (rendered) {
        var wasRendered = this.rendered;

        if (rendered !== wasRendered) {
            this.rendered = rendered;

            var items = this.items.items,
                carouselItems = this.carouselItems,
                i, ln, item;

            for (i = 0, ln = items.length; i < ln; i++) {
                item = items[i];
                if (!item.isInnerItem()) {
                    item.setRendered(rendered);
                }
            }

            for (i = 0, ln = carouselItems.length; i < ln; i++) {
                carouselItems[i].setRendered(rendered);
            }

            return true;
        }

        return false;
    },

    onSizeChange: function () {
        this.refreshSizing();
        this.refreshCarouselItems();
        this.refreshActiveItem();
    },

    onItemAdd: function (item, index) {
        this.callParent(arguments);

        var innerIndex = this.getInnerItems().indexOf(item),
            indicator = this.getIndicator();

        if (indicator && item.isInnerItem()) {
            indicator.addIndicator();
        }

        if (innerIndex <= this.getActiveIndex()) {
            this.refreshActiveIndex();
        }

        if (this.isIndexDirty(innerIndex) && !this.isItemsInitializing) {
            this.refreshActiveItem();
        }
    },

    doItemLayoutAdd: function (item) {
        if (item.isInnerItem()) {
            return;
        }

        this.callParent(arguments);
    },

    onItemRemove: function (item, index) {
        this.callParent(arguments);

        var innerIndex = this.getInnerItems().indexOf(item),
            indicator = this.getIndicator(),
            carouselItems = this.carouselItems,
            i, ln, carouselItem;

        if (item.isInnerItem() && indicator) {
            indicator.removeIndicator();
        }

        if (innerIndex <= this.getActiveIndex()) {
            this.refreshActiveIndex();
        }

        if (this.isIndexDirty(innerIndex)) {
            for (i = 0, ln = carouselItems.length; i < ln; i++) {
                carouselItem = carouselItems[i];

                if (carouselItem.getComponent() === item) {
                    carouselItem.setComponent(null);
                }
            }

            this.refreshActiveItem();
        }
    },

    doItemLayoutRemove: function (item) {
        if (item.isInnerItem()) {
            return;
        }

        this.callParent(arguments);
    },

    onInnerItemMove: function (item, toIndex, fromIndex) {
        if ((this.isIndexDirty(toIndex) || this.isIndexDirty(fromIndex))) {
            this.refreshActiveItem();
        }
    },

    doItemLayoutMove: function (item) {
        if (item.isInnerItem()) {
            return;
        }

        this.callParent(arguments);
    },

    isIndexDirty: function (index) {
        var activeIndex = this.getActiveIndex(),
            bufferSize = this.getBufferSize();

        return (index >= activeIndex - bufferSize && index <= activeIndex + bufferSize);
    },

    getTranslatable: function () {
        var translatable = this.translatable;

        if (!translatable) {
            this.translatable = translatable = new YourTour.view.widget.XTranslatableGroup;
            translatable.setItems(this.orderedCarouselItems);
            translatable.on('animationend', 'onAnimationEnd', this);
        }

        return translatable;
    },

    onDragStart: function (e) {
        var direction = this.getDirection(),
            absDeltaX = e.absDeltaX,
            absDeltaY = e.absDeltaY,
            directionLock = this.getDirectionLock();

        this.isDragging = true;

        if (directionLock) {
            if ((direction === 'horizontal' && absDeltaX > absDeltaY)
                || (direction === 'vertical' && absDeltaY > absDeltaX)) {
                e.stopPropagation();
            }
            else {
                this.isDragging = false;
                return;
            }
        }

        this.getTranslatable().stopAnimation();

        this.dragStartOffset = this.offset;
        this.dragDirection = 0;
    },

    onDrag: function (e) {
        if (!this.isDragging) {
            return;
        }

        var startOffset = this.dragStartOffset,
            direction = this.getDirection(),
            delta = direction === 'horizontal' ? e.deltaX : e.deltaY,
            lastOffset = this.offset,
            flickStartTime = this.flickStartTime,
            dragDirection = this.dragDirection,
            now = Ext.Date.now(),
            currentActiveIndex = this.getActiveIndex(),
            maxIndex = this.getMaxItemIndex(),
            lastDragDirection = dragDirection,
            offset;

        if ((currentActiveIndex === 0 && delta > 0) || (currentActiveIndex === maxIndex && delta < 0)) {
            delta *= 0.5;
        }

        offset = startOffset + delta;

        if (offset > lastOffset) {
            dragDirection = 1;
        }
        else if (offset < lastOffset) {
            dragDirection = -1;
        }

        if (dragDirection !== lastDragDirection || (now - flickStartTime) > 300) {
            this.flickStartOffset = lastOffset;
            this.flickStartTime = now;
        }

        this.dragDirection = dragDirection;

        this.setOffset(offset);
    },

    onDragEnd: function (e) {
        if (!this.isDragging) {
            return;
        }

        this.onDrag(e);

        this.isDragging = false;

        var now = Ext.Date.now(),
            itemLength = this.itemLength,
            threshold = itemLength / 2,
            offset = this.offset,
            activeIndex = this.getActiveIndex(),
            maxIndex = this.getMaxItemIndex(),
            animationDirection = 0,
            flickDistance = offset - this.flickStartOffset,
            flickDuration = now - this.flickStartTime,
            indicator = this.getIndicator(),
            velocity;

        if (flickDuration > 0 && Math.abs(flickDistance) >= 10) {
            velocity = flickDistance / flickDuration;

            if (Math.abs(velocity) >= 1) {
                if (velocity < 0 && activeIndex < maxIndex) {
                    animationDirection = -1;
                }
                else if (velocity > 0 && activeIndex > 0) {
                    animationDirection = 1;
                }
            }
        }

        if (animationDirection === 0) {
            /*if (activeIndex < maxIndex && offset < -threshold) {
                animationDirection = -1;
            }
            else if (activeIndex > 0 && offset > threshold) {
                animationDirection = 1;
            }*/

            if (offset < -threshold) {
                animationDirection = -1;
            }
            else if (offset > threshold) {
                animationDirection = 1;
            }
        }

        if (indicator) {
            indicator.setActiveIndex(activeIndex - animationDirection);
        }

        this.animationDirection = animationDirection;

        this.setOffsetAnimated(animationDirection * itemLength);
    },

    applyAnimation: function (animation) {
        animation.easing = Ext.factory(animation.easing, Ext.fx.easing.EaseOut);

        return animation;
    },

    updateDirection: function (direction) {
        var indicator = this.getIndicator();

        this.currentAxis = (direction === 'horizontal') ? 'x' : 'y';

        if (indicator) {
            indicator.setDirection(direction);
        }
    },

    /**
     * @private
     * @chainable
     */
    setOffset: function (offset) {
        this.offset = offset;

        if (Ext.isNumber(this.itemOffset)) {
            this.getTranslatable().translateAxis(this.currentAxis, offset + this.itemOffset);
        }

        return this;
    },

    /**
     * @private
     * @return {Ext.carousel.Carousel} this
     * @chainable
     */
    setOffsetAnimated: function (offset) {
        var indicator = this.getIndicator();

        if (indicator) {
            indicator.setActiveIndex(this.getActiveIndex() - this.animationDirection);
        }

        this.offset = offset;

        this.getTranslatable().translateAxis(this.currentAxis, offset + this.itemOffset, this.getAnimation());

        return this;
    },

    onAnimationEnd: function (translatable) {
        var currentActiveIndex = this.getActiveIndex(),
            animationDirection = this.animationDirection,
            axis = this.currentAxis,
            currentOffset = translatable[axis],
            itemLength = this.itemLength,
            offset;

        if (animationDirection === -1) {
            offset = itemLength + currentOffset;
        }
        else if (animationDirection === 1) {
            offset = currentOffset - itemLength;
        }
        else {
            offset = currentOffset;
        }

        offset -= this.itemOffset;
        this.offset = offset;
        this.setActiveItem(currentActiveIndex - animationDirection);
    },

    refresh: function () {
        this.refreshSizing();
        this.refreshActiveItem();
    },

    refreshSizing: function () {
        var element = this.element,
            itemLength = this.getItemLength(),
            itemHeight = this.getItemHeight(),

            translatableItemLength = {
                x: 0,
                y: 0
            },
            itemOffset, containerSize;

        if (this.getDirection() === 'horizontal') {
            containerSize = element.getWidth();
        }
        else {
            containerSize = element.getHeight();
        }

        this.hiddenTranslation = -containerSize;

        if (itemLength === null) {
            itemLength = containerSize;
            itemHeight = element.getHeight();
            itemOffset = 0;
        }
        else {
            itemOffset = (containerSize - itemLength) / 2;
        }

        this.itemLength = itemLength;
        this.itemHeight = itemHeight;
        this.itemOffset = itemOffset;
        translatableItemLength[this.currentAxis] = itemLength;
        this.getTranslatable().setItemLength(translatableItemLength);
    },

    refreshOffset: function () {
        this.setOffset(this.offset);
    },

    refreshActiveItem: function () {
        this.doSetActiveItem(this.getActiveItem());
    },

    /**
     * Returns the index of the currently active card.
     * @return {Number} The index of the currently active card.
     */
    getActiveIndex: function () {
        return this.activeIndex;
    },

    refreshActiveIndex: function () {
        this.activeIndex = this.getInnerItemIndex(this.getActiveItem());
    },

    refreshCarouselItems: function () {
        var items = this.carouselItems,
            i, ln, item;

        for (i = 0, ln = items.length; i < ln; i++) {
            item = items[i];
            item.getTranslatable().refresh();
        }

        this.refreshInactiveCarouselItems();
    },

    refreshInactiveCarouselItems: function () {
        var items = this.inactiveCarouselItems,
            hiddenTranslation = this.hiddenTranslation,
            axis = this.currentAxis,
            i, ln, item;

        for (i = 0, ln = items.length; i < ln; i++) {
            item = items[i];
            item.translateAxis(axis, hiddenTranslation);
        }
    },

    /**
     * @private
     * @return {Number}
     */
    getMaxItemIndex: function () {
        return this.innerItems.length - 1;
    },

    /**
     * @private
     * @return {Number}
     */
    getInnerItemIndex: function (item) {
        return this.innerItems.indexOf(item);
    },

    /**
     * @private
     * @return {Object}
     */
    getInnerItemAt: function (index) {
        return this.innerItems[index];
    },

    /**
     * @private
     * @return {Object}
     */
    applyActiveItem: function () {
        var activeItem = this.callParent(arguments),
            activeIndex;

        if (activeItem) {
            activeIndex = this.getInnerItemIndex(activeItem);

            if (activeIndex !== -1) {
                this.activeIndex = activeIndex;
                return activeItem;
            }
        }
    },

    doSetActiveItem: function (activeItem) {
        var activeIndex = this.getActiveIndex(),
            maxIndex = this.getMaxItemIndex(),
            indicator = this.getIndicator(),
            bufferSize = this.getBufferSize(),
            carouselItems = this.carouselItems.slice(),
            orderedCarouselItems = this.orderedCarouselItems,
            visibleIndexes = {},
            visibleItems = {},
            visibleItem, component, id, i, index, ln, carouselItem;

        if (carouselItems.length === 0) {
            return;
        }

        this.callParent(arguments);

        orderedCarouselItems.length = 0;

        if (activeItem) {
            id = activeItem.getId();
            visibleItems[id] = activeItem;
            visibleIndexes[id] = bufferSize;

            if (activeIndex >= 0) {
                for (i = 1; i <= bufferSize; i++) {
                    index = activeIndex - i;
                    if (index >= 0) {
                        visibleItem = this.getInnerItemAt(index);
                        id = visibleItem.getId();
                        visibleItems[id] = visibleItem;
                        visibleIndexes[id] = bufferSize - i;
                    } else if (index < 0) {
                        visibleItem = this.getInnerItemAt(maxIndex);
                        id = visibleItem.getId();
                        visibleItems[id] = visibleItem;
                        visibleIndexes[id] = bufferSize - i;
                    } else {
                        break;
                    }
                }
            }

            if (activeIndex <= maxIndex) {
                for (i = 1; i <= bufferSize; i++) {
                    index = activeIndex + i;
                    if (index <= maxIndex) {
                        visibleItem = this.getInnerItemAt(index);
                        id = visibleItem.getId();
                        visibleItems[id] = visibleItem;
                        visibleIndexes[id] = bufferSize + i;
                    } else if (index > maxIndex) {
                        visibleItem = this.getInnerItemAt(0);
                        id = visibleItem.getId();
                        visibleItems[id] = visibleItem;
                        visibleIndexes[id] = bufferSize - i;
                    } else {
                        break;
                    }
                }
            }

            for (i = 0, ln = carouselItems.length; i < ln; i++) {
                carouselItem = carouselItems[i];
                component = carouselItem.getComponent();

                if (component) {
                    id = component.getId();

                    if (visibleIndexes.hasOwnProperty(id)) {
                        carouselItems.splice(i, 1);
                        i--;
                        ln--;
                        delete visibleItems[id];
                        orderedCarouselItems[visibleIndexes[id]] = carouselItem;
                    }
                }
            }

            for (id in visibleItems) {
                if (visibleItems.hasOwnProperty(id)) {
                    visibleItem = visibleItems[id];
                    carouselItem = carouselItems.pop();
                    carouselItem.setComponent(visibleItem);
                    orderedCarouselItems[visibleIndexes[id]] = carouselItem;
                }
            }
        }

        var itemHeight = this.itemHeight;
        Ext.Array.forEach(orderedCarouselItems, function(item, index){
            item.itemHeight = itemHeight;
            if(index != 1){
                item.setHeight('60%');
                item.setTop( itemHeight * 0.4 / 2);
            }else{
                item.setTop(0);
                item.setHeight('100%');
            }
        });

        this.inactiveCarouselItems.length = 0;
        this.inactiveCarouselItems = carouselItems;
        this.refreshOffset();
        this.refreshInactiveCarouselItems();

        if (indicator) {
            indicator.setActiveIndex(activeIndex);
        }
    },

    /**
     * Switches to the next card.
     * @return {Ext.carousel.Carousel} this
     * @chainable
     */
    next: function () {
        this.setOffset(0);

        this.animationDirection = -1;
        this.setOffsetAnimated(-this.itemLength);
        return this;
    },

    /**
     * Switches to the previous card.
     * @return {Ext.carousel.Carousel} this
     * @chainable
     */
    previous: function () {
        this.setOffset(0);

        this.animationDirection = 1;
        this.setOffsetAnimated(this.itemLength);
        return this;
    },

    // @private
    applyIndicator: function (indicator, currentIndicator) {
        return Ext.factory(indicator, Ext.carousel.Indicator, currentIndicator);
    },

    // @private
    updateIndicator: function (indicator) {
        if (indicator) {
            this.insertFirst(indicator);

            indicator.setUi(this.getUi());
            indicator.on({
                next: 'next',
                previous: 'previous',
                scope: this
            });
        }
    },

    destroy: function () {
        var carouselItems = this.carouselItems.slice();

        this.carouselItems.length = 0;

        Ext.destroy(carouselItems, this.getIndicator(), this.translatable);

        this.callParent();
        delete this.carouselItems;
    }

}, function () {
    //<deprecated product=touch since=2.0>
    this.override({
        constructor: function (config) {
            if (config && 'activeIndex' in config) {
                //<debug warn>
                Ext.Logger.deprecate("'activeIndex' config is deprecated, please use 'activeItem' config instead }");
                //</debug>

                config.activeItem = config.activeIndex;
            }

            this.callParent([config]);
        }
    });

    Ext.deprecateClassMethod(this, {
        /**
         * Returns `true` when {@link #direction} is 'vertical'.
         * @return {Boolean}
         * @deprecated 2.0.0 Use `getDirection() === 'vertical'` instead.
         */
        isVertical: function getDirection() {
            return this.getDirection() === 'vertical';
        },
        /**
         * Returns `true` when {@link #direction} is 'horizontal'.
         * @return {Boolean}
         * @deprecated 2.0.0 Use `getDirection() === 'horizontal'` instead.
         */
        isHorizontal: function getDirection() {
            return this.getDirection() === 'horizontal';
        },
        /**
         * @method
         * @inheritdoc Ext.carousel.Carousel#previous
         * @deprecated 2.0.0 Use {@link Ext.carousel.Carousel#previous} instead.
         */
        prev: 'previous'
    });
    //</deprecated>
})