Ext.define('YourTour.model.PlaceModel', {
    extend: 'Ext.data.Model',
    requires:['YourTour.model.LiveModel','YourTour.model.ChatModel','YourTour.model.AlongModel','YourTour.model.TalentModel'],
    config:{
    	idProperty:'rowKey',
    	
	    fields:[{name:'rowKey', type:'string'},
	    		{name:'name', type:'string'},
	    		{name:'liveNum', type:'string'},
	    		{name:'chatNum', type:'string'}
	    ],
	    
	    associations: [
		    {  
	            type: 'hasMany',   
	            model: 'YourTour.model.LiveModel',   
	            name:'lives',
	            associationKey:'lives'
	        },
	        {  
	            type: 'hasMany',   
	            model: 'YourTour.model.ChatModel',   
	            name:'chats',
	            associationKey:'chats'
	        },
	        {  
	            type: 'hasMany',   
	            model: 'YourTour.model.AlongModel',   
	            name:'alongs',
	            associationKey:'alongs'
	        },
	        {  
	            type: 'hasMany',   
	            model: 'YourTour.model.TalentModel',   
	            name:'talents',
	            associationKey:'talents'
	        }
        ]  
    }
});
