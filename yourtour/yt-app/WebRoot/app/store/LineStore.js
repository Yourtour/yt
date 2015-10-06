Ext.define('YourTour.store.LineStore', {
	extend: 'Ext.data.Store',
    requires: [
       'YourTour.model.LineModel'
    ],
    config:{
    	model:'YourTour.model.LineModel',
    	
    	data:[
			{
		    	rowKey:"1", imageUrl:"resources/images/scene_8.jpg", name:"九寨沟、黄龙、峨眉、乐山",
		     	feature:"迢迢丝绸路，绵绵帕米尔，驼铃摇荡着张骞的风尘，驿站背负着古道的岁月。如此辽阔的原野，如此美丽的风景，驾车出行，将会真正感受到悠远、壮美和自在。南疆太大了，景点又很分散，不知道从哪里开始最好......",
		     	reason:"迢迢丝绸路，绵绵帕米尔，驼铃摇荡着张骞的风尘，驿站背负着古道的岁月。如此辽阔的原野，如此美丽的风景，驾车出行，将会真正感受到悠远、壮美和自在。南疆太大了，景点又很分散，不知道从哪里开始最好......", 
		     	routes:[
			     	{rowKey:"11", imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg", name:"九寨沟、黄龙、峨眉、乐山9日游", period:'9天',feature:'迢迢丝绸路，绵绵帕米尔，驼铃摇荡着张骞的风尘，驿站背负着古道的岁月.....'},
			     	{rowKey:"12", imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg", name:"九寨沟、黄龙、峨眉、乐山8日游", period:'8天',feature:'迢迢丝绸路，绵绵帕米尔，驼铃摇荡着张骞的风尘，驿站背负着古道的岁月......'},
			     	{rowKey:"13", imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg", name:"九寨沟、黄龙、峨眉、乐山7日游", period:'7天',feature:'迢迢丝绸路，绵绵帕米尔，驼铃摇荡着张骞的风尘，驿站背负着古道的岁月......'}
			     ],
			     "resources":[
			     	{rowKey:"11", name:"九寨沟",imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg"},
			     	{rowKey:"12", name:"黄龙",imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg"},
			     	{rowKey:"13", name:"乐山",imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg"},
			     	{rowKey:"14", name:"峨眉",imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg"}
			     ]
		    },
	     
			{
				rowKey:"2", imageUrl:"resources/images/scene_1.jpg", name:"九寨沟、黄龙、峨眉、乐山", 
				feature:"迢迢丝绸路，绵绵帕米尔，驼铃摇荡着张骞的风尘......",
		     	reason:"迢迢丝绸路，绵绵帕米尔，驼铃摇荡着张骞的风尘......",					
				routes:[
   			     	{rowKey:"11", imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg", name:"九寨沟、黄龙、峨眉、乐山9日游", period:'9天',feature:'迢迢丝绸路，绵绵帕米尔，驼铃摇荡着张骞的风尘，驿站背负着古道的岁月......'},
			     	{rowKey:"12", imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg", name:"九寨沟、黄龙、峨眉、乐山8日游", period:'8天',feature:'迢迢丝绸路，绵绵帕米尔，驼铃摇荡着张骞的风尘，驿站背负着古道的岁月......'},
			     	{rowKey:"13", imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg", name:"九寨沟、黄龙、峨眉、乐山7日游", period:'7天',feature:'迢迢丝绸路，绵绵帕米尔，驼铃摇荡着张骞的风尘，驿站背负着古道的岁月......'}
			     ],
			     
			     "resources":[
			     	{rowKey:"11", name:"九寨沟",intro:"九寨沟国家级自然保护区位于四川省阿坝藏族羌族自治州九寨沟县境内，是中国第一个以保护自然风景为主要目的的自然保护区，同时，也是中国著名风景名胜区和全国文明风景旅游区示范点。距离成都市400多千米，是一条纵深50余千米的山沟谷地，总面积64297公顷[1] ，大部分为森林所覆盖。因沟内有树正寨、荷叶寨、则查洼寨等九个藏族村寨坐落在这片高山湖泊群中而得名。",imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg"},
			     	{rowKey:"12", name:"黄龙",intro:"九寨沟国家级自然保护区位于四川省阿坝藏族羌族自治州九寨沟县境内，是中国第一个以保护自然风景为主要目的的自然保护区，同时，也是中国著名风景名胜区和全国文明风景旅游区示范点。距离成都市400多千米，是一条纵深50余千米的山沟谷地，总面积64297公顷[1] ，大部分为森林所覆盖。因沟内有树正寨、荷叶寨、则查洼寨等九个藏族村寨坐落在这片高山湖泊群中而得名。",imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg"},
			     	{rowKey:"13", name:"乐山",intro:"九寨沟国家级自然保护区位于四川省阿坝藏族羌族自治州九寨沟县境内，是中国第一个以保护自然风景为主要目的的自然保护区，同时，也是中国著名风景名胜区和全国文明风景旅游区示范点。距离成都市400多千米，是一条纵深50余千米的山沟谷地，总面积64297公顷[1] ，大部分为森林所覆盖。因沟内有树正寨、荷叶寨、则查洼寨等九个藏族村寨坐落在这片高山湖泊群中而得名。",imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg"},
			     	{rowKey:"14", name:"峨眉",intro:"九寨沟国家级自然保护区位于四川省阿坝藏族羌族自治州九寨沟县境内，是中国第一个以保护自然风景为主要目的的自然保护区，同时，也是中国著名风景名胜区和全国文明风景旅游区示范点。距离成都市400多千米，是一条纵深50余千米的山沟谷地，总面积64297公顷[1] ，大部分为森林所覆盖。因沟内有树正寨、荷叶寨、则查洼寨等九个藏族村寨坐落在这片高山湖泊群中而得名。",imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg"}
			     ]
			},
	     
			{
				rowKey:"3", imageUrl:"resources/images/scene_3.jpg", name:"九寨沟、黄龙、峨眉、乐山", 
				feature:"迢迢丝绸路，绵绵帕米尔，驼铃摇荡着张骞的风尘......",
		     	reason:"迢迢丝绸路，绵绵帕米尔，驼铃摇荡着张骞的风尘......",					
				routes:[
			     	{rowKey:"11", imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg", name:"九寨沟、黄龙、峨眉、乐山9日游", period:'9天',feature:'迢迢丝绸路，绵绵帕米尔，驼铃摇荡着张骞的风尘，驿站背负着古道的岁月......'},
			     	{rowKey:"12", imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg", name:"九寨沟、黄龙、峨眉、乐山8日游", period:'8天',feature:'迢迢丝绸路，绵绵帕米尔，驼铃摇荡着张骞的风尘，驿站背负着古道的岁月......'},
			     	{rowKey:"13", imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg", name:"九寨沟、黄龙、峨眉、乐山7日游", period:'7天',feature:'迢迢丝绸路，绵绵帕米尔，驼铃摇荡着张骞的风尘，驿站背负着古道的岁月......'}
			     ],
			     
			     "resources":[
			     	{rowKey:"11", name:"九寨沟",intro:"九寨沟国家级自然保护区位于四川省阿坝藏族羌族自治州九寨沟县境内，是中国第一个以保护自然风景为主要目的的自然保护区，同时，也是中国著名风景名胜区和全国文明风景旅游区示范点。距离成都市400多千米，是一条纵深50余千米的山沟谷地，总面积64297公顷[1] ，大部分为森林所覆盖。因沟内有树正寨、荷叶寨、则查洼寨等九个藏族村寨坐落在这片高山湖泊群中而得名。",imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg"},
			     	{rowKey:"12", name:"黄龙",intro:"九寨沟国家级自然保护区位于四川省阿坝藏族羌族自治州九寨沟县境内，是中国第一个以保护自然风景为主要目的的自然保护区，同时，也是中国著名风景名胜区和全国文明风景旅游区示范点。距离成都市400多千米，是一条纵深50余千米的山沟谷地，总面积64297公顷[1] ，大部分为森林所覆盖。因沟内有树正寨、荷叶寨、则查洼寨等九个藏族村寨坐落在这片高山湖泊群中而得名。",imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg"},
			     	{rowKey:"13", name:"乐山",intro:"九寨沟国家级自然保护区位于四川省阿坝藏族羌族自治州九寨沟县境内，是中国第一个以保护自然风景为主要目的的自然保护区，同时，也是中国著名风景名胜区和全国文明风景旅游区示范点。距离成都市400多千米，是一条纵深50余千米的山沟谷地，总面积64297公顷[1] ，大部分为森林所覆盖。因沟内有树正寨、荷叶寨、则查洼寨等九个藏族村寨坐落在这片高山湖泊群中而得名。",imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg"},
			     	{rowKey:"14", name:"峨眉",intro:"九寨沟国家级自然保护区位于四川省阿坝藏族羌族自治州九寨沟县境内，是中国第一个以保护自然风景为主要目的的自然保护区，同时，也是中国著名风景名胜区和全国文明风景旅游区示范点。距离成都市400多千米，是一条纵深50余千米的山沟谷地，总面积64297公顷[1] ，大部分为森林所覆盖。因沟内有树正寨、荷叶寨、则查洼寨等九个藏族村寨坐落在这片高山湖泊群中而得名。",imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg"}
			     ]
			},
	     
			{
				rowKey:"4", imageUrl:"resources/images/scene_1.jpg", name:"九寨沟、黄龙、峨眉、乐山", 
				feature:"迢迢丝绸路，绵绵帕米尔，驼铃摇荡着张骞的风尘......",
		     	reason:"迢迢丝绸路，绵绵帕米尔，驼铃摇荡着张骞的风尘......",					
				routes:[
			     	{rowKey:"11", imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg", name:"九寨沟、黄龙、峨眉、乐山9日游", period:'9天',feature:'迢迢丝绸路，绵绵帕米尔，驼铃摇荡着张骞的风尘，驿站背负着古道的岁月。如此辽阔的原野，如此美丽的风景，驾车出行，将会真正感受到悠远、壮美和自在。南疆太大了，景点又很分散，不知道从哪里开始最好......'},
			     	{rowKey:"12", imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg", name:"九寨沟、黄龙、峨眉、乐山8日游", period:'8天',feature:'迢迢丝绸路，绵绵帕米尔，驼铃摇荡着张骞的风尘，驿站背负着古道的岁月。如此辽阔的原野，如此美丽的风景，驾车出行，将会真正感受到悠远、壮美和自在。南疆太大了，景点又很分散，不知道从哪里开始最好......'},
			     	{rowKey:"13", imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg", name:"九寨沟、黄龙、峨眉、乐山7日游", period:'7天',feature:'迢迢丝绸路，绵绵帕米尔，驼铃摇荡着张骞的风尘，驿站背负着古道的岁月。如此辽阔的原野，如此美丽的风景，驾车出行，将会真正感受到悠远、壮美和自在。南疆太大了，景点又很分散，不知道从哪里开始最好......'}
			     ],
			     
			     "resources":[
			     	{rowKey:"11", name:"九寨沟",intro:"九寨沟国家级自然保护区位于四川省阿坝藏族羌族自治州九寨沟县境内，是中国第一个以保护自然风景为主要目的的自然保护区，同时，也是中国著名风景名胜区和全国文明风景旅游区示范点。距离成都市400多千米，是一条纵深50余千米的山沟谷地，总面积64297公顷[1] ，大部分为森林所覆盖。因沟内有树正寨、荷叶寨、则查洼寨等九个藏族村寨坐落在这片高山湖泊群中而得名。",imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg"},
			     	{rowKey:"12", name:"黄龙",intro:"九寨沟国家级自然保护区位于四川省阿坝藏族羌族自治州九寨沟县境内，是中国第一个以保护自然风景为主要目的的自然保护区，同时，也是中国著名风景名胜区和全国文明风景旅游区示范点。距离成都市400多千米，是一条纵深50余千米的山沟谷地，总面积64297公顷[1] ，大部分为森林所覆盖。因沟内有树正寨、荷叶寨、则查洼寨等九个藏族村寨坐落在这片高山湖泊群中而得名。",imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg"},
			     	{rowKey:"13", name:"乐山",intro:"九寨沟国家级自然保护区位于四川省阿坝藏族羌族自治州九寨沟县境内，是中国第一个以保护自然风景为主要目的的自然保护区，同时，也是中国著名风景名胜区和全国文明风景旅游区示范点。距离成都市400多千米，是一条纵深50余千米的山沟谷地，总面积64297公顷[1] ，大部分为森林所覆盖。因沟内有树正寨、荷叶寨、则查洼寨等九个藏族村寨坐落在这片高山湖泊群中而得名。",imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg"},
			     	{rowKey:"14", name:"峨眉",intro:"九寨沟国家级自然保护区位于四川省阿坝藏族羌族自治州九寨沟县境内，是中国第一个以保护自然风景为主要目的的自然保护区，同时，也是中国著名风景名胜区和全国文明风景旅游区示范点。距离成都市400多千米，是一条纵深50余千米的山沟谷地，总面积64297公顷[1] ，大部分为森林所覆盖。因沟内有树正寨、荷叶寨、则查洼寨等九个藏族村寨坐落在这片高山湖泊群中而得名。",imageUrls:"resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg;resources/images/scene_8.jpg"}
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
