Ext.define('YourTour.model.AlongModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'rowKey',
    	
	    fields:[{name:'rowKey', type:'string'},
	            {name:'title', type:'string'},
	            {name:'imageUrl', type:'string'},
	            {name:'nickName', type:'string'},
	            {name:'gender', type:'string'},
	            {name:'publishTime', type:'string'},
	            {name:'intention', type:'string'},
	            {name:'startDate', type:'string'},
	            {name:'endDate', type:'string'},
	            {name:'lineName', type:'string'},
	            {name:'memo', type:'string'},
	            {name:'address', type:'string'},
	            {name:'readNum', type:'string'},
	            {name:'commentNum', type:'string'},
	    		{name:'deadline', type:'string'},
	    		{name:'alongNum', type:'string'}
	    ]
    }
});
