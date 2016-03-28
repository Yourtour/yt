Ext.define('YourTour.model.LaunchModel', {
	extend: 'YourTour.model.BaseModel',
    config:{
		fields:[
			{name:'id', type:'string'},
			{name:'accessToken', type:'string'},
			{name:'sessionToken', type:'string'}
		],

	    associations: [
			{
				type: 'hasMany',
				model: 'YourTour.model.VersionModel',
				name:'version',
				associationKey:'version'
			},

			{
				type: 'hasMany',
				model: 'YourTour.model.ActivityModel',
				name:'activity',
				associationKey:'activity'
			}
	    ]
    }
});
