Ext.define('YourTour.model.ServiceModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'id',
    	
	    fields:[{name:'id', type:'string'},
	            {name:'title', type:'string'}
	    ]
    }
});
