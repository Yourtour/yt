Ext.define('YourTour.store.ResourceStore', {
    extend: 'YourTour.store.AjaxStore',
    requires: [
       'YourTour.model.ResourceModel'
    ],
    config:{
    	model:'YourTour.model.ResourceModel'
    }
});
