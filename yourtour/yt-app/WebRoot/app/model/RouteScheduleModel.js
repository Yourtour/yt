Ext.define('YourTour.model.RouteScheduleModel', {
    extend: 'Ext.data.Model',
    requires:['YourTour.model.RouteActivityModel'],
    config:{
    	idProperty:'id',
    	
	    fields:[
	    	{name:'id', type:'string'},
            {name:'type', type:'string'},
    		{name:'title', type:'string'},
    		{name:'memo', type:'string'},
            {name:'date', type:'string'},
            {name:'days', type:'string'},
            {name:'placeIds', type:'string'},
            {name:'places', type:'string'}
	    ],
	    
	    associations: [{  
            type: 'hasMany',   
            model: 'YourTour.model.RouteActivityModel',   
            name:'activities',
            associationKey:'activities'
        }]
    }
});
