Ext.define('YourTour.store.PlaceListStore', {
	extend: 'Ext.data.Store',
    requires: [
       'YourTour.model.PlaceModel'
    ],
    config:{
    	model:'YourTour.model.PlaceModel',
    	sorters:'ename',
    	getGroupString:function(record){
    		return record.get('ename');
    	},
    	
    	data:[
			{}
    	]
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
