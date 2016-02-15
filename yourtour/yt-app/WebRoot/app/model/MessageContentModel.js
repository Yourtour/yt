Ext.define('YourTour.model.MessageContentModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'id',
    	
	    fields:[{name:'id', type:'string'},
				{name: 'contentType', type: 'string'},
	    		{name:'content', type:'string'},
	    		{name:'sendTime', type:'string'}
	    ],

		associations: [
			{
				type: 'hasMany',
				model: 'YourTour.model.UserModel',
				name:'user',
				associationKey:'user'
			}
		]
    }
});
