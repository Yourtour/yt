Ext.define('YourTour.model.RouteActivityModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'id',
    	
	    fields:[
	    	{name:'id', type:'string'},
            {name:'type', type:'string'},
    		{name:'title', type:'string'},
    		{name:'memo', type:'string'},
    		{name:'startTime', type:'string'},
    		{name:'endTime', type:'string'},
    		{name:'period', type:'string'}
	    ],
	    
	    associations: [
  	        {  
  	            type: 'hasMany',   
  	            model: 'YourTour.model.ResourceModel',   
  	            name:'resource',   
  	            associationKey:'resource'  
  	        }
   	    ],
    }
});
