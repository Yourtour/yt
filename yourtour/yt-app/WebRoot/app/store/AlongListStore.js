Ext.define('YourTour.store.AlongListStore', {
	extend: 'Ext.data.Store',
    requires: [
       'YourTour.model.AlongModel'
    ],
    config:{
    	model:'YourTour.model.AlongModel',
    	data:[
			{rowKey:"11", imageUrl:'resources/images/user_logo_32.png',title:'十一上海休闲游',nickName:'喵喵QQ', gender:'1', publishTime:'2015-10-01', intention:'拼车',startDate:'2015-10-20', endDate:'2015-10-31', lineName:'东方明珠->外滩->城隍庙',deadline:'2015-10-12',alongNum:'3', memo:'机票已经订好，周末飞上海，然后计划在上海及其附近游玩10天，目前独身一人在旅行，希望能捡个90后妹子同游。。。。。', address:'我在  浙江杭州西湖区', readNum:'23', commentNum:'23'},
	     	{rowKey:"12", imageUrl:'resources/images/user_logo_32.png',title:'春节上海休闲游',nickName:'喵喵QQ', gender:'1', publishTime:'2015-10-02', intention:'拼车',startDate:'2015-10-20', endDate:'2015-10-31', lineName:'东方明珠->外滩->城隍庙', deadline:'2015-10-12',alongNum:'3', memo:'机票已经订好，周末飞上海，然后计划在上海及其附近游玩10天，目前独身一人在旅行，希望能捡个90后妹子同游。。。。。', address:'我在  浙江杭州西湖区', readNum:'23', commentNum:'23'},
	     	{rowKey:"11", imageUrl:'resources/images/user_logo_32.png',title:'十一上海休闲游',nickName:'喵喵QQ', gender:'1', publishTime:'2015-10-01', intention:'拼车',startDate:'2015-10-20', endDate:'2015-10-31', lineName:'东方明珠->外滩->城隍庙',deadline:'2015-10-12',alongNum:'3', memo:'机票已经订好，周末飞上海，然后计划在上海及其附近游玩10天，目前独身一人在旅行，希望能捡个90后妹子同游。。。。。', address:'我在  浙江杭州西湖区', readNum:'23', commentNum:'23'},
	     	{rowKey:"13", imageUrl:'resources/images/user_logo_32.png',title:'春节上海休闲游',nickName:'喵喵QQ', gender:'1', publishTime:'2015-10-02', intention:'拼车',startDate:'2015-10-20', endDate:'2015-10-31', lineName:'东方明珠->外滩->城隍庙', deadline:'2015-10-12',alongNum:'3', memo:'机票已经订好，周末飞上海，然后计划在上海及其附近游玩10天，目前独身一人在旅行，希望能捡个90后妹子同游。。。。。', address:'我在  浙江杭州西湖区', readNum:'23', commentNum:'23'},
	     	{rowKey:"14", imageUrl:'resources/images/user_logo_32.png',title:'十一上海休闲游',nickName:'喵喵QQ', gender:'1', publishTime:'2015-10-01', intention:'拼车',startDate:'2015-10-20', endDate:'2015-10-31', lineName:'东方明珠->外滩->城隍庙',deadline:'2015-10-12',alongNum:'3', memo:'机票已经订好，周末飞上海，然后计划在上海及其附近游玩10天，目前独身一人在旅行，希望能捡个90后妹子同游。。。。。', address:'我在  浙江杭州西湖区', readNum:'23', commentNum:'23'},
	     	{rowKey:"15", imageUrl:'resources/images/user_logo_32.png',title:'春节上海休闲游',nickName:'喵喵QQ', gender:'1', publishTime:'2015-10-02', intention:'拼车',startDate:'2015-10-20', endDate:'2015-10-31', lineName:'东方明珠->外滩->城隍庙', deadline:'2015-10-12',alongNum:'3', memo:'机票已经订好，周末飞上海，然后计划在上海及其附近游玩10天，目前独身一人在旅行，希望能捡个90后妹子同游。。。。。', address:'我在  浙江杭州西湖区', readNum:'23', commentNum:'23'}
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
