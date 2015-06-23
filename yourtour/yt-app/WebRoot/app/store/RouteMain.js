Ext.define('YourTour.store.RouteMain', {
    extend: 'YourTour.store.AjaxStore',
    requires: [
       'YourTour.model.RouteMain'
    ],
    config:{
    	model:'YourTour.model.RouteMain',
    	autoLoad:false,
    	
    	proxy:{
	    	api:{
				 read: YourTour.util.Context.getContext('/route/user/Query.action'),
				 create: YourTour.util.Context.getContext('/route/Save.action'),
				 update: YourTour.util.Context.getContext('/route/Update.action'),
				 destroy: YourTour.util.Context.getContext('/route/Delete.action')
			}
    	}
    },
});
