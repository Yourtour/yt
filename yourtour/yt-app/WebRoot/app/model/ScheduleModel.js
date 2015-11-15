Ext.define('YourTour.model.ScheduleModel', {
    extend: 'Ext.data.Model',
    requires:['YourTour.model.SchedulePlanModel'],
    config:{
    	idProperty:'id',
    	
	    fields:[
	    	{name:'id', type:'string'},
            {name:'type', type:'string'},
    		{name:'name', type:'string'},
            {name:'date', type:'string'},
            {name:'days', type:'string'},
    		{name:'places', type:'string'},
    		{name:'memo', type:'string'}
	    ],
	    
	    associations: [{  
            type: 'hasMany',   
            model: 'YourTour.model.SchedulePlanModel',   
            name:'plans',
            associationKey:'plans'
        }]
    }
});
