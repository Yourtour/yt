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

        items:[

        ],

        fullscreen: true,

        data: null
    },

    initialize: function () {
        var me = this;
        me.callParent(arguments);

        me.getScrollable().getScroller().on('scroll', me.onScroller,me);

        me.getScrollable().getScroller().on('scrollend', me.onScrollerEnd,me);
    },

    /**
     * 1）将数据绑定到当前页面，2）负责页面数据更新
     * @param data
     */
    updateData: function (data) {
        this.data = data;

        this.updateRecord();
    },

    /**
     * 只将数据绑定到当前页面，而不做页面数据更新
     * @param data
     */
    bindData:function(data){
        this.data = data;
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
    },

    hideProcessing:function() {
        var pagebody = this.down('#pagebody');
        if(pagebody){
            this.setActiveItem(pagebody);
        }
    }
});

