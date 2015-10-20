Ext.define('YourTour.model.LaunchModel', {
    extend: 'Ext.data.Model',
    config:{
	    associations: [
   	    	{  
   	            type: 'hasMany',   
   	            model: 'YourTour.model.PlaceModel',   
   	            name:'places',
   	            associationKey:'places'
   	        }
	    ]
    }
});
