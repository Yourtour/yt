Ext.define('YourTour.model.common.ResponseModel', {
    extend: 'Ext.data.Model',
    requires:['YourTour.model.LiveModel'],
    config:{
	    fields:[
            {name:'errorCode', type:'string'},
            {name:'errorText', type:'string'}
	    ],
	    
	    associations: [
   	        {  
   	            type: 'hasMany',   
   	            model: 'YourTour.model.ResourceModel',   
   	            name:'resources',   
   	            associationKey:'resources'  
   	        },
	    ],
	    
	    proxy: {
	    	type: 'ajax',
	    	url : 'account/register',
	    	noCache: false,
	    	reader: {
		    	type: 'json',
		    	root: 'data'
	    	}
	    }
    }
});
