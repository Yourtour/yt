Ext.define('YourTour.controller.line.RecommendCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.LineStore','YourTour.view.line.RecommedListItem'],
    config: {
       refs:{
    	   lines:'#linerecommendview #lines'
       },
       
       control:{
       	   /**
       	    * 线路推荐列表返回按钮事件定义
       	    * @type 
       	    */
       	   '#linerecommendview #close':{
       	   	   tap:'backToNewRouteView'
       	   },
       	   
       	   /**
       	    * 线路推荐列表详情按钮事件定义
       	    * @type 
       	    */
       	   '#linerecommendview #new':{
       	   	   tap:'onLineNewTap'
       	   },
       	   
       	   /**
       	    * 推荐列表点击事件捕获处理
       	    * @type 
       	    */
    	   lines:{
    		   itemtap:"onLinesItemTap"
    	   }
       },
       
       routes:{
        	'/line/recommend':'showView'
       },
       
       store : undefined
    },
    
    init: function(){
    	this.store = Ext.create('YourTour.store.LineStore');	
    },
    
    onLineNewTap:function(){
    	
    },
   
    backToNewRouteView:function(){
    	this.show('newrouteview','YourTour.view.route.NewView');
    },
    
    onLinesItemTap:function(dataView, index, target, record, e, eOpts){
    	this.redirectTo('/line/introduction/' + index);
    },
   
    /**
     * 
     */
    showView:function(){
    	this.show('linerecommendview','YourTour.view.line.RecommendView');
    	
    	var store = this.store;
    	
    	var lines = this.getLines();
        var showView = function(){
        	lines.setStore(store);
        };
        
        store.load(showView,this);
    }
});
