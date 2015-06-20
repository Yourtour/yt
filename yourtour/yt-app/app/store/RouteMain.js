Ext.define('YourTour.store.RouteMain', {
    extend: 'YourTour.store.BaseStore',
    requires: [
       'YourTour.model.RouteMain'
    ],
    config:{
    	model:'YourTour.model.RouteMain',
    	
    	proxy:{
    		type:'ajax',
    		reader:{
    			type:'json',
    			rootProperty:''
    		}	
    	}
    },
});
