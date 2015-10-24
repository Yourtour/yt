Ext.define('YourTour.store.LaunchStore', {
	extend: 'YourTour.store.AjaxStore',
    requires: [
       'YourTour.model.LaunchModel'
    ],
    config:{
    	model:'YourTour.model.LaunchModel',
    	
    	proxy:{
	    	api:{
				 read: YourTour.util.Context.getContext('/app/launch')
			}
    	}
    }
});
