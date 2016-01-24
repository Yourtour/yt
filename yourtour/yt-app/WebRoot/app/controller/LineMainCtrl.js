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
    	   	},

           '#LineIntroductionView #featureTitle':{
               tap:'onFeatureTitleTap'
           },

           '#LineIntroductionView #reasonTitle':{
               tap:'onReasonTitleTap'
           }
       },

        record:null
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
        this.record = record;
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.line.LineIntroductionView'));

        var view = this.getLineIntroductionView();
        var image = view.down('#image');
        image.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "' style='width:100%; max-height:150px'>");

        var name = view.down('#name');
        name.setHtml(record.get('name'));

        var feature = view.down('#feature');
        feature.setHtml(Ext.String.ellipsis(record.get('feature'),70,false));

        var reason = view.down('#reason');
        reason.setHtml(Ext.String.ellipsis(record.get('reason'),70,false));
    },

    onFeatureTitleTap:function(){
        var controller = this.getApplication().getController('CommonMainCtrl');
        controller.showContentReadView('线路特点', this.record.get('feature'));
    },

    onReasonTitleTap:function(){
        var controller = this.getApplication().getController('CommonMainCtrl');
        controller.showContentReadView('推荐理由', this.record.get('reason'));
    }
});
