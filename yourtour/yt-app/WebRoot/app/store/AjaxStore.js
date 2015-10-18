Ext.define('YourTour.store.AjaxStore', {
    extend: 'Ext.data.Store',
    config:{
    	useDefaultXhrHeader: false,
    	
	    proxy:{
			type: 'ajax',
			useDefaultXhrHeader:false,
			noCache: false,
			actionMethods : {
				create : 'POST', //新增
	            read   : 'GET', // 查询
	            update : 'POST', //更新
	            destroy: 'GET' // 删除
			},
		    
			reader:{
				type:'json',
				rootProperty:'data'
			},
			
			headers:{
				//'Origin':'http://192.168.0.5'
			}
	    }
    }
});
