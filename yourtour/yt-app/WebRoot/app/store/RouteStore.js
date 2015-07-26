Ext.define('YourTour.store.RouteStore', {
    extend: 'Ext.data.Store',
    requires: [
       'YourTour.model.RouteModel'
    ],
    config:{
    	model:'YourTour.model.RouteModel',
    	autoLoad:true,
    	
    	data:[
    		{
    			rowKey:"1",imageUrl:"resources/images/guangdong.jpg", name:"九寨沟、黄龙、峨眉、乐山双飞7日",lineName:"九寨沟、黄龙、峨眉、乐山", feeling:"旅行并不仅仅是你到了一个景点，在那儿拍照留念，当时的天气，当时空气中花粉的味道，还有当时你是否是气喘吁吁汗流浃背，这些都构成了你旅行的一部分，你整个的身体是在参与的，而不仅仅是拍照和看到",startTime:"2016-01-01", endTime:"2016-01-10", period:"10",
    			schedules:[
    				{rowKey:'1', type:'prepare', name:'游前准备'},
    				{rowKey:'11',type:'prepareItem', name:'制定详细行程计划'},
    				{rowKey:'12',type:'prepareItem', name:'根据天气预报选择服装，如有游泳项目可带泳衣', isLast:'1'},
    				{rowKey:'2', type:'day', name:'第一天',lines:'出发地-成都'},
    				{rowKey:'21',type:'traffic', name:'行驶时间',desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺,全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺,全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺,全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺,全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时'},
    				{rowKey:'22',type:'food', name:'午餐时间-叠溪羌家十三花生态餐 ',resName:'叠溪羌家十三花生态餐 ',imageUrl:'resources/images/food.jpg', desc:'乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,',time:'11:30-12:30', period:'约1个小时', isLast:'1'},
    				{rowKey:'23',type:'hotel', name:'返回宾馆-锦江之星',resName:'锦江之星', imageUrl:'resources/images/resident.jpg', desc:'乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,',time:'11:30-12:30', period:'约1个小时', isLast:'1'},
    				{rowKey:'3', type:'day', name:'第二天',lines:'出发地-成都'},
    				{rowKey:'30',type:'scene',  name:'九寨沟', resName:'黄龙风景名胜区', imageUrl:'resources/images/scene_0.jpg', desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时'},
    				{rowKey:'31',type:'traffic', name:'行驶时间',desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时', isLast:'1'},
    				{rowKey:'4', type:'day', name:'第三天',lines:'出发地-成都'},
    				{rowKey:'41',type:'traffic', name:'行驶时间',desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时'},
    				{rowKey:'42',type:'food', name:'午餐时间',desc:'乡村农家乐，美味乡间小菜',time:'11:30-12:30', period:'约1个小时', isLast:'1'},
    				{rowKey:'5', type:'day', name:'第四天',lines:'出发地-成都'},
    				{rowKey:'51',type:'traffic', name:'行驶时间',desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时'},
    				{rowKey:'52',type:'food', name:'午餐时间',desc:'乡村农家乐，美味乡间小菜',time:'11:30-12:30', period:'约1个小时', isLast:'1'},
    				{rowKey:'6', type:'day', name:'第五天',lines:'出发地-成都'},
    				{rowKey:'61',type:'traffic', name:'行驶时间',desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时'},
    				{rowKey:'62',type:'food', name:'午餐时间',desc:'乡村农家乐，美味乡间小菜',time:'11:30-12:30', period:'约1个小时', isLast:'1'}
    			]
    		},
    		
    		{
    			rowKey:"2",imageUrl:"resources/images/beijing.jpg", name:"九寨沟、黄龙、峨眉、乐山双飞7日",lineName:"九寨沟、黄龙、峨眉、乐山", feeling:"旅行并不仅仅是你到了一个景点，在那儿拍照留念，当时的天气，当时空气中花粉的味道，还有当时你是否是气喘吁吁汗流浃背，这些都构成了你旅行的一部分，你整个的身体是在参与的，而不仅仅是拍照和看到",startTime:"2016-01-01", endTime:"2016-01-10", period:"10",
    			schedules:[
    				{rowKey:'1', type:'prepare', name:'游前准备'},
    				{rowKey:'11',type:'prepareItem', name:'制定详细行程计划'},
    				{rowKey:'12',type:'prepareItem', name:'根据天气预报选择服装，如有游泳项目可带泳衣', isLast:'1'},
    				{rowKey:'2', type:'day', name:'第一天',lines:'出发地-成都'},
    				{rowKey:'21',type:'traffic', name:'行驶时间',desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺,全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺,全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺,全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺,全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时'},
    				{rowKey:'22',type:'food', name:'午餐时间-叠溪羌家十三花生态餐 ',imageUrl:'resources/images/food.jpg', desc:'乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,',time:'11:30-12:30', period:'约1个小时', isLast:'1'},
    				{rowKey:'23',type:'hotel', name:'返回宾馆-锦江之星',imageUrl:'resources/images/resident.jpg', desc:'乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,',time:'11:30-12:30', period:'约1个小时', isLast:'1'},
    				{rowKey:'3', type:'day', name:'第二天',lines:'出发地-成都'},
    				{rowKey:'30',type:'scene',  name:'九寨沟', resName:'黄龙风景名胜区', imageUrl:'resources/images/scene_0.jpg', desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时'},
    				{rowKey:'31',type:'traffic', name:'行驶时间',desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时', isLast:'1'},
    				{rowKey:'4', type:'day', name:'第三天',lines:'出发地-成都'},
    				{rowKey:'41',type:'traffic', name:'行驶时间',desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时'},
    				{rowKey:'42',type:'food', name:'午餐时间',desc:'乡村农家乐，美味乡间小菜',time:'11:30-12:30', period:'约1个小时', isLast:'1'},
    				{rowKey:'5', type:'day', name:'第四天',lines:'出发地-成都'},
    				{rowKey:'51',type:'traffic', name:'行驶时间',desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时'},
    				{rowKey:'52',type:'food', name:'午餐时间',desc:'乡村农家乐，美味乡间小菜',time:'11:30-12:30', period:'约1个小时', isLast:'1'},
    				{rowKey:'6', type:'day', name:'第五天',lines:'出发地-成都'},
    				{rowKey:'61',type:'traffic', name:'行驶时间',desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时'},
    				{rowKey:'62',type:'food', name:'午餐时间',desc:'乡村农家乐，美味乡间小菜',time:'11:30-12:30', period:'约1个小时', isLast:'1'}
    			]
    		},
    		{
    			rowKey:"3",imageUrl:"resources/images/shanghai.jpg", name:"九寨沟、黄龙、峨眉、乐山双飞7日",lineName:"九寨沟、黄龙、峨眉、乐山", feeling:"旅行并不仅仅是你到了一个景点，在那儿拍照留念，当时的天气，当时空气中花粉的味道，还有当时你是否是气喘吁吁汗流浃背，这些都构成了你旅行的一部分，你整个的身体是在参与的，而不仅仅是拍照和看到",startTime:"2016-01-01", endTime:"2016-01-10", period:"10",
    			schedules:[
    				{rowKey:'1', type:'prepare', name:'游前准备'},
    				{rowKey:'11',type:'prepareItem', name:'制定详细行程计划'},
    				{rowKey:'12',type:'prepareItem', name:'根据天气预报选择服装，如有游泳项目可带泳衣', isLast:'1'},
    				{rowKey:'2', type:'day', name:'第一天',lines:'出发地-成都'},
    				{rowKey:'21',type:'traffic', name:'行驶时间',desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺,全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺,全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺,全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺,全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时'},
    				{rowKey:'22',type:'food', name:'午餐时间-叠溪羌家十三花生态餐 ',imageUrl:'resources/images/food.jpg', desc:'乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,',time:'11:30-12:30', period:'约1个小时', isLast:'1'},
    				{rowKey:'23',type:'hotel', name:'返回宾馆-锦江之星',imageUrl:'resources/images/resident.jpg', desc:'乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,',time:'11:30-12:30', period:'约1个小时', isLast:'1'},
    				{rowKey:'3', type:'day', name:'第二天',lines:'出发地-成都'},
    				{rowKey:'30',type:'scene',  name:'九寨沟', resName:'黄龙风景名胜区', imageUrl:'resources/images/scene_0.jpg', desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时'},
    				{rowKey:'31',type:'traffic', name:'行驶时间',desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时', isLast:'1'},
    				{rowKey:'4', type:'day', name:'第三天',lines:'出发地-成都'},
    				{rowKey:'41',type:'traffic', name:'行驶时间',desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时'},
    				{rowKey:'42',type:'food', name:'午餐时间',desc:'乡村农家乐，美味乡间小菜',time:'11:30-12:30', period:'约1个小时', isLast:'1'},
    				{rowKey:'5', type:'day', name:'第四天',lines:'出发地-成都'},
    				{rowKey:'51',type:'traffic', name:'行驶时间',desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时'},
    				{rowKey:'52',type:'food', name:'午餐时间',desc:'乡村农家乐，美味乡间小菜',time:'11:30-12:30', period:'约1个小时', isLast:'1'},
    				{rowKey:'6', type:'day', name:'第五天',lines:'出发地-成都'},
    				{rowKey:'61',type:'traffic', name:'行驶时间',desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时'},
    				{rowKey:'62',type:'food', name:'午餐时间',desc:'乡村农家乐，美味乡间小菜',time:'11:30-12:30', period:'约1个小时', isLast:'1'}
    			]
    		},
    		{
    			rowKey:"4",imageUrl:"resources/images/yunnan.jpg", name:"九寨沟、黄龙、峨眉、乐山双飞7日",lineName:"九寨沟、黄龙、峨眉、乐山", feeling:"旅行并不仅仅是你到了一个景点，在那儿拍照留念，当时的天气，当时空气中花粉的味道，还有当时你是否是气喘吁吁汗流浃背，这些都构成了你旅行的一部分，你整个的身体是在参与的，而不仅仅是拍照和看到",startTime:"2016-01-01", endTime:"2016-01-10", period:"10",
    			schedules:[
    				{rowKey:'1', type:'prepare', name:'游前准备'},
    				{rowKey:'11',type:'prepareItem', name:'制定详细行程计划'},
    				{rowKey:'12',type:'prepareItem', name:'根据天气预报选择服装，如有游泳项目可带泳衣', isLast:'1'},
    				{rowKey:'2', type:'day', name:'第一天',lines:'出发地-成都'},
    				{rowKey:'21',type:'traffic', name:'行驶时间',desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺,全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺,全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺,全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺,全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时'},
    				{rowKey:'22',type:'food', name:'午餐时间-叠溪羌家十三花生态餐 ',imageUrl:'resources/images/food.jpg', desc:'乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,',time:'11:30-12:30', period:'约1个小时', isLast:'1'},
    				{rowKey:'23',type:'hotel', name:'返回宾馆-锦江之星',imageUrl:'resources/images/resident.jpg', desc:'乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,乡村农家乐，美味乡间小菜,',time:'11:30-12:30', period:'约1个小时', isLast:'1'},
    				{rowKey:'3', type:'day', name:'第二天',lines:'出发地-成都'},
    				{rowKey:'30',type:'scene',  name:'九寨沟', resName:'黄龙风景名胜区', imageUrl:'resources/images/scene_0.jpg', desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时'},
    				{rowKey:'31',type:'traffic', name:'行驶时间',desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时', isLast:'1'},
    				{rowKey:'4', type:'day', name:'第三天',lines:'出发地-成都'},
    				{rowKey:'41',type:'traffic', name:'行驶时间',desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时'},
    				{rowKey:'42',type:'food', name:'午餐时间',desc:'乡村农家乐，美味乡间小菜',time:'11:30-12:30', period:'约1个小时', isLast:'1'},
    				{rowKey:'5', type:'day', name:'第四天',lines:'出发地-成都'},
    				{rowKey:'51',type:'traffic', name:'行驶时间',desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时'},
    				{rowKey:'52',type:'food', name:'午餐时间',desc:'乡村农家乐，美味乡间小菜',time:'11:30-12:30', period:'约1个小时', isLast:'1'},
    				{rowKey:'6', type:'day', name:'第五天',lines:'出发地-成都'},
    				{rowKey:'61',type:'traffic', name:'行驶时间',desc:'全体集合发车，沿途经过成都-都江堰-汶川-茂县-松潘-川主寺',time:'6:00-11:00', period:'约5个小时'},
    				{rowKey:'62',type:'food', name:'午餐时间',desc:'乡村农家乐，美味乡间小菜',time:'11:30-12:30', period:'约1个小时', isLast:'1'}
    			]
    		}
    	]
    	/*proxy:{
	    	api:{
				 read: YourTour.util.Context.getContext('/route/user/Query'),
				 create: YourTour.util.Context.getContext('/route/Save'),
				 update: YourTour.util.Context.getContext('/route/Update'),
				 destroy: YourTour.util.Context.getContext('/route/Delete')
			}
    	}*/
    },
});
