Ext.define('YourTour.model.RouteModel', {
    extend: 'Ext.data.Model',
    requires:['YourTour.model.ScheduleModel'],
    config:{
    	idProperty:'id',
    	
	    fields:[{name:'id', type:'string'},
	            {name:'imageUrl', type:'string'},
	    		{name:'name', type:'string'},
	    		{name:'impression', type:'string'},
	    		{name:'startDate', type:'string'},
	    		{name:'endDate', type:'string'},
	    		{name:'period', type:'string'},
	    		{name:'feature', type:'string'}
	    ],
	    
	    associations: [{  
            type: 'hasMany',   
            model: 'YourTour.model.ScheduleModel',   
            name:'schedules',
            associationKey:'schedules'
        }],
        
        proxy: {
	    	type: 'ajax',
	    	noCache: false,
	    	reader: {
		    	type: 'json'
	    	}
	    }
    }
});
