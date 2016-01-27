Ext.define('YourTour.model.ChargeModel', {
    extend: 'YourTour.model.BaseModel',
    config:{
	    fields:[
			{name:'id', type:'string'},
			{name:'name', type:'string'},
			{name:'item', type:'string'},
			{name:'imageUrl', type:'string'},
			{name:'amount', type:'string'},
			{name:'memo', type:'string'},
			{name:'type', type:'string'},
			{name:'chargeDate', type:'long'},
			{name:'hidden', type:'boolean'}
	    ]
    }
});
