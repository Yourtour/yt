Ext.define('YourTour.store.PlaceStore', {
	extend: 'Ext.data.Store',
    requires: [
       'YourTour.model.PlaceModel'
    ],
    config:{
    	model:'YourTour.model.PlaceModel',
    	
    	data:[
			{rowKey:'1', parent:'1', name:'安徽', cityCount:'62', cities:[{rowKey:'1.1', name:'黄山'},{rowKey:'1.2', name:'九华山'},{rowKey:'1.3', name:'合肥'},{rowKey:'1.4', name:'大别山'}]},
			{rowKey:'2', parent:'1', name:'北京', cityCount:'0'},
			{rowKey:'3', parent:'1', name:'重庆', cityCount:'0'},
			{rowKey:'4', parent:'1', name:'福建', cityCount:'62'}
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
