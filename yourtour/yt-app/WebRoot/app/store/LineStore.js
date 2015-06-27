Ext.define('YourTour.store.LineStore', {
    extend: 'Ext.data.Store',
    requires: [
       'YourTour.model.LineModel',
       'YourTour.model.UserModel'
    ],
    config:{
    	model:'YourTour.model.LineModel',
    	
    	data:[
			{imageUrl:"http://192.168.2.103:8080/yt-app/resources/images/guangdong.jpg", name:"九寨沟、黄龙、峨眉、乐山", users:[{imageUrl:"http://192.168.2.103:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下"},{imageUrl:"http://192.168.2.103:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下"},{imageUrl:"http://192.168.2.103:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下"}]},
			{imageUrl:"http://192.168.2.103:8080/yt-app/resources/images/shanghai.jpg", name:"九寨沟、黄龙、峨眉、乐山", users:[{imageUrl:"http://192.168.2.103:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下"},{imageUrl:"http://192.168.2.103:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下"},{imageUrl:"http://192.168.2.103:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下"}]}
    	]
    	/*proxy:{
	    	api:{
				 read: YourTour.util.Context.getContext('/line/Match.action'),
				 create: YourTour.util.Context.getContext('/route/Save.action'),
				 update: YourTour.util.Context.getContext('/route/Update.action'),
				 destroy: YourTour.util.Context.getContext('/route/Delete.action')
			}
    	}*/
    },
});
