Ext.define('YourTour.model.LocalCache', {
    extend: 'Ext.data.Model',
    xtype:'localCache',
    config:{
	    fields:[{name:'key', type:'string'},
	            {name:'value', type:'string'}
	    	   ],
	    
	    proxy: {
	        type: 'localstorage',
	        id  : 'cachekey'
	    }
    },
    
    add:function(key, value){
    	Ext.Msg.alert('add'); 
    },
    
    update:function(key, value){
    	
    },
    
    get:function(key){
    	
    },
    
    remove:function(key){
    	
    },
    
    removeAll:function(key){
    	
    }
});
