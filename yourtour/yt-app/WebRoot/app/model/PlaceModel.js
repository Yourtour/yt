Ext.define('YourTour.model.PlaceModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'id',
    	
	    fields:[{name:'id', type:'string'},
	            {name:'code', type:'string'},
	    		{name:'name', type:'string'},
	    		{name:'num', type:'string'}
	    ],
	    
	    associations: [
	        {  
	            type: 'hasMany',   
	            model: 'YourTour.model.PlaceModel',   
	            name:'children',   
	            associationKey:'children'  
	        }
        ]  
    }
});
