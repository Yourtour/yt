Ext.define('YourTour.store.RouteStore', {
    extend: 'YourTour.store.AjaxStore',
    requires: [
       'YourTour.model.RouteModel'
    ],
    config:{
    	model:'YourTour.model.RouteModel',
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
