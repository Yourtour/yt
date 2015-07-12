Ext.define('YourTour.controller.line.RecommendCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.LineStore','YourTour.view.line.RecommedListItem'],
    config: {
       refs:{
    	   lines:'#linerecommendview #lines',
    	   
    	   recommendRoutes:'#lineListItem #routes',
    	   
    	   lineInfo:'#lineListItem #lineInfo'
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
       	   
    	   recommendRoutes:{
    	   	   itemtap:'onRouteItemTap'	
    	   },
    	   
    	   lineInfo:{
    	       tap:'onLineInfoTap'
    	   },
    	   
    	   lines:{
    	   	
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
    
    onLineInfoTap:function(){
    	var lines = this.getLines();
    	var index = lines.getActiveIndex();
    	this.redirectTo('/line/introduction/' + index);
    },
    
    onLineNewTap:function(){
    	this.redirectTo("/route/schedule/1");
    },
   
    backToNewRouteView:function(){
    	this.show('newrouteview','YourTour.view.route.NewView');
    },
    
    onRouteItemTap:function(dataView, index, target, record, e, eOpts){
    	this.redirectTo('/route/reference/schedule/' + index);
    },
   
    /**
     * 
     */
    showView:function(){
    	this.show('linerecommendview','YourTour.view.line.RecommendListView');
    	
    	var store = this.store;
    	var lines = this.getLines();
        var showView = function(){
        	store.each(function(item){
        		var line = Ext.create("YourTour.view.line.RecommedListItem",{model:item});
				lines.add(line);        	
				
				var routes = line.down('#routes');
				var items = routes.getViewItems();
				var height = 0;
				for(var index = 0; index < items.length; index++){
					height += Ext.get(items[index].getId()).getHeight();
				}
				
				console.log(height);
	 	   		routes.setHeight(height + 100);
        	});
        };
        
        store.load(showView,this);
    }
});
