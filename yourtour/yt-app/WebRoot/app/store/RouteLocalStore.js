Ext.define('YourTour.store.RouteLocalStore', {
    extend: 'Ext.data.Store',
    requires:['Ext.data.proxy.LocalStorage'],
    config:{
	    model: "YourTour.model.RouteModel",
	    proxy: {
	        type: 'localstorage',
	        id  : 'RouteKey'
	    }
    }
});
