Ext.define('YourTour.model.ExpertModel', {
	extend: 'Ext.data.Model',
    config:{
    	idProperty:'id',
    	
	    fields:[
            {name:'id', type:'string'},
            {name:'mobile', type:'string'},
            {name:'authcode', type:'string'},
            {name:'userName', type:'string'},
            {name:'password', type:'string'},
    		{name:'nickName', type:'string'},
			{name:'isExpert', type:'string'},
    		{name:'sex', type:'string'},
    		{name:'role', type:'string'},
    		{name:'imageUrl', type:'string'},
    		{name:'tags', type:'string'},
			{name:'age', type:'string'},
			{name:'identity', type:'string'},
			{name:'memo', type:'string'},
			{name:'places', type:'string'},
			{name:'idAuthenticate', type:'string'},
			{name:'mobileAuthenticate', type:'string'},
			{name:'snsAuthenticate', type:'string'},
	    ],

		associations: [
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
