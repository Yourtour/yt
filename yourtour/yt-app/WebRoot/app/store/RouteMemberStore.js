Ext.define('YourTour.store.UserStore', {
	extend: 'YourTour.store.AjaxStore',
    requires: [
       'YourTour.model.UserModel'
    ],
    config:{
    	model:'YourTour.model.UserModel'
    }
});
