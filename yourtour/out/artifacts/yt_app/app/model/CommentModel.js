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
				{name:'recommendedIndex', type:'string'},
				{name:'image', type:'string'},
				{name:'good', type:'string'},
				{name:'medium', type:'string'},
				{name:'bad', type:'string'}
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
