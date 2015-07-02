Ext.define('YourTour.controller.route.NewCtrl', {
    extend: 'Ext.app.Controller',
    requires:['YourTour.store.RouteStore', 'YourTour.model.RouteModel'],
    config: {
       refs:{
    	  newRouteView:'#newrouteview',
       },
       
       control:{
       	   '#newrouteview #add':{
       	   	   tap:"addDestinationPlace"	
       	   },
       	   
    	   "#newrouteview #close":{
    		   tap:"OnCloseClick"
    	   },
    	   
    	   '#newrouteview #place':{
    		   focus:"addStartPlace"
    	   },
    	   
    	   '#newrouteview #next':{
    		   tap:'OnNextClick'
    	   }
       },
       
       routes:{
        	'/route/new':'showNewRouteView'
       }
    },
    
    routeStore:Ext.create('YourTour.store.RouteStore'),
    
    addDestinationPlace:function(){
    	console.log('addDestinationPlace');
    	
    	this.redirectTo('/place/selection/1');
    },
    
    OnCloseClick:function(){
    	Ext.Viewport.setActiveItem("mainview");
    },
    
    addStartPlace:function(){
    	this.redirectTo('/place/selection/2');
    },
    
    OnNextClick:function(){
    	this.redirectTo('/line/recommend');
    },
    
    showNewRouteView:function(){
    	Ext.Viewport.setActiveItem('newrouteview');
    }
});
