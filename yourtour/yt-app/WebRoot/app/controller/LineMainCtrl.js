Ext.define('YourTour.controller.LineMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{
           lineListView:'#LineListView',
    	   lineList:'#LineListView #lineList',

           lineIntroductionView:'#LineIntroductionView'
       },
       
       control:{

       }
    },

    showLineList:function(placeId, store){
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.line.LineListView'));
        var me = this, lineListView = me.getLineListView();

        if(store) {
            lineListView.bindData(store)
        }

        me.refreshLineList();
    },

    /**
     * 刷新线路列表清单数据
     */
    refreshLineList:function(){
        var me = this,
            lineListView = me.getLineListView(),
            store = lineListView.getData();

        if(store){
            me.getLineList().setStore(store);
        }
    },

    onLinesTap:function(dataView, index, target, record, e, eOpts){
        this.showLineInfo(record);
    },

    /**
     * 显示线路信息
     * @param record
     */
    showLineInfo:function(record){
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.line.LineIntroductionView'));

        var me = this, view = this.getLineIntroductionView();
        view.setData(record);
    }
});
