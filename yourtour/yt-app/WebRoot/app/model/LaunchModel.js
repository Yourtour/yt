Ext.define('YourTour.model.LaunchModel', {
    extend: 'Ext.data.Model',
    config:{
	    associations: [
			{
				type: 'hasMany',
				model: 'YourTour.model.DeviceModel',
				name:'device',
				associationKey:'device'
			},

			{
				type: 'hasMany',
				model: 'YourTour.model.ActivityModel',
				name:'activity',
				associationKey:'activity'
			},
	    ]
    }
});
