Ext.define('YourTour.controller.route.New', {
    extend: 'Ext.app.Controller',
    requires:['YourTour.store.RouteStore', 'YourTour.model.RouteModel'],
    config: {
       refs:{
    	  form:'#routenew'
       },
       
       control:{
    	   "#close":{
    		   tap:"OnCloseClick"
    	   },
    	   
    	   '#routenew #place':{
    		   focus:"OnPlaceClick"
    	   },
    	   
    	   '#next':{
    		   tap:'OnNextClick'
    	   }
       },
       
       routes:{
        	'/route/new':'showNewRouteView'
       }
    },
    
    routeStore:Ext.create('YourTour.store.RouteStore'),
    
    OnCloseClick:function(){
    	Ext.Viewport.setActiveItem("mainview");
    },
    
    OnPlaceClick:function(){
    	this.redirectTo('/place/selection');
    },
    
    OnNextClick:function(){
    	this.redirectTo('/route/plan');
    },
    
    showNewRouteView:function(){
    	Ext.Viewport.setActiveItem("routenew");
    }
});
