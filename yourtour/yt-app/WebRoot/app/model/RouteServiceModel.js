Ext.define('YourTour.model.RouteServiceModel', {
    extend: 'YourTour.model.BaseModel',
    config:{
	    fields:[
			{name:'id', type:'string'},
			{name:'title', type:'string'},
			{name:'imageUrl', type:'string'},
			{name:'imageUrls', type:'string'},
			{name:'fee', type:'string'},
			{name:'withdraw', type:'string'},
			{name:'feeIncluding', type:'string'},
			{name:'feeExcluding', type:'string'},
			{name:'memo', type:'string'},
			{name:'status', type:'string'},
			{name:'expertServiceId', type:'string'}
	    ],

		associations: [
			{
				type: 'hasMany',
				model: 'YourTour.model.ExpertServiceModel',
				name:'expertService',
				associationKey:'expertService'
			}
		]
    }
});
