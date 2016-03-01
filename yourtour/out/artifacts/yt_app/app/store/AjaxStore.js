Ext.define('YourTour.store.AjaxStore', {
    extend: 'Ext.data.Store',
    config:{
    	autoLoad: false,
    	loaded:false,
	    proxy:{
			type: 'ajax',
			noCache: false,
			/*actionMethods : {
				create : 'POST', //新增
	            read   : 'GET', // 查询
	            update : 'POST', //更新
	            destroy: 'GET' // 删除
			},*/
		    
			reader:{
				type:'json',
				rootProperty:'data'
			}
	    }
    }
});
