Ext.define('YourTour.store.UserAccountStore', {
	extend: 'Ext.data.Store',
    requires: [
       'YourTour.model.UserAccountModel'
    ],
    config:{
    	model:'YourTour.model.UserAccountModel',
    	
    	proxy:{
	    	api:{
				 create: YourTour.util.Context.getContext('/acount/register')
			}
    	}
    }
});
