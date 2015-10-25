Ext.define('YourTour.model.UserAccountModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'rowKey',
    	
	    fields:[
            {name:'rowKey', type:'string'},
            {name:'mobileNo', type:'string'},
            {name:'authCode', type:'string'},
            {name:'userName', type:'string'},
            {name:'password', type:'string'},
    		{name:'nickname', type:'string'},
    		{name:'sex', type:'string'},
    		{name:'imageurl', type:'string'},
    		{name:'tags', type:'string'},
    		{name:'step', type:'string'}
	    ],
	    
	    proxy: {
	    	type: 'ajax',
	    	url : 'account/register',
	    	reader: {
		    	type: 'json',
		    	root: 'users'
	    	}
	    }
    }
});
