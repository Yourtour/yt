Ext.define('YourTour.model.ExpertModel', {
	extend: 'Ext.data.Model',
    config:{
    	idProperty:'id',
    	
	    fields:[
            {name:'id', type:'string'}
	    ],

		associations: [
			{
				type: 'hasMany',
				model: 'YourTour.model.UserModel',
				name:'profile',
				associationKey:'profile'
			},

			{
				type: 'hasMany',
				model: 'YourTour.model.RouteModel',
				name:'routes',
				associationKey:'routes'
			},

			{
				type: 'hasMany',
				model: 'YourTour.model.ServiceModel',
				name:'services',
				associationKey:'services'
			}
		]
    }
});
