Ext.define('YourTour.view.widget.XZoomCarousel', {
    extend: 'Ext.Container',
    xtype: 'xzoomcarousel',
    config: {
        cls: 'x-xzoomcarousel',
        layout: 'hbox',
        activeIndex: 0
    },

    initialize: function () {
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
        activeItem.setCentered(true);

        var preItem = this.getAt(preItemIndex);
        preItem.setHeight('80%');
        preItem.setLeft(-150);

        var nextItem = this.getAt(nextItemIndex);
        nextItem.setHeight('80%');
        nextItem.setRight(-160);
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
        /*alert('end')
        var startX = this.startX, lastX = a.pageX;

        if(Math.abs(lastX - startX) > 20){
            console.log('move')
        }*/
    }
})