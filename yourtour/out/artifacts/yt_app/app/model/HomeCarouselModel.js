Ext.define('YourTour.model.HomeCarouselModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'rowKey',
    	
	    fields:[{name:'rowKey', type:'string'},
	    		{name:'imageUrl', type:'string'}
	    ] 
    }
});
