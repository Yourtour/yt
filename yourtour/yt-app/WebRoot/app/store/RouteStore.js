Ext.define('YourTour.store.RouteStore', {
    extend: 'YourTour.store.AjaxStore',
    requires: [
       'YourTour.model.RouteModel'
    ],
    config:{
    	model:'YourTour.model.RouteModel',
    }
});
