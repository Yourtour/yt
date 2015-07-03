Ext.define('YourTour.controller.route.MainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.RouteStore','YourTour.store.RouteLocalStore', 'YourTour.model.RouteModel','YourTour.view.route.MainItem'],
    
    config: {
       refs: {
       	    routemainview:'#routemainview',
        	routeCarousel:'#routemainview #routeCarousel',
       		newRoute:'#routemainview #new' 	
       },
       
       control:{
    	   newRoute:{
    		   tap:'newRoute'
    	   }
       },
       
       routes:{
        	'/main/route':'showRoute'
       }
    },
    
    routeStore:Ext.create('YourTour.store.RouteStore'),
    
    newRoute:function(){
    	this.redirectTo("/route/new");
    },
    
    showRoute:function(){
    	this.show('routemainview','YourTour.view.route.MainView');
    	
    	var handler = function(){
    		var routeCarousel = this.getRouteCarousel();
    		routeCarousel.removeAll(true, false);
        	this.routeStore.each(function(item){
        		var routePanel = Ext.create('YourTour.view.route.MainItem');
        		routePanel.setRecord(item);
        		routeCarousel.add(routePanel);
        	});
        	
        	routeCarousel.setActiveItem(0);
    	};
    	
    	this.routeStore.load({params:{userId:'1111','userName':'tony'}, callback:handler,scope:this});
    }
});
