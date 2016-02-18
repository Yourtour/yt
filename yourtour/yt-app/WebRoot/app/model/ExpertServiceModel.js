Ext.define('YourTour.model.ExpertServiceModel', {
    extend: 'YourTour.model.BaseModel',
    config:{
	    fields:[
			{name:'id', type:'string'},
			{name:'title', type:'string'},
			{name:'imageUrl', type:'string'},
			{name:'fee', type:'string', defaultValue:'0'},
			{name:'withdraw', type:'string'},
			{name:'feeIncluding', type:'string'},
			{name:'feeExcluding', type:'string'},

			{name:'commentNum', type:'string'},
			{name:'goodNum', type:'string'},
			{name:'mediumNum', type:'string'},
			{name:'badNum', type:'string'},
			{name:'imageNum', type:'string'},
			{name:'commentScore', type:'string'},
			{name:'memo', type:'string'},
			{name:'favorited', type:'string'},
			{name:'status', type:'string'}
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
