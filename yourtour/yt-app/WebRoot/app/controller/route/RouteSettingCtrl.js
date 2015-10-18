Ext.define('YourTour.controller.route.RouteSettingCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.RouteStore', 'YourTour.model.RouteModel'],
    config: {
       refs:{
    	   placeList:'#RouteSettingView #placeList',
    	   view:'#RouteSettingView'	   
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
    	
    },
    
    showPage:function(){
		Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteSettingView'));
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
    	this.store = Ext.create('YourTour.store.RouteStore',{itemId:'newRouteStore',data:[]});
    	
    	var me = this.getView();
    	var name = me.down('#name').getValue();
    	var place = me.down('#place').getValue();
    	var model = Ext.create('YourTour.model.RouteModel',{rowKey:'-1', name:name, place:place});
    	model.schedules = this.getPlaceList().getStore();
    	model.schedules.sync();
    	
    	this.store.add(model);
    	console.log(this.store);
    	
    	this.redirectTo('/line/recommend');
    },
    
    addPlace:function(place){
    	var store = this.getPlaceList().getStore();
    	if(! store){
    		store = Ext.create("Ext.data.Store",{itemId:'schedulesStore', model:'YourTour.model.ScheduleModel'});
    		this.getPlaceList().setStore(store);
    	}
    	
    	var schedule = Ext.create('YourTour.model.ScheduleModel',{name:place.get('name')});
    	store.add(schedule);
    }
});
