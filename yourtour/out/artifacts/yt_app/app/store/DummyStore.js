Ext.define('YourTour.store.DummyStore', {
    extend: 'Ext.data.Store',
    config:{
    	useDefaultXhrHeader: false,
    	autoLoad: false,
	    proxy:{
			type: 'ajax',
			useDefaultXhrHeader:false,
			noCache: false,
			reader:{
				type:'json',
				rootProperty:'data'
			}
	    }
    }
});
