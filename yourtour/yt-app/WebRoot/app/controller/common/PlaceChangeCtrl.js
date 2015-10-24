Ext.define('YourTour.controller.common.PlaceChangeCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.view.common.PlaceListItemView'],
    config: {
       refs: {
       	   placeType:'#PlaceChangeView #placeType',
       	   placeList:'#PlaceChangeView #placeList'
       },
       
       control	:{
    	   placeType:{
    		   itemtap:'onItemTap4PlaceType'
    	   },
    	   
    	   placeList:{
    		   itemtap:'onItemTap4PlaceList'
    	   }
       },
       
       routes:{
        	'/place/change':'showPage'
       }
    },
    
    /**
     * 显示页面
     */
    showPage:function(){
		Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.common.PlaceChangeView'));
		
		var launchStore = this.getApplication().getController("Launch").store;
		this.getPlaceType().setStore(launchStore.getAt(0).placesStore);
    },
    
    /**
     * 
     */
    onItemTap4PlaceType: function(obj, index, target, record, e, eOpts){
    	var placeList = this.getPlaceList();
    	placeList.removeAll();
    	
    	var url = YourTour.util.Context.getContext('/place/' + record.get('rowKey') + '/query');
    	var placeStore = Ext.create('YourTour.store.PlaceStore');
    	placeStore.getProxy().setUrl(url);
    	
    	placeStore.load(function(records, operation, success){
    		placeStore.getData().each(function(model){
        		placeList.add(Ext.create('YourTour.view.common.PlaceListItemView',{target:placeList, record:model}));
        	});
    	});
    },
    
    /**
     * 
     */
    onItemTap4PlaceList: function(record, e, eOpts){
    	var mainview = Ext.ComponentManager.get('MainView');
    	mainview.pop();
    	
    	this.getApplication().getController('home.HomeMainCtrl').onCallback(record);
    }
});
