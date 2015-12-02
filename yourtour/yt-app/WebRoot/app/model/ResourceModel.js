Ext.define('YourTour.model.ResourceModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'id',
    	
	    fields:[{name:'id', type:'string'},
	    		{name:'imageUrls', type:'string'},
	            {name:'imageUrl', type:'string'},
	    		{name:'name', type:'string'},
	    		{name:'type', type:'string'},
	    		{name:'intro', type:'string'},
	    		{name:'address', type:'string'},
	    		{name:'phone', type:'string'},
	    		{name:'open', type:'string'},
	    		{name:'price', type:'string'},
	    		{name:'recommendIndex', type:'string'},
	    		{name:'assessmentIndex', type:'string'},
	    		{name:'desc', type:'string'}
	    ]
    }
});
