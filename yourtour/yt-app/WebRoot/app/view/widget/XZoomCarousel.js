Ext.define('YourTour.view.widget.XZoomCarousel', {
    extend: 'Ext.Carousel',
    xtype: 'xzoomcarousel',
    requires: [
        'Ext.fx.easing.EaseOut',
        'Ext.carousel.Item',
        'Ext.carousel.Indicator',
        'YourTour.view.widget.XTranslatableGroup'
    ],

    config: {
        indicator:false
    },

    refreshSizing: function() {
        var element = this.element,
            itemLength = this.getItemLength(),
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
            itemLength = containerSize * 0.6;
            itemOffset = 0;
        }
        else {
            itemOffset = (containerSize - itemLength) / 2;
        }

        this.itemLength = itemLength;
        this.itemOffset = itemOffset;
        translatableItemLength[this.currentAxis] = itemLength;
        this.getTranslatable().setItemLength(translatableItemLength);
    },

    getTranslatable: function() {
        var translatable = this.translatable;

        if (!translatable) {
            this.translatable = translatable = new YourTour.view.widget.XTranslatableGroup;
            translatable.setItems(this.orderedCarouselItems);
            translatable.on('animationend', 'onAnimationEnd', this);
        }

        return translatable;
    }
})