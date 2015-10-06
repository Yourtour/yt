Ext.define('YourTour.store.CommentStore', {
	extend: 'Ext.data.Store',
    requires: [
       'YourTour.model.CommentModel'
    ],
    config:{
    	model:'YourTour.model.CommentModel',
    	
    	/*proxy:{
	    	api:{
				 read: YourTour.util.Context.getContext('/line/Match.action'),
				 create: YourTour.util.Context.getContext('/route/Save.action'),
				 update: YourTour.util.Context.getContext('/route/Update.action'),
				 destroy: YourTour.util.Context.getContext('/route/Delete.action')
			}
    	}*/
    }
});
