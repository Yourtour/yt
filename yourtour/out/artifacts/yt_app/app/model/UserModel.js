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
			{name:'position', type:'string', defaultValue:'121.579496,31.267881'},
			{name:'age', type:'string'},
			{name:'identity', type:'string'},
			{name:'memo', type:'string'},
			{name:'places', type:'string'},
			{name:'idAuthenticate', type:'string'},
			{name:'mobileAuthenticate', type:'string'},
			{name:'snsAuthenticate', type:'string'},

			{name:'followingNum', type:'string', defaultValue:'0'},
			{name:'followedNum', type:'string', defaultValue:'0'},
			{name:'thumbupNum', type:'string', defaultValue:'0'},
			{name:'albumNum', type:'string', defaultValue:'0'},
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
