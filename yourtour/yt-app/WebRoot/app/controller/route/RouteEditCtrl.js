Ext.define('YourTour.controller.route.RouteEditCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.RouteStore', 'YourTour.model.RouteModel'],
    config: {
       refs:{
    	   settingView:'#RouteSettingView',
    		   
    	   placeList:'#RouteSettingView #placeList',
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
    	var me = this.getSettingView();
    	var name = me.down('#name').getValue();
    	var model = Ext.create('YourTour.model.RouteModel',{graphid:'-1', name:name});
    	
    	var data = Ext.JSON.encode(model.data);
    	Ext.Ajax.request({
    	    url : YourTour.util.Context.getContext('/routes/main/save'),
    	    method : "POST",
    	    params : data,
    	    success : function(response) {
    	    	var data = Ext.JSON.decode(response.responseText);
    	    	if(data.errorCode != '0'){
    	    		Ext.Msg.alert(data.errorText);
    	    		return;
    	    	};
    	    	
    	    	me.redirectTo('/line/recommend');
    	    },
    	    failure : function(response) {
    	        var respObj = Ext.JSON.decode(response.responseText);
    	        Ext.Msg.alert("Error", respObj.status.statusMessage);
    	    }
    	});
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
