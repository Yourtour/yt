Ext.define('YourTour.view.widget.XPage', {
    extend: 'Ext.Container',
    xtype: 'xpage',
    config: {
        layout:'vbox',
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
                    springTension: 0.9999,
                },
                /*minVelocity: 5*/
            },
            outOfBoundRestrictFactor: 0
        },

        fullscreen: true,

        data: null
    },

    initialize: function () {
        var me = this;
        me.callParent(arguments);

        me.getScrollable().getScroller().on('scroll', me.onScroller,me);

        me.getScrollable().getScroller().on('scrollend', me.onScrollerEnd,me);
    },

    updateData: function (data) {
        this.data = data;

        this.updateRecord();
    },

    getData: function () {
        return this.data;
    },

    onScrollerEnd : function(scroller,offsets){
    },

    onScroller: function (scroller, x, y) {
    },

    updateRecord:function(){
        var me = this;
        var data = me.data;

        if(data) {
            YourTour.util.Context.fillViewFields(me, data);
        }
    }
});

