Ext.define('YourTour.model.ChatModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'rowKey',
    	
	    fields:[{name:'rowKey', type:'string'},
	    		{name:'name', type:'string'},
	    		{name:'logoUrl', type:'string'},
	    		{name:'content', type:'string'}
	    ] 
    }
});
