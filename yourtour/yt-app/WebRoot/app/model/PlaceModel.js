Ext.define('YourTour.model.PlaceModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'rowKey',
    	
	    fields:[{name:'rowKey', type:'string'},
	            {name:'ename', type:'string'},
	    		{name:'name', type:'string'}
	    ],
	    
	    associations: [
	        {  
	            type: 'hasMany',   
	            model: 'YourTour.model.PlaceModel',   
	            name:'cities',   
	            associationKey:'cities'  
	        }
        ]  
    }
});
