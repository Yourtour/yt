Ext.define('YourTour.store.ResourceSelectionStore', {
    extend: 'Ext.data.Store',
    requires: [
       'YourTour.model.ResourceModel'
    ],
    config:{
    	model:'YourTour.model.ResourceModel',
    	autoLoad:true,
    	
    	data:[
    		{
    			rowKey:"1",imageUrl:"resources/images/guangdong.jpg", name:"九寨沟风景名胜区",
    			address:'四川省阿坝藏族羌族自治州九寨沟县漳扎镇九寨沟风景区',phone:'012345678',
    			intro:'九寨沟国家级自然保护区位于四川省阿坝藏族羌族自治州九寨沟县境内，是中国第一个以保护自然风景为主要目的的自然保护区，同时，也是中国著名风景名胜区和全国文明风景旅游区示范点。距离成都市400多千米，是一条纵深50余千米的山沟谷地，总面积64297公顷[1] ，大部分为森林所覆盖。因沟内有树正寨、荷叶寨、则查洼寨等九个藏族村寨坐落在这片高山湖泊群中而得名。',
    			open:'九寨沟：08:00-18:00（16:00停止取票）',
    			price:'成人票:220元；九寨沟观光车成人票：90元',
    			promotion:'A.免票政策：身高1.2米（含）以下的儿童享受门票和观光车票免票；70周岁（含）以上老年人、现役军人、离休干部和残疾人凭有效身份证件免景区大门票，需另购全价观光车票。\n B.优惠政策：6周岁（不含)-18周岁（含）未成年人、全日制大学本科及以下学历学生、持《港澳居民来往内地通行证》、《台湾居民来往大陆通行证》或学生证等有效证件的香港、澳门、台湾等入境游青少年、60（含）至70周岁（不含）的老年人、持省外省级宗教团体统一印发的皈依证等有效证件的同一宗教信教群众持有效身份证件购景区优惠大门票，需另购全价观光车票。'
    		}
    	]
    	/*proxy:{
	    	api:{
				 read: YourTour.util.Context.getContext('/route/user/Query.action'),
				 create: YourTour.util.Context.getContext('/route/Save.action'),
				 update: YourTour.util.Context.getContext('/route/Update.action'),
				 destroy: YourTour.util.Context.getContext('/route/Delete.action')
			}
    	}*/
    },
});
