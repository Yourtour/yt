Ext.define('YourTour.model.UserModel', {
	extend: 'Ext.data.Model',
    config:{
    	idProperty:'id',
    	
	    fields:[
            {name:'id', type:'string'},
            {name:'mobile', type:'string'},
            {name:'authcode', type:'string'},
            {name:'userName', type:'string'},
            {name:'password', type:'string'},
    		{name:'nickName', type:'string'},
    		{name:'sex', type:'string'},
    		{name:'role', type:'string'},
    		{name:'imageUrl', type:'string'},
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
