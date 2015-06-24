Ext.define('YourTour.model.RouteModel', {
    extend: 'Ext.data.Model',
    config:{
	    fields:[{name:'id', type:'string'},
	            {name:'imageUrl', type:'string'},
	    		{name:'name', type:'string'},
	    		{name:'lineName', type:'string'},
	    		{name:'feeling', type:'string'},
	    		{name:'step', type:'string'}
	    ]
    }
});
