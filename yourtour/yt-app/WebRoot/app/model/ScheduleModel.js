Ext.define('YourTour.model.ScheduleModel', {
    extend: 'Ext.data.Model',
    requires:['YourTour.model.SchedulePlanModel'],
    config:{
    	idProperty:'rowKey',
    	
	    fields:[
	    	{name:'rowKey', type:'string'},
            {name:'type', type:'string'},
    		{name:'name', type:'string'},
            {name:'date', type:'string'},
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
