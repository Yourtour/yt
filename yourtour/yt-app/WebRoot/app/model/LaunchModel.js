Ext.define('YourTour.model.LaunchModel', {
    extend: 'Ext.data.Model',
    config:{
	    associations: [
			{
				type: 'hasMany',
				model: 'YourTour.model.DeviceModel',
				name:'devices',
				associationKey:'devices'
			},

   	    	{  
   	            type: 'hasMany',   
   	            model: 'YourTour.model.PlaceModel',   
   	            name:'places',
   	            associationKey:'places'
   	        },

			{
				type: 'hasMany',
				model: 'YourTour.model.SimpleModel',
				name:'tags',
				associationKey:'tags'
			}
	    ]
    }
});
