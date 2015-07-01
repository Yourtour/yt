Ext.define('YourTour.model.ScheduleModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'rowKey',
    	
	    fields:[{name:'rowKey', type:'string'},
	            {name:'date', type:'string'},
	    		{name:'place', type:'string'}
	    ],
	    
	    associations: [{  
            type: 'hasMany',   
            model: 'YourTour.model.OptionModel',   
            name:'options',   
            associationKey:'options'  
        }]  
    }
});
