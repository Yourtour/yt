Ext.define('YourTour.store.MessageContentStore', {
	extend: 'Ext.data.Store',
    requires: [
       'YourTour.model.MessageContentModel'
    ],
    config:{
    	model:'YourTour.model.MessageContentModel',
    }
});
