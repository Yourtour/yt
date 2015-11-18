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
    	var data = {};
    	var name = me.down('#name').getValue();
    	var startDate = new Date(me.down('#startDate').getValue());
    	data.id='0';
    	data.name = name;
    	data.startDate = startDate.getTime();
    	data.schedules = [];
    	
    	var store = this.getPlaceList().getStore();
    	store.each(function(item){
    		var schedule = {};
    		schedule.id = '0';
    		schedule.days = item.get('days');
    		schedule.places = item.get('places');
    		schedule.placeIds = item.get('placeIds');
    		data.schedules.push(schedule);
    	});
    	
    	Ext.Ajax.request({
    	    url : YourTour.util.Context.getContext('/routes/main_schedule/save'),
    	    method : "POST",
    	    params : Ext.JSON.encode(data),
    	    success : function(response) {
    	    	var data = Ext.JSON.decode(response.responseText);
    	    	if(data.errorCode != '0'){
    	    		Ext.Msg.alert(data.errorText);
    	    		return;
    	    	};
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
    		store = Ext.create("Ext.data.Store",{itemId:'schedulesStore', model:'YourTour.model.RouteScheduleModel'});
    		this.getPlaceList().setStore(store);
    	}
    	
    	var schedule = Ext.create('YourTour.model.RouteScheduleModel',{placeIds:place.get('graphId'), days:'1', places:place.get('name')});
    	
    	store.add(schedule);
    }
});
