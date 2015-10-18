Ext.define('YourTour.model.RouteModel', {
    extend: 'Ext.data.Model',
    requires:['YourTour.model.ScheduleModel'],
    config:{
    	idProperty:'rowKey',
    	
	    fields:[{name:'rowKey', type:'string'},
	            {name:'imageUrl', type:'string'},
	            {name:'imageUrls', type:'string'},
	    		{name:'name', type:'string'},
	    		{name:'place', type:'string'},
	    		{name:'lineName', type:'string'},
	    		{name:'impression', type:'string'},
	    		{name:'startTime', type:'string'},
	    		{name:'endTime', type:'string'},
	    		{name:'period', type:'string'},
	    		{name:'feature', type:'string'}
	    ],
	    
	    associations: [{  
            type: 'hasMany',   
            model: 'YourTour.model.ScheduleModel',   
            name:'schedules',
            associationKey:'schedules'
        }]  
    }
});
