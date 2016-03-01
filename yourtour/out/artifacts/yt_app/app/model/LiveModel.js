Ext.define('YourTour.model.LiveModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'rowKey',
    	
	    fields:[{name:'rowKey', type:'string'},
	    		{name:'lineName', type:'string'}
	    ] 
    }
});
