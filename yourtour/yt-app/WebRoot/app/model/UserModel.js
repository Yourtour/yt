Ext.define('YourTour.model.UserModel', {
	extend: 'Ext.data.Model',
    config:{
    	idProperty:'graphId',
    	
	    fields:[
            {name:'graphId', type:'string'},
            {name:'mobile', type:'string'},
            {name:'authcode', type:'string'},
            {name:'userName', type:'string'},
            {name:'password', type:'string'},
    		{name:'nickname', type:'string'},
    		{name:'sex', type:'string'},
    		{name:'imageurl', type:'string'},
    		{name:'tags', type:'string'}
	    ],
	    
	    proxy: {
	    	type: 'ajax',
	    	url : 'account/main/save',
	    	noCache: false,
	    	reader: {
		    	type: 'json'
	    	}
	    }
    }
});
