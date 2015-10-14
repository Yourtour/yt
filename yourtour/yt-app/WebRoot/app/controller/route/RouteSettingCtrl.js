Ext.define('YourTour.controller.route.RouteSettingCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.RouteStore', 'YourTour.model.RouteModel'],
    config: {
       refs:{
    	   placeList:'#RouteSettingView #placeList'
       },
       
       control:{
       	   '#RouteSettingView #add':{
       	   	   tap:"addDestinationPlace"	
       	   },
       	   
       	   placeList:{
       		   itemdel:'onPlaceItemDelete'	
       	   },
       	
    	   "#RouteSettingView #close":{
    		   tap:"OnCloseClick"
    	   },
    	   
    	   '#RouteSettingView #place':{
    		   focus:"addStartPlace"
    	   },
    	   
    	   '#RouteSettingView #next':{
    		   tap:'OnNextClick'
    	   }
       },
       
       routes:{
        	'/route/new':'showPage'
       },
       
       store:null
    },
    
    init: function(){
    	this.store = Ext.create('YourTour.store.RouteStore');
    	var model = Ext.create('YourTour.model.RouteModel',{rowKey:'-1'});
    	this.store.add(model);
    },
    
    showPage:function(){
		Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteSettingView'));
		
		var route = this.store.getAt(0);
    	var store = route.schedulesStore;
		this.getPlaceList().setStore(store);
    },
    
    addDestinationPlace:function(){
    	this.redirectTo('/place/selection');
    },
    
    onPlaceItemDelete:function(record){
    	var store = this.getPlaceList().getStore();
    	store.remove(record);
    },
    
    addStartPlace:function(){
    	this.redirectTo('/place/selection');
    },
    
    OnNextClick:function(){
    	this.redirectTo('/line/recommend');
    },
    
    addPlace:function(place){
    	var store = this.getPlaceList().getStore();
    	
    	var schedule = Ext.create('YourTour.model.ScheduleModel',{name:place.get('name')});
    	store.add(schedule);
    }
});
