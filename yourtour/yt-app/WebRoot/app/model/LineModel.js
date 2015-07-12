Ext.define('YourTour.model.LineModel', {
    extend: 'Ext.data.Model',
    requires:['YourTour.model.UserModel','YourTour.model.ResourceModel'],
    config:{
    	idProperty:'rowKey',
    	
	    fields:[{name:'rowKey', type:'string'},
	            {name:'imageUrl', type:'string'},
	    		{name:'name', type:'string'},
	    		{name:'feature', type:'string'},
	    		{name:'reason', type:'string'}
	    ],
	    
	    associations: [
	        {  
	            type: 'hasMany',   
	            model: 'YourTour.model.ResourceModel',   
	            name:'resources',   
	            associationKey:'resources'  
	        },{  
	            type: 'hasMany',   
	            model: 'YourTour.model.RouteModel',   
	            name:'routes',   
	            associationKey:'routes'  
	        }
        ]  
    }
});
