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
    	var parent = this;
		var store = this.store;
		var success = function(){
			parent.show('BestListView','YourTour.view.home.BestListView');
			parent.getBestList().setStore(store);
		};
		
		store.load(success, this);
    },
    
    onBackTap:function(){
    	this.show('PlaceMainView','YourTour.view.home.PlaceMainView');
    },
});
