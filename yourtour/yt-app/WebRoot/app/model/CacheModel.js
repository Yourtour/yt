Ext.define('YourTour.model.CacheModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'key',
    	
	    fields:[{name:'key', type:'string'},
	            {name:'value', type:'string'}
	    ]
    }
});
