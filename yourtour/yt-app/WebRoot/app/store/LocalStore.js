Ext.define('YourTour.store.LocalStore', {
    extend: 'Ext.data.Store',
    
    getUrl:function(url){
    	return "" + url;
    }
});
