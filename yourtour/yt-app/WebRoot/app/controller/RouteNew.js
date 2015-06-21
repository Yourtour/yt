Ext.define('YourTour.controller.RouteNew', {
    extend: 'Ext.app.Controller',
    requires: [
       'YourTour.view.RouteNew'
    ],
    config: {
       control:{
    	   "#close":{
    		   tap:"close"
    	   }
       },
       
       routes:{
        	'/route/new':'showNewRouteView'
       }
    },
    
    close:function(){
    	Ext.Viewport.setActiveItem("mainview");
    },
    
    showNewRouteView:function(){
    	Ext.Viewport.setActiveItem("routenew");
    }
});
