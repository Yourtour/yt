Ext.define('YourTour.controller.LineMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{
           lineRecommendListView:'#LineRecommendListView',
    	   lineList:'#LineRecommendListView #lineList',

           lineIntroductionView:'#LineIntroductionView'
       },
       
       control:{
       	   	'#LineRecommendListView #new':{
       	   	   tap:'onLineNewTap'
       	   	},

			lineList:{
    	   	   itemtap:'onLinesTap'	
    	   	}
       }
    },

    /**
     * 显示页面，并在列表显示达人推荐线路
     */
    getRecommendLineList:function(duration, places){
        var me = this;
		Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.line.LineRecommendListView'));

        //获取达人推荐线路
        var store = Ext.create('YourTour.store.AjaxStore', {model:'YourTour.model.LineModel'});
        var proxy = store.getProxy();
        proxy.setUrl(YourTour.util.Context.getContext('/lines/match/' + places));
        store.load(function(){
            me.getLineList().setStore(store);
        })
    },

    onLinesTap:function(dataView, index, target, record, e, eOpts){
        this.redirectTo('/line/introduction/' + index);
    }
});
