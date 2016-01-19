Ext.define('YourTour.model.PlaceModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'id',
    	
	    fields:[{name:'id', type:'string'},
	            {name:'code', type:'string'},
				{name:'parentCode', type:'string'},
	    		{name:'name', type:'string'},
	    		{name:'num', type:'string'},
				{name:'imageUrl', type:'string'},
				{name:'leaf', type:'string'},
				{name:'expandable', type:'string'},
				{name:'followedNum', type:'string'},
				{name:'goneNum', type:'string'},
				{name:'goingNum', type:'string'},
				{name:'hidden', type:'boolean'}

	    ],
	    
	    associations: [
	        {  
	            type: 'hasMany',   
	            model: 'YourTour.model.PlaceModel',   
	            name:'children',   
	            associationKey:'children'  
	        }
        ]  
    }
});
