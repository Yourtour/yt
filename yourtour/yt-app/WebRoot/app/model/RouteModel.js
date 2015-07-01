Ext.define('YourTour.model.RouteModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'rowKey',
    	
	    fields:[{name:'rowKey', type:'string'},
	            {name:'imageUrl', type:'string'},
	    		{name:'name', type:'string'},
	    		{name:'lineName', type:'string'},
	    		{name:'feeling', type:'string'},
	    		{name:'startTime', type:'string'},
	    		{name:'endTime', type:'string'},
	    		{name:'period', type:'string'}
	    ]
    }
});
