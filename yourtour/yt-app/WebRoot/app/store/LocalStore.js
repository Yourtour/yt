/**
 * 用来存储从服务器端获取的数据的本地缓存，这些缓存数据在用户退出客户端时也不会丢失
 */
Ext.define('YourTour.store.LocalStore', {
    extend: 'Ext.data.Store',
    
    config:{
    	fields: ['key', 'value'],
    	
    	 proxy: {
	        type: 'localstorage',
	        id  : 'YTKey'
	    }
    }
});
