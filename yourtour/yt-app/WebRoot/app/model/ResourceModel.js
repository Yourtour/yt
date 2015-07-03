Ext.define('YourTour.model.ResourceModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'rowKey',
    	
	    fields:[{name:'rowKey', type:'string'},
	            {name:'imageUrls', type:'string'},
	    		{name:'name', type:'string'},
	    		{name:'intro', type:'string'}
	    ]
    }
});
