Ext.define('YourTour.store.RouteMemberStore', {
	extend: 'YourTour.store.AjaxStore',
    requires: [
       'YourTour.model.UserModel'
    ],
    config:{
    	model:'YourTour.model.UserModel'
    }
});
