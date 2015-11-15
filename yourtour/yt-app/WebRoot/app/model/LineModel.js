Ext.define('YourTour.model.LineModel', {
    extend: 'Ext.data.Model',
    requires:['YourTour.model.UserModel','YourTour.model.ResourceModel'],
    config:{
    	idProperty:'id',
    	
	    fields:[{name:'id', type:'string'},
	            {name:'imageUrl', type:'string'},
	    		{name:'name', type:'string'},
	    		{name:'feature', type:'string'},
	    		{name:'reason', type:'string'},
	    		{name:'rank', type:'string'},
	    		{name:'rankScore', type:'string'},
	    		{name:'thumbupNum', type:'string'},
	    		{name:'favoriteNum', type:'string'},
	    		{name:'shareNum', type:'string'},
	    		{name:'commentNum', type:'string'}
	    ],
	    
	    associations: [
	        {  
	            type: 'hasMany',   
	            model: 'YourTour.model.ResourceModel',   
	            name:'resources',   
	            associationKey:'resources'  
	        }
        ]  
    }
});
