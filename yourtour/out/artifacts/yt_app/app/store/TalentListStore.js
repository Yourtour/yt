Ext.define('YourTour.store.TalentListStore', {
	extend: 'Ext.data.Store',
    requires: [
       'YourTour.model.TalentModel'
    ],
    config:{
    	model:'YourTour.model.TalentModel',
    	data:[
			{rowKey:"11", imageUrl:'resources/images/member_logo_64.png', nickname:"李美旋猫女111",sex:'F',tag1:'历史达人',tag2:'服务周到'},
	     	{rowKey:"12", imageUrl:'resources/images/member_logo_64.png', nickname:"刘德华222",sex:'M',tag1:'美食达人',tag2:'幽默风趣'},
	     	{rowKey:"13", imageUrl:'resources/images/member_logo_64.png', nickname:"张学友333",sex:'M',tag1:'行程达人',tag2:'当地通'}
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
