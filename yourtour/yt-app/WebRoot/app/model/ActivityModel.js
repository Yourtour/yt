/**
 * 用来定义启动页的活动或者广告之类实体
 */
Ext.define('YourTour.model.ActivityModel', {
    extend: 'YourTour.model.BaseModel',
    config:{
	    fields:[
			{name:'id', type:'string'},
			{name:'img', type:'string'},
			{name:'memo', type:'string'}
	    ]
    }
});
