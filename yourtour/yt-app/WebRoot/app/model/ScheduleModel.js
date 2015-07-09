Ext.define('YourTour.model.ScheduleModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'rowKey',
    	
	    fields:[
	    	{name:'rowKey', type:'string'},
            {name:'type', type:'string'},
    		{name:'name', type:'string'},
    		{name:'resName', type:'string'},
    		{name:'imageUrl', type:'string'},
    		{name:'address', type:'string'},
    		{name:'phone', type:'string'},
    		{name:'desc', type:'string'},
    		{name:'time', type:'string'},
    		{name:'period', type:'string'},
    		{name:'lines', type:'string'},
    		{name:'isLast', type:'string'}
	    ]
    }
});
