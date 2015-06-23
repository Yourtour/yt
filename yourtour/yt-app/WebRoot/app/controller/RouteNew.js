Ext.define('YourTour.controller.RouteNew', {
    extend: 'Ext.app.Controller',
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
    
    store:Ext.create('YourTour.store.RouteMain'),
    
    OnCloseClick:function(){
    	Ext.Viewport.setActiveItem("mainview");
    },
    
    OnNextClick:function(){
    	var routeStore = this.store;
    	var route = Ext.create('YourTour.model.RouteMain');
    	this.getForm().updateRecord(route);
    	
    	route.setProxy(routeStore.getProxy());
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
