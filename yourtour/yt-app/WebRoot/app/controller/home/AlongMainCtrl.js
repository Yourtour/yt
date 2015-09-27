Ext.define('YourTour.controller.home.AlongMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{
    	   alongListView:'#AlongListView',
    	   alongList:'#AlongListView #alongList',
       },
       
       control:{
    	   '#AlongListView #toolbar':{
    	   	   tap:'onBackTap'
    	   },
       },
       
       routes:{
       	'/main/place/along':'showPage'
       },
      
       store : null,
    },
    
    init: function(){
    	this.store = Ext.create('YourTour.store.AlongListStore'); 
    },
    
    showPage:function(){
    	var parent = this;
		var store = this.store;
		var success = function(){
			parent.show('AlongListView','YourTour.view.home.AlongListView');
			parent.getAlongList().setStore(store);
		};
		
		store.load(success, this);
    },
    
    onBackTap:function(){
    	this.show('PlaceMainView','YourTour.view.home.PlaceMainView');
    },
});
