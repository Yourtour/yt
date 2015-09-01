Ext.define('YourTour.model.AlongModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'rowKey',
    	
	    fields:[{name:'rowKey', type:'string'},
	    		{name:'imageUrl', type:'string'},
	    		{name:'memberImageUrl', type:'string'},
	    		{name:'title', type:'string'},
	    		{name:'intention', type:'string'},
	    		{name:'deadline', type:'string'},
	    		{name:'alongNum', type:'string'}
	    ] 
    }
});
