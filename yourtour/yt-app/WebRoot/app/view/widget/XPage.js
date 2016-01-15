Ext.define('YourTour.view.widget.XPage', {
    extend: 'Ext.Panel',
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

        attrs: null,

        data: null
    },

    initialize: function () {
        var me = this;
        me.callParent(arguments);


        me.getScrollable().getScroller().on('scroll', function (scroller, x, y) {
            me.scroll(scroller, x, y);
        });

        me.getScrollable().getScroller().on('scrollend', me.onScrollerEnd,me);
    },

    updateData: function (data) {
        this.data = data;

        this.fillData();
    },

    getData: function () {
        return this.data;
    },

    onScrollerEnd : function(scroller,offsets){
        var y = offsets.y;
        if(y <= 0){
            return false;
        }
    },

    scroll: function (scroller, x, y) {
        if(y <= 0){
            return false;
        }
    },

    fillData:function(){
        var me = this;
        var data = this.data;

        var elements = Ext.ComponentQuery.query('xfield,xmultifield,dataview', me);
        Ext.Array.forEach(elements,function(item){
            item.updateRecord(data);
        });
    }
});

