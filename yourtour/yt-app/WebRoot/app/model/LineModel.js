Ext.define('YourTour.model.LineModel', {
    extend: 'Ext.data.Model',
    requires:['YourTour.model.UserModel'],
    config:{
    	idProperty:'rowKey',
    	
	    fields:[{name:'rowKey', type:'string'},
	            {name:'imageUrl', type:'string'},
	    		{name:'name', type:'string'},
	    		{name:'feature', type:'string'},
	    		{name:'reason', type:'string'}
	    ],
	    
	    associations: [{  
            type: 'hasMany',   
            model: 'YourTour.model.UserModel',   
            name:'users',   
            associationKey:'users'  
        },
        {  
            type: 'hasMany',   
            model: 'YourTour.model.ResourceModel',   
            name:'resources',   
            associationKey:'resources'  
        }]  
    }
});
