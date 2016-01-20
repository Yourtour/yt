Ext.define('YourTour.view.widget.XPage', {
    extend: 'Ext.Container',
    xtype: 'xpage',
    config: {
        layout:'vbox',

        items:[

        ],

        fullscreen: true,

        data: null,

        callback:null
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

    updateRecord:function(){
        var me = this;
        var data = me.data;

        if(data) {
            YourTour.util.Context.fillViewFields(me, data);
        }
    },

    updateCallback:function(callback){
        this.callback = callback;
    },

    getCallback:function(){
        return this.callback;
    },

    hideProcessing:function() {
        var pagebody = this.down('#pagebody');
        if(pagebody){
            this.setActiveItem(pagebody);
        }
    }
});

