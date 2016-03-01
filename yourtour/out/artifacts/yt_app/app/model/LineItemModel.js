Ext.define('YourTour.model.LineItemModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'rowKey',
    	
	    fields:[{name:'rowKey', type:'string'},
	            {name:'imageUrl', type:'string'},
	    		{name:'name', type:'string'}
	    ]
    }
});
