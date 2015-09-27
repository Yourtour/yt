Ext.define('YourTour.controller.home.TalentMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{
    	   talentListView:'#TalentListView',
    	   talentList:'#TalentListView #talentList',
       },
       
       control:{
    	   '#TalentListView #toolbar':{
    	   	   tap:'onBackTap'
    	   },
       },
       
       routes:{
       	'/main/place/talent':'showPage'
       },
      
       store : null,
    },
    
    init: function(){
    	this.store = Ext.create('YourTour.store.TalentListStore'); 
    },
    
    showPage:function(){
    	var parent = this;
		var store = this.store;
		var success = function(){
			parent.show('TalentListView','YourTour.view.home.TalentListView');
			parent.getTalentList().setStore(store);
		};
		
		store.load(success, this);
    },
    
    onBackTap:function(){
    	this.show('PlaceMainView','YourTour.view.home.PlaceMainView');
    },
});
