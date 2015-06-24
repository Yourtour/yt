Ext.define('YourTour.controller.RouteNew', {
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
    
    OnNextClick:function(){
    	var store = this.routeStore;
    	var route = Ext.create('YourTour.model.RouteModel');
    	this.getForm().updateRecord(route);
    	
    	route.setProxy(store.getProxy());
    	route.save({
			success:function(){
				routeStore.add(route);
				Ext.Viewport.setActiveItem("mainview");
			}
		});
    },
    
    showNewRouteView:function(){
    	Ext.Viewport.setActiveItem("routenew");
    }
});
