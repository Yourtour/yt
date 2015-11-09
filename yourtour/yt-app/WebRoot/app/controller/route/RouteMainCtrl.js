Ext.define('YourTour.controller.route.RouteMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.RouteStore','YourTour.view.route.MainItem'],
    
    config: {
       refs: {
       	    routeMainView:'#RouteMainView',
        	routeCarousel:'#RouteMainView #routeCarousel',
       		newRoute:'#RouteMainView #new'
       },
       
       control:{
    	   newRoute:{
    		   	tap:'newRoute'
    	   },
    	   
    	   '#routemainitem #btnRoute':{
    	   		tap:'onRouteTap'	
    	   }
       },
       
       routes:{
        	'/main/route':'showPage'
       },
       
       store:null
    },
    
    newRoute:function(){
    	this.redirectTo("/route/new");
    },
    
    showPage:function(){
    	var me = this;
    	
    	YourTour.util.Context.mainview = me.getRouteMainView();
    	
    	var routeCarousel = me.getRouteCarousel();
    	var store = me.store = Ext.create('YourTour.store.RouteStore',{storeId:'routemainstore'});	
    	var handler = function(){
    		routeCarousel.removeAll(true, false);
        	store.each(function(item){
        		var routePanel = Ext.create('YourTour.view.route.MainItem');
        		routePanel.setRecord(item);
        		routeCarousel.add(routePanel);
        	});
        	
        	routeCarousel.setActiveItem(0);
    	};

    	var proxy = store.getProxy();
    	proxy.setUrl(YourTour.util.Context.getContext('/routes/personal/query'));
    	store.load(handler, this);
    },
    
    onRouteTap:function(){
    	var routeCarousel = this.getRouteCarousel();
    	var index = routeCarousel.getActiveIndex();
    	var record = this.store.getAt(index);
   		this.redirectTo('/route/schedule/' + record.get('rowKey'));
    }
});
