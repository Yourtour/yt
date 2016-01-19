Ext.define('YourTour.view.widget.XPageBody', {
    extend: 'Ext.Container',
    xtype: 'xpagebody',
    config:{
        itemId:'pagebody',
        flex:1,
        scrollable: {
            direction: 'vertical',
            indicators: false,
            directionLock: true,
            momentumEasing:  {
                /*momentum: {
                 acceleration: 10,
                 friction: 0.9
                 },*/
                bounce: {
                    acceleration: 0.0001,
                    springTension: 0.9999
                }
                /*minVelocity: 5*/
            },
            outOfBoundRestrictFactor: 0
        }
    },

    initialize: function () {
        var me = this;
        me.callParent(arguments);

        /*me.getScrollable().getScroller().on('scroll', me.onScroller,me);

        me.getScrollable().getScroller().on('scrollend', me.onScrollerEnd,me);*/
    },

    /*onScrollerEnd : function(scroller,offsets){
    },

    onScroller: function (scroller, x, y,eOpts ) {
        console.log(scroller);
    }*/
});

