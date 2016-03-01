Ext.define('YourTour.store.MessageSessionStore', {
	extend: 'Ext.data.Store',
    requires: [
       'YourTour.model.MessageSessionModel'
    ],
    config:{
    	model:'YourTour.model.MessageSessionModel',
    }
});
