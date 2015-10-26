Ext.define('YourTour.model.UserAccountModel', {
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
	    	url : 'account/register',
	    	noCache: false,
	    	reader: {
		    	type: 'json',
		    	root: 'data'
	    	}
	    }
    }
});
