Ext.define('YourTour.store.LineStore', {
	extend: 'YourTour.store.AjaxStore',
    requires: [
       'YourTour.model.LineModel'
    ],
    config:{
    	model:'YourTour.model.LineModel',
    	
    	/*data:[
			{lineId:'1', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/scene_8.jpg", name:"九寨沟、黄龙、峨眉、乐山", users:[{userId:'11', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下1"},{userId:'12', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下2"}]},
			{lineId:'2', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/scene_1.jpg", name:"九寨沟、黄龙、峨眉、乐山", users:[{userId:'21', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下"},{userId:'22', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下"}]},
			{lineId:'3', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/scene_3.jpg", name:"九寨沟、黄龙、峨眉、乐山", users:[{userId:'11', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下1"},{userId:'12', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下2"}]},
			{lineId:'4', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/scene_8.jpg", name:"九寨沟、黄龙、峨眉、乐山", users:[{userId:'21', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下"},{userId:'22', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下"}]},
			{lineId:'5', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/scene_1.jpg", name:"九寨沟、黄龙、峨眉、乐山", users:[{userId:'11', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下1"},{userId:'12', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下2"}]},
			{lineId:'6', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/scene_3.jpg", name:"九寨沟、黄龙、峨眉、乐山", users:[{userId:'21', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下"},{userId:'22', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下"}]},
			{lineId:'7', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/scene_8.jpg", name:"九寨沟、黄龙、峨眉、乐山", users:[{userId:'11', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下1"},{userId:'12', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下2"}]},
			{lineId:'8', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/scene_1.jpg", name:"九寨沟、黄龙、峨眉、乐山", users:[{userId:'21', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下"},{userId:'22', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下"}]},
			{lineId:'9', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/scene_3.jpg", name:"九寨沟、黄龙、峨眉、乐山", users:[{userId:'11', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下1"},{userId:'12', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下2"}]},
			{lineId:'10', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/scene_8.jpg", name:"九寨沟、黄龙、峨眉、乐山", users:[{userId:'21', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下"},{userId:'22', imageUrl:"http://192.168.2.102:8080/yt-app/resources/images/user/user.jpg", nickName:"行走天下"}]}
    	]*/
    	proxy:{
	    	api:{
				 read: YourTour.util.Context.getContext('/line/Match.action'),
				 create: YourTour.util.Context.getContext('/route/Save.action'),
				 update: YourTour.util.Context.getContext('/route/Update.action'),
				 destroy: YourTour.util.Context.getContext('/route/Delete.action')
			}
    	}
    },
});
