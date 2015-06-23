Ext.define('YourTour.store.AjaxStore', {
    extend: 'Ext.data.Store',
    
    config:{
	    proxy:{
			type: 'ajax',
			noCache: false,
			actionMethods : 'POST',
		    
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
