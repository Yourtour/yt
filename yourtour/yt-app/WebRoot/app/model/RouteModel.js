Ext.define('YourTour.model.RouteModel', {
    extend: 'Ext.data.Model',
    requires:['YourTour.model.RouteScheduleModel','YourTour.model.UserModel','YourTour.model.ExpertModel'],
    config:{
    	idProperty:'id',
		useCache:false,
    	
	    fields:[{name:'id', type:'string'},
				{name:'step', type:'string'},
	            {name:'imageUrl', type:'string'},
	    		{name:'name', type:'string'},
	    		{name:'lineName', type:'string'},
	    		{name:'impression', type:'string'},
				{name: 'topic', type: 'string', defaultValue:'登山游'},
				{name: 'price', type: 'string', defaultValue:'3500元/成人，1700元/儿童'},

				{name:'fromPlace', type:'string'}, //id,name
				{name:'toPlaces', type:'string'},//ids|names
	    		{name:'startDate', type:'string'},
	    		{name:'endDate', type:'string'},
	    		{name:'duration', type:'string', defaultValue:'10天'},

				{name:'adultNum', type:'string'},
				{name:'childNum', type:'string'},
				{name:'olderNum', type:'string'},

				{name:'rank', type:'string'},
				{name:'rankScore', type:'string'},
				{name:'thumbupNum', type:'string'},
				{name:'favoriteNum', type:'string'},
				{name:'shareNum', type:'string'},
				{name:'commentNum', type:'string'},

				{name:'feature', type:'string'},
				{name:'reason', type:'string'},
				{name:'chargeMemo', type:'string'},
				{name:'withdrawMemo', type:'string'},
				{name:'useMemo', type:'string'},
				{name:'orderMemo', type:'string'},
				{name:'status', type:'string', defaultValue:'new'}
	    ],

	    associations: [
			{
				type: 'hasMany',
				model: 'YourTour.model.UserModel',
				name:'user',
				associationKey:'user'
			},

			{
            	type: 'hasMany',
				model: 'YourTour.model.RouteScheduleModel',
				name:'schedules',
				associationKey:'schedules'
        	}
		]
    }
});

