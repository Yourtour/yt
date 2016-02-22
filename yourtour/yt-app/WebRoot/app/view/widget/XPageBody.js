Ext.define('YourTour.view.widget.XPageBody', {
    extend: 'Ext.Container',
    xtype: 'xpagebody',
    config:{
        itemId:'pagebody',
        flex:1,
        layout:'vbox',
        scrollable: {
            direction: 'vertical',
            indicators: false,
            directionLock: true,
            momentumEasing:  {
                bounce: {
                    acceleration: 0.0001,
                    springTension: 0.9999
                }
            },
            outOfBoundRestrictFactor: 0
        }
    },

    initialize: function () {
        this.callParent(arguments);
        /*var me = this, scroller = me.getScrollable().getScroller();

        scroller.on({
            scroll: 'onScroller',
            scrollend: 'onScrollerEnd',
        });*/
    },

    /*onScroller: function (scroller, x, y,eOpts ) {
        alert('onScroller');
    },

    onScrollerEnd: function (scroller, x, y,eOpts ) {
        alert('onScrollerEnd');
    }*/
});

