Ext.define('YourTour.controller.user.UserListCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{
    	   page:'#UserListView',
    	   
    	   userList :'#UserListView #userList'
       },
       
       control:{
       },
       
       routes:{
       		'/user/list':'showPage'	
       },
       
       store : null
    },
    
    showPage:function(){
    	var me = this;
    	var userList = me.getUserList();
    	
    	var store = me.store;
    	var showView=function(){
			userList.setStore(store);
    	};
 	   	
 	   	store.load(showView,this);
    }
});
