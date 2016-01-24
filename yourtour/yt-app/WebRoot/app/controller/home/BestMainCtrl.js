Ext.define('YourTour.controller.home.BestMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{
    	   bestList:'#BestListView #bestList'
       },
       
       control:{
    	   '#BestListView #bestList':{
    		   itemtap:'onItemTap'
    	   }
       },
       
       routes:{
       	'/home/best/list':'showListView'
       },
      
       store : null
    },
    
    init: function(){
    	this.store = Ext.create('YourTour.store.BestListStore'); 
    },
    
    showListView:function(){
    	var page = Ext.create('YourTour.view.home.BestListView');
		Ext.ComponentManager.get('MainView').push(page);
		
    	var parent = this;
		var store = this.store;
		var success = function(){
			parent.getBestList().setStore(store);
		};
		
		store.load(success, this);
    },
    
    onItemTap:function(dataview, index, item, record){
    	this.redirectTo('/line/introduction/1');
    }
});
