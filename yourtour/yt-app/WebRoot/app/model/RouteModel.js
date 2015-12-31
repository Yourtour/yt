Ext.define('YourTour.model.RouteModel', {
    extend: 'Ext.data.Model',
    requires:['YourTour.model.RouteScheduleModel'],
    config:{
    	idProperty:'id',
    	
	    fields:[{name:'id', type:'string'},
				{name:'step', type:'string'},
	            {name:'imageUrl', type:'string'},
	    		{name:'name', type:'string'},
	    		{name:'lineName', type:'string'},
	    		{name:'impression', type:'string'},
				{name:'fromPlace', type:'string'}, //id,name
				{name:'toPlaces', type:'string'},//ids|names
	    		{name:'startDate', type:'string'},
	    		{name:'endDate', type:'string'},
	    		{name:'duration', type:'string'},
	    		{name:'feature', type:'string'}
	    ],

	    associations: [{  
            type: 'hasMany',   
            model: 'YourTour.model.RouteScheduleModel',   
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
