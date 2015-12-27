Ext.define('YourTour.model.ServiceModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'id',
    	
	    fields:[
			{name:'id', type:'string'},
			{name:'title', type:'string'},
			{name:'free', type:'string'},
			{name:'withdraw', type:'string'},
			{name:'feeIncluding', type:'string'},
			{name:'feeExcluding', type:'string'},
			{name:'memo', type:'string'}
	    ]
    }
});
