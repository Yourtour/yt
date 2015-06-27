Ext.define('YourTour.model.LineModel', {
    extend: 'Ext.data.Model',
    config:{
	    fields:[{name:'id', type:'string'},
	            {name:'imageUrl', type:'string'},
	    		{name:'name', type:'string'},
	    		{name:'lineName', type:'string'}
	    ],
	    
	    hasMany:{model:'YourTour.model.UserModel', name:'users'}
    },
});
