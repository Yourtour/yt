Ext.define('YourTour.model.CommentModel', {
    extend: 'YourTour.model.BaseModel',
    config:{
	    fields:[{name:'id', type:'string'},
	            {name:'memo', type:'string'},
				{name:'score', type:'string'},
				{name:'thumbup', type:'string'},
				{name:'replied', type:'string'},
				{name:'tags', type:'string'},
	            {name:'imageUrls', type:'string'},
				{name:'createdDate', type:'string'},
	    ],

		associations: [
			{
				type: 'hasMany',
				model: 'YourTour.model.UserModel',
				name:'user',
				associationKey:'user'
			}
		]
    }
});
