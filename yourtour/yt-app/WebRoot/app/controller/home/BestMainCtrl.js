Ext.define('YourTour.controller.home.BestMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{
    	   bestListView:'#BestListView',
    	   bestList:'#BestListView #bestList',
       },
       
       control:{
    	   '#BestListView #toolbar':{
    	   	   tap:'onBackTap'
    	   },
    	   
    	   '#BestListView #bestList':{
    		   itemtap:'onItemTap'
    	   }
       },
       
       routes:{
       	'/main/place/best':'showPage'
       },
      
       store : null,
    },
    
    init: function(){
    	this.store = Ext.create('YourTour.store.BestListStore'); 
    },
    
    showPage:function(){
    	var page = Ext.create('YourTour.view.home.BestListView');
		Ext.ComponentManager.get('MainView').push(page);
		
    	var parent = this;
		var store = this.store;
		var success = function(){
			parent.getBestList().setStore(store);
		};
		
		store.load(success, this);
    },
    
    onItemTap:function(dataview, index, item, record,e){
    	this.redirectTo('/line/introduction/bestListView/1');
    },
    
    onBackTap:function(){
    	this.show('PlaceMainView','YourTour.view.home.PlaceMainView');
    },
});
