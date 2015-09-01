Ext.define('YourTour.model.TalentModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'rowKey',
    	
	    fields:[
            {name:'rowKey', type:'string'},
            {name:'imageUrl', type:'string'},
    		{name:'nickname', type:'string'},
    		{name:'rank', type:'string'},
    		{name:'sex', type:'string'},
    		{name:'tag1', type:'string'},
    		{name:'tag2', type:'string'}
	    ]
    }
});
