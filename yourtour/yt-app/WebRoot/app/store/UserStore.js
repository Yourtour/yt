Ext.define('YourTour.store.UserStore', {
	extend: 'Ext.data.Store',
    requires: [
       'YourTour.model.UserModel'
    ],
    config:{
    	model:'YourTour.model.UserModel',
    	
    	data:[
			{
		    	rowKey:"1", imageUrl:"resources/images/member_logo_64.png", nickname:"清澈的月光",rank:'金钻',
		     	role:'达人',sex:"30",age:"F",slogan:'旅行对我来说，是恢复青春活力的源泉。'
		    },
		    {
		    	rowKey:"2", imageUrl:"resources/images/member_logo_64.png", nickname:"清澈的月光",rank:'银钻',
		     	role:'达人',sex:"30",age:"F",slogan:'旅行对我来说，是恢复青春活力的源泉。'
		    },
		    {
		    	rowKey:"3", imageUrl:"resources/images/member_logo_64.png", nickname:"清澈的月光",rank:'金钻',
		     	role:'达人',sex:"30",age:"F",slogan:'旅游使智者更智，愚者更昧。'
		    },
		    {
		    	rowKey:"4", imageUrl:"resources/images/member_logo_64.png", nickname:"清澈的月光",rank:'银钻',
		     	role:'达人',sex:"30",age:"F",slogan:'旅行是真正的知识最伟大的发源地。'
		    },
		    {
		    	rowKey:"5", imageUrl:"resources/images/member_logo_64.png", nickname:"清澈的月光",rank:'金钻',
		     	role:'驴友',sex:"30",age:"F",slogan:'旅行对我来说，是恢复青春活力的源泉。'
		    },
		    {
		    	rowKey:"6", imageUrl:"resources/images/member_logo_64.png", nickname:"清澈的月光",rank:'普通',
		     	role:'驴友',sex:"30",age:"F",slogan:'旅游使智者更智，愚者更昧。。'
		    },
		    {
		    	rowKey:"7", imageUrl:"resources/images/member_logo_64.png", nickname:"清澈的月光",rank:'金钻',
		     	role:'驴友',sex:"30",age:"F",slogan:'行路多者见识多'
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
