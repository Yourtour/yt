Ext.define('YourTour.controller.resource.ResourceSelectionCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{
       		placeselection:'#SelectionListView #placeselection',
       		typeselection:'#SelectionListView #typeselection',
       		orderselection:'#SelectionListView #orderselection',
       		
       		selectionList:'#SelectionListView #selectionList'
       },
       
       control:{
       		'#SelectionListView #toolbar':{
       	   	   	tap:'onBackTap'
       	 	},
       	 	
       	 	'#SelectionListView #selectionList':{
       	 		itemtap:'onListTap'
       	 	}
       },
       
       routes:{
        	'/schedule/resource/selection':'showResourceSelectionView'
       },
       
       store:null
    },
    
    init: function(){
    	this.store = Ext.create('YourTour.store.ResourceStore');
    },

    onBackTap:function(){
    	this.show('ScheduleListView','YourTour.view.route.schedule.ScheduleListView');
    },
    
    onListTap:function(dataView, index, target, record, e, eOpts){
    	this.redirectTo('/resource/plan/1');
    },
    
    showResourceSelectionView:function(){
    	this.show('SelectionListView','YourTour.view.resource.SelectionListView');
		
    	this.getSelectionList().setStore(this.store);
    }
});
