Ext.define('YourTour.store.LocalStore', {
    extend: 'Ext.data.Store',
    
    config:{
    	fields: ['key', 'value'],
    	
    	 proxy: {
	        type: 'localstorage',
	        id  : 'YTKey'
	    }
    }
});
