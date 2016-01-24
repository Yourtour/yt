Ext.define('YourTour.controller.home.TalentMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{
    	   talentListView:'#TalentListView',
    	   talentList:'#TalentListView #talentList'
       },
       
       routes:{
       	'/main/talent/list':'showPage'
       },
      
       store : null
    },
    
    init: function(){
    	this.store = Ext.create('YourTour.store.TalentListStore');
    },
    
    showPage:function(){
		var page = Ext.create('YourTour.view.home.TalentListView');
		Ext.ComponentManager.get('MainView').push(page);
		
		var parent = this;
		var store = this.store;
		var success = function(){
			parent.getTalentList().setStore(store);
		};
		
		store.load(success, this);
    }
});
