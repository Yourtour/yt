Ext.define('YourTour.store.BestListStore', {
	extend: 'Ext.data.Store',
    requires: [
       'YourTour.model.BestModel'
    ],
    config:{
    	model:'YourTour.model.BestModel',
    	data:[
			{rowKey:"10", category:'一月', imageUrl:"resources/images/scene_0.jpg",name:'东方明珠1'},
			{rowKey:"11", category:'一月', imageUrl:"resources/images/scene_0.jpg",name:'东方明珠2'},
			{rowKey:"12", category:'二月', imageUrl:"resources/images/scene_1.jpg",name:'上海"东方明珠" + "船游黄浦江" + "外滩"'},
			{rowKey:"13", category:'三月', imageUrl:"resources/images/scene_3.jpg",name:'泰晤士小镇'},
			{rowKey:"14", category:'四月', imageUrl:"resources/images/scene_0.jpg",name:'广富林遗址'}
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
