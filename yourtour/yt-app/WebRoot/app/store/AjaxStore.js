Ext.define('YourTour.store.AjaxStore', {
    extend: 'Ext.data.Store',
    config:{
    	useDefaultXhrHeader: false,
    	
	    proxy:{
			type: 'ajax',
			noCache: false,
			actionMethods : 'GET',
		    
			reader:{
				type:'json',
				rootProperty:'data'
			},
			
			headers:{
				//user_id:YourTour.util.Context.getUserId()
			}
	    }
    }
});
