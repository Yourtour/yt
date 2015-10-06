Ext.define('YourTour.store.BestListStore', {
	extend: 'Ext.data.Store',
    requires: [
       'YourTour.model.LineModel'
    ],
    config:{
    	model:'YourTour.model.LineModel',
    	data:[
			{rowKey:"10", category:'一月', imageUrl:"resources/images/scene_0.jpg",name:'东方明珠1', rank:'线路综合排名第三名', rankScore:'4.5', shareNum:'300', thumbupNum:'300', favoriteNum:300, commentNum:300},
			{rowKey:"11", category:'一月', imageUrl:"resources/images/scene_0.jpg",name:'东方明珠2', rank:'线路综合排名第三名', rankScore:'4.5', shareNum:'300', thumbupNum:'300', favoriteNum:300, commentNum:300},
			{rowKey:"12", category:'二月', imageUrl:"resources/images/scene_1.jpg",name:'上海"东方明珠" + "船游黄浦江" + "外滩"', rank:'线路综合排名第三名', ratyScore:'4.5', shareNum:'300', thumbupNum:'300', favoriteNum:300, commentNum:300},
			{rowKey:"13", category:'三月', imageUrl:"resources/images/scene_3.jpg",name:'泰晤士小镇', rank:'线路综合排名第三名', rankScore:'4.5', shareNum:'300', thumbupNum:'300', favoriteNum:300, commentNum:300},
			{rowKey:"14", category:'四月', imageUrl:"resources/images/scene_0.jpg",name:'广富林遗址', rank:'线路综合排名第三名', rankScore:'4.5', shareNum:'300', thumbupNum:'300', favoriteNum:300, commentNum:300}
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
