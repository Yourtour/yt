Ext.define('YourTour.model.OptionModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'value',
    	
	    fields:[{name:'text', type:'string'},
	            {name:'value', type:'string'}
	    ]
    }
});
