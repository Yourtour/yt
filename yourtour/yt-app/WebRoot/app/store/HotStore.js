Ext.define('YourTour.store.PlaceStore', {
	extend: 'Ext.data.TreeStore',
    requires: [
       'YourTour.model.TreeModel'
    ],
    xtype:'hotStore',
    config:{
    	model:'YourTour.model.TreeModel',
    	
    	data:[
			{
		    	rowKey:"1", name:'上海',liveNum:'2000', chatNum:'2000',
		    	lives:[
		    		{rowKey:"11", lineName:"九寨沟、黄龙、峨眉、乐山1"},
			     	{rowKey:"12", lineName:"九寨沟、黄龙、峨眉、乐山2"},
			     	{rowKey:"13", lineName:"九寨沟、黄龙、峨眉、乐山3"}
		    	],
		    	
		     	chats:[
			     	{rowKey:"11", imageUrl:"resources/images/user_logo_32.png",content:'上海哪儿好玩？'},
			     	{rowKey:"12", imageUrl:"resources/images/user_logo_32.png",content:'上海最近天气怎么样？'},
			     	{rowKey:"13", imageUrl:"resources/images/user_logo_32.png",content:'上海哪儿有好吃的？'}
			    ],
			    
			    alongs:[
			     	{rowKey:"11", memberImageUrl:'resources/images/user_logo_32.png', imageUrl:"resources/images/scene_64.jpg",title:'上海哪儿好玩？',intention:'拼车',deadline:'2015-10-12',alongNum:'3'},
			     	{rowKey:"12", memberImageUrl:'resources/images/user_logo_32.png', imageUrl:"resources/images/scene_64.jpg",title:'上海最近天气怎么样？',intention:'拼车',deadline:'2015-10-12',alongNum:'3'}
			    ],
			    
			    talents:[
			     	{rowKey:"11", imageUrl:'resources/images/user_logo_32.png', nickname:"李美旋猫女",sex:'F',tag1:'历史达人',tag2:'服务周到'},
			     	{rowKey:"12", imageUrl:'resources/images/user_logo_32.png', nickname:"刘德华",sex:'M',tag1:'美食达人',tag2:'幽默风趣'},
			     	{rowKey:"13", imageUrl:'resources/images/user_logo_32.png', nickname:"张学友",sex:'M',tag1:'行程达人',tag2:'当地通'}
			    ]
		    }
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
