Ext.define('YourTour.model.ResourceModel', {
	extend: 'YourTour.model.BaseModel',
    config:{
	    fields:[{name:'id', type:'string'},
	    		{name:'imageUrls', type:'string'},
	            {name:'imageUrl', type:'string'},
	    		{name:'name', type:'string'},
	    		{name:'type', type:'string'},
	    		{name:'intro', type:'string'},
	    		{name:'address', type:'string'},
	    		{name:'phone', type:'string'},
	    		{name:'openTime', type:'string'},
	    		{name:'price', type:'string'},
				{name:'commentNum', type:'string'},
				{name:'goodNum', type:'string'},
				{name:'mediumNum', type:'string'},
				{name:'badNum', type:'string'},
				{name:'imageNum', type:'string'},
				{name:'healthNum', type:'string'},
				{name:'trafficNum', type:'string'},
				{name:'facilityNum', type:'string'},
				{name:'environmentNum', type:'string'},
				{name:'commentScore', type:'string'},
				{name:'healthScore', type:'string'},
				{name:'environmentScore', type:'string'},
				{name:'serviceScore', type:'string'},
				{name:'facilityScore', type:'string'},
	    		{name:'recommendIndex', type:'string'},
	    		{name:'assessmentIndex', type:'string'},
	    		{name:'desc', type:'string'}
	    ],

		associations: [
			{
				type: 'hasMany',
				model: 'YourTour.model.ActivityItemModel',
				name:'activityItems',
				associationKey:'activityItems'
			}
		]
    }
});
