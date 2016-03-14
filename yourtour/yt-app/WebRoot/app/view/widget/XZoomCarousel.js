Ext.define('YourTour.view.widget.XZoomCarousel', {
    extend: 'Ext.Carousel',
    xtype: 'xzoomcarousel',
    config: {
        cls: 'x-xzoomcarousel',
        layout: 'hbox',
        indicator:false,
    },


    /**
     * @private
     * @chainable
     */
    setOffset: function(offset) {
        var delta = Math.abs(offset) / 200;
        delta = delta - 0.2 ? '0.2' : delta;

        this.offset = offset;

        if (Ext.isNumber(this.itemOffset)) {
            var activeIndex = this.getActiveIndex(),
                activeItem = this.getAt(activeIndex),
                leftItem = activeIndex == 0 ? null : this.getAt(activeIndex - 1);
                rightItem = activeIndex == this.getMaxItemIndex() ? null: this.getAt(activeIndex + 1);

            activeItem.setHeight((1 - delta) * 100 + '%');

            if(offset > 0) {
                if(leftItem != null){
                    leftItem.setHeight((0.8 + delta) * 100 + '%')
                }
            }else{
                if(rightItem != null) {
                    rightItem.setHeight((0.8 + delta) * 100 + '%');
                }
            }

            this.getTranslatable().translateAxis(this.currentAxis, offset + this.itemOffset);
        }

        return this;
    },


    doItemLayoutAdd: function(item) {
        console.log(item);

        this.callParent(arguments);
    }


    /*initialize: function () {
        this.callParent(arguments);

        var items = this.getItems();
        if (items.length > 0) {
            this.activeItem(0);
        }

        var me = this;
        this.element.on({
            dragstart: me.onTouchStart,
            drag: me.onTouchMove,
            dragend: me.onTouchEnd,
            scope: me
        });
    },

    activeItem: function (activeIndex) {
        var me = this,
            items = me.getItems(),
            length = items.length,
            activeItem = this.getAt(activeIndex);

        var preItemIndex = (activeIndex == 0) ? length - 1 : activeIndex - 1,
            nextItemIndex = (activeIndex == length - 1) ? 0 : activeIndex + 1;

        activeItem.setWidth('80%');

        var preItem = this.getAt(preItemIndex);
        preItem.setHeight('80%');

        var nextItem = this.getAt(nextItemIndex);
        nextItem.setHeight('80%');
    },

    onTouchStart: function (a) {
        this.startX = a.pageX;
        this.lastX = a.pageX;
    },

    onTouchMove: function (a) {
        var me = this,
            activeIndex = this.activeIndex || this.getActiveIndex(),
            item = this.getAt(activeIndex);

        var deltaX = a.pageX - this.lastX;
        if (deltaX && deltaX != 0) {
            var right = item.getRight();
            item.setRight(right - deltaX);
        }

        this.lastX = a.pageX;
    },

    onTouchEnd: function (a) {
        var startX = this.startX, lastX = a.pageX;
        if(Math.abs(lastX - startX) > 20){
        }
    }*/
})