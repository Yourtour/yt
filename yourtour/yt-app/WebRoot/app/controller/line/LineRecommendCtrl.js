Ext.define('YourTour.controller.line.LineRecommendCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.LineStore'],
    config: {
       refs:{
    	   lineList:'#LineRecommendView #lineList',
    	   closeBtn:'#LineRecommendView #close',
    	   newBtn:'#LineRecommendView #new'
       },
       
       control:{
       	   	/**
       	    * 线路推荐列表返回按钮事件定义
       	    * @type 
       	    */
       	   	closeBtn:{
       	   	   tap:'backToNewRouteView'
       	   	},
       	   
       	   /**
       	    * 线路推荐列表详情按钮事件定义
       	    * @type 
       	    */
       	   	newBtn:{
       	   	   tap:'onLineNewTap'
       	   	},
       	   
			lineList:{
    	   	   itemtap:'onLinesTap'	
    	   	}
       },
       
       routes:{
        	'/line/recommend':'showView'
       },
       
       store : null
    },
    
    init: function(){
    	this.store = Ext.create('YourTour.store.LineStore');	
    },
    
    onLinesTap:function(dataView, index, target, record, e, eOpts){
    	this.redirectTo('/line/introduction/' + index);
    },
    
    onLineNewTap:function(){
    	this.redirectTo("/route/schedule/1");
    },
   
    backToNewRouteView:function(){
    	this.show('newrouteview','YourTour.view.route.NewView');
    },
    
    showView:function(){
    	this.show('LineRecommendView','YourTour.view.line.LineRecommendView');
    	
    	var store = this.store;
    	var lines = this.getLineList();
    	lines.setStore(store);
    }
});
