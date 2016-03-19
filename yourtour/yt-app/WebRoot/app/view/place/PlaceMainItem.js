Ext.define('YourTour.view.place.PlaceMainItem', {
    extend: 'Ext.Container',
    xtype: 'PlaceMainItem',
    config: {
        layout: 'card',
        padding: '0 0 10 0',
        items: [
            {
                xtype: 'container',
                itemId: 'container',
                layout: 'vbox',
                scrollable: {
                    direction: 'vertical',
                    indicators: false,
                    directionLock: true,
                    momentumEasing: {
                        bounce: {
                            acceleration: 0.0001,
                            springTension: 0.9999
                        }
                    },
                    outOfBoundRestrictFactor: 0
                },
                items: [
                    {
                        xtype: 'img',
                        itemId: 'image',
                        height:'60%',
                        width: '100%',
                        mode: 'tag'
                    },

                    {
                        xtype: 'xmultifield',
                        itemId: 'memo',
                        underline: false,
                        flex:1
                    },

                    {
                        xtype: 'xmultifield',
                        underline: false,
                        flex:1
                    }
                ]
            }
        ]
    },

    /*beforeInitialize: function () {
        var container = this.down('#container');
        container.element.on({
            drag: 'onDrag',
            dragend: 'onDragEnd',
            scope: this
        });

        container.getScrollable().getScroller().on(
            {
                scrollstart: 'onScrollStart',
                scope: this
            }
        );
    },

    onScrollStart: function (e) {
        this.dragging = true;
    },

    onDrag: function (e) {
        if (!this.dragging) return;

        var container = this.down('#container'),
            scroller = container.getScrollable().getScroller(),
            startPosition = scroller.startPosition,
            position = scroller.position;

        if (parseInt(startPosition.y) != parseInt(position.y)) return;

        if (e.absDeltaY > 50) {
            var carousel = this.up('.carousel');
            if (carousel) {
                var activeIndex = carousel.getActiveIndex(this), nextItem = null;
                if (Math.abs(startPosition.y) == 0 && activeIndex > 0) {
                    nextItem = carousel.getAt(activeIndex - 1);
                } else if (Math.abs(startPosition.y) > 0 && activeIndex < carousel.getItems().length - 1) {
                    nextItem = carousel.getAt(activeIndex + 1);
                }

                if (nextItem != null) {
                    this.dragging = false;
                    nextItem.scrollToTop();
                    carousel.animateActiveItem(nextItem, {type: 'flip', duration: 1000});
                }
            }
        }
    },

    scrollToTop: function () {
        var container = this.down('#container'),
            scroller = container.getScrollable().getScroller();

        scroller.scrollToTop();
    },*/

    updateData: function (data) {
        if (data) {
            var me = this,
                image = me.down('#image'),
                memo = me.down('#memo');

            var url = YourTour.util.Context.getImageResource(data.get('imageUrl'));
            image.setSrc(url);

            memo.setText(data.get('memo'));
        }
    }
});

