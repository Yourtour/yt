Ext.define('YourTour.model.BestModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'rowKey',
    	
	    fields:[{name:'rowKey', type:'string'},
	            {name:'imageUrl', type:'string'},
	    		{name:'name', type:'string'},
	    		{name:'category', type:'string'},
	    		{name:'type', type:'string'}
	    ]
    }
});
