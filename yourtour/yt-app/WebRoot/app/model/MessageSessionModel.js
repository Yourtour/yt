Ext.define('YourTour.model.MessageSessionModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'id',
    	
	    fields:[{name:'id', type:'string'},
	            {name:'imageUrl', type:'string'},
	    		{name:'title', type:'string'},
	    		{name:'latestMessage', type:'string'}
	    ]
    }
});
