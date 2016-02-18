Ext.define('YourTour.model.RouteServiceModel', {
    extend: 'YourTour.model.BaseModel',
    config:{
	    fields:[
			{name:'id', type:'string'},
			{name:'fee', type:'string', defaultValue:'0'},
			{name:'adultNum', type:'string'},
			{name:'oldNum', type:'string'},
			{name:'childNum', type:'string'},
			{name:'fromDateStr', type:'string'},
			{name:'endDateStr', type:'string'},
			{name:'place', type:'string'},
			{name:'memo', type:'string'}
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
