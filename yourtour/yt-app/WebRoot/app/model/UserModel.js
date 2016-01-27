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
			{name:'isExpert', type:'string'},
    		{name:'sex', type:'string'},
    		{name:'role', type:'string'},
    		{name:'imageUrl', type:'string'},
    		{name:'tags', type:'string'},
			{name:'position', type:'string', defaultValue:'121.579496,31.267881'}
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
