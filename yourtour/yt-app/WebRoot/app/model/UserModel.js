Ext.define('YourTour.model.UserModel', {
    extend: 'Ext.data.Model',
    config:{
	    fields:[{name:'id', type:'string'},
	            {name:'imageUrl', type:'string'},
	    		{name:'nickName', type:'string'}
	    ]
    },
});
