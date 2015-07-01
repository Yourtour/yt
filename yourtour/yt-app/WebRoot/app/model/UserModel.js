Ext.define('YourTour.model.UserModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'rowKey',
    	
	    fields:[
            {name:'rowKey', type:'string'},
            {name:'imageUrl', type:'string'},
    		{name:'nickName', type:'string'}
	    ]
    },
});
