Ext.define('YourTour.controller.route.MainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.RouteStore','YourTour.view.route.MainItem'],
    
    config: {
       refs: {
       	    routemainview:'#routemainview',
        	routeCarousel:'#routemainview #routeCarousel',
       		newRoute:'#routemainview #new'
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
        	'/main/route':'showRoute'
       },
       
       store:null
    },
    
    init: function(){
    	this.store = Ext.create('YourTour.store.RouteStore',{storeId:'routemainstore'});	
    },
    
    newRoute:function(){
    	this.redirectTo("/route/new");
    },
    
    showRoute:function(){
    	this.show('routemainview','YourTour.view.route.MainView');
    	
    	var handler = function(){
			var store = Ext.getStore('routemainstore');
    		var routeCarousel = this.getRouteCarousel();
    		routeCarousel.removeAll(true, false);
        	store.each(function(item){
        		var routePanel = Ext.create('YourTour.view.route.MainItem');
        		routePanel.setRecord(item);
        		routeCarousel.add(routePanel);
        	});
        	
        	routeCarousel.setActiveItem(0);
    	};
    	
    	this.store.load(handler, this);
    },
    
    onRouteTap:function(){
    	var routeCarousel = this.getRouteCarousel();
    	var index = routeCarousel.getActiveIndex();
    	var record = this.store.getAt(index);
   		this.redirectTo('/route/schedule/' + record.get('rowKey'));
    }
});
