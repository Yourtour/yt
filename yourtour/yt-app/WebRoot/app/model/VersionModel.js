Ext.define('YourTour.model.VersionModel', {
    extend: 'YourTour.model.BaseModel',
    config:{
	    fields:[{name:'id', type:'string'},
	            {name:'releaseNotes', type:'string'},
                {name:'newVersionUrl', type:'string'}
	    ]
    }
});
