Ext.define('YourTour.model.CommentModel', {
    extend: 'Ext.data.Model',
    config:{
    	idProperty:'rowKey',
    	
	    fields:[{name:'rowKey', type:'string'},
	            {name:'nickname', type:'string'},
	            {name:'imageUrl', type:'string'},
	            {name:'comment', type:'string'},
	            {name:'publishTime', type:'string'}
	    ]
    }
});
