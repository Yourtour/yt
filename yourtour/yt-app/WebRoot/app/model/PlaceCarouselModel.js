Ext.define('YourTour.model.PlaceCarouselModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'rowKey',
    	
	    fields:[{name:'rowKey', type:'string'},
	    		{name:'imageUrl', type:'string'}
	    ] 
    }
});
