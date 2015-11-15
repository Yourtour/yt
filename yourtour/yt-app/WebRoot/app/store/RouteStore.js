Ext.define('YourTour.store.RouteStore', {
    extend: 'YourTour.store.AjaxStore',
    requires: [
       'YourTour.model.RouteModel'
    ],
    config:{
    	model:'YourTour.model.RouteModel',
    	
    	/*data:[
    		{
    			rowKey:"1",imageUrl:"resources/images/guangdong.jpg", name:"九寨沟、黄龙、峨眉、乐山双飞7日",lineName:"九寨沟、黄龙、峨眉、乐山,九寨沟、黄龙、峨眉、乐山,九寨沟、黄龙、峨眉、乐山",impression:"我的记忆中，去游过许多地方，有水韵江南的古镇，有天下之幽的青城，有险峻天堑的华山，还有彩云之南的大理，还有……很多留下脚印的地方且不一一枚举了。然而春秋两季的九寨沟之行，着实让我彻彻底底的为大自然的神工巧斧，创作出这样童话般的杰作倍为感叹！",startTime:"2016-01-01", endTime:"2016-01-10", period:"10",
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
    			rowKey:"2",imageUrl:"resources/images/beijing.jpg", name:"九寨沟、黄龙、峨眉、乐山双飞7日",lineName:"九寨沟、黄龙、峨眉、乐山", impression:"我的记忆中，去游过许多地方，有水韵江南的古镇，有天下之幽的青城，有险峻天堑的华山，还有彩云之南的大理，还有……很多留下脚印的地方且不一一枚举了。然而春秋两季的九寨沟之行，着实让我彻彻底底的为大自然的神工巧斧，创作出这样童话般的杰作倍为感叹！",startTime:"2016-01-01", endTime:"2016-01-10", period:"10",
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
    			rowKey:"3",imageUrl:"resources/images/shanghai.jpg", name:"九寨沟、黄龙、峨眉、乐山双飞7日",lineName:"九寨沟、黄龙、峨眉、乐山", impression:"我的记忆中，去游过许多地方，有水韵江南的古镇，有天下之幽的青城，有险峻天堑的华山，还有彩云之南的大理，还有……很多留下脚印的地方且不一一枚举了。然而春秋两季的九寨沟之行，着实让我彻彻底底的为大自然的神工巧斧，创作出这样童话般的杰作倍为感叹！",startTime:"2016-01-01", endTime:"2016-01-10", period:"10",
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
    			rowKey:"4",imageUrl:"resources/images/yunnan.jpg", name:"九寨沟、黄龙、峨眉、乐山双飞7日",lineName:"九寨沟、黄龙、峨眉、乐山", impression:"我的记忆中，去游过许多地方，有水韵江南的古镇，有天下之幽的青城，有险峻天堑的华山，还有彩云之南的大理，还有……很多留下脚印的地方且不一一枚举了。然而春秋两季的九寨沟之行，着实让我彻彻底底的为大自然的神工巧斧，创作出这样童话般的杰作倍为感叹！",startTime:"2016-01-01", endTime:"2016-01-10", period:"10",
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
    	]*/
    }
});
