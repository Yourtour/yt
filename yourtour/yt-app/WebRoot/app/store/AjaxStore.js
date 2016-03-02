Ext.define('YourTour.store.AjaxStore', {
    extend: 'Ext.data.Store',
    config:{
    	autoLoad: false,
    	loaded:false,
	    proxy:{
			type: 'ajax',
			noCache: false,
			reader:{
				type:'json',
				rootProperty:'data'
			}
	    }
    }
});
