Ext.define('YourTour.controller.route.RouteScheduleListCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.RouteStore', 'YourTour.model.RouteModel'],
    
    config: {
       refs: {
    	   routeScheduleListView:'#RouteScheduleListView',
    	   schedulePlanList:'#RouteScheduleListView #RouteScheduleList',
    	   
    	   scheduleFormView:'#RouteScheduleFormView'
       },
       
       control:{
    	   schedulePlanList:{
    	   	   itemtap:'onItemTap'
    	   },
    	   
    	   '#RouteScheduleListView #edit':{
    		   tap:'onEditTap'
    	   },
    	   
    	   '#RouteScheduleFormView #resName':{
    		   tap:'onShowResourceView'
    	   },
    	   
    	   '#RouteScheduleFormView #address':{
    		   tap:'onShowResourceMap'
    	   },
    	   
    	   '#RouteScheduleFormView #services':{
    		   itemtap:'onScheduleServiceTap'
    	   },

		   '#RouteScheduleFormView #items':{
			   itemtap:'onScheduleActivityItemTap'
		   }
       },
       
       routes:{
        	'/route/load/:routeId':'showPage'
       },
       
       store:null,
       
       routeId : null,
       
       resource:null,
    },
    
    init:function(){
		this.store = Ext.create('YourTour.store.RouteStore', {storeId:'RouteDetailStore'});	    	
    },
    
    onEditTap:function(){
		var editController = this.getApplication().getController('route.RouteSchedulePlanCtrl');
		editController.updateRouteSchedule(this.store);
    },
    showPage:function(routeId){
    	this.routeId = routeId;
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteScheduleListView'));
    	
    	var view = this.getRouteScheduleListView();
    	var store = this.store;
    	store.setData('');
    	
    	var showView=function(){
    		var record = store.first();
    		
    		var imageEl = view.down('#imageUrl');
    		imageEl.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "' style='width:100%; max-height:150px'>");
    		
	    	var scheduleList = view.down('#RouteScheduleList');
	    	scheduleList.setStore(record.schedulesStore);
    	};
 	   	
    	store.getProxy().setUrl(YourTour.util.Context.getContext('/routes/' + routeId +'/query'));
 	   	store.load(showView,this);
    },
    
    onItemTap:function(dataview, index, item, record,e){
    	var type = record.get('resourceType');
    	if(type == 'SCENE' || type == 'FOOD' ||type == 'HOTEL'){
    		this.showRouteActivityInfo(record);
    	}
    },

    showRouteActivityInfo:function(record){
    	var me = this;
    	
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteScheduleFormView'));
    	
    	var store = Ext.create('YourTour.store.AjaxStore', {
    	    model: 'YourTour.model.RouteActivityModel',
    	});
    	
    	store.getProxy().setUrl(YourTour.util.Context.getContext('/routes/activity/' + record.get('id')));
    	store.load(function(){
    		var activity = store.first();

    		var scheduleView = me.getScheduleFormView();
			scheduleView.setData(activity);
    		
        	var headerbar = scheduleView.down('#headerbar');
        	headerbar.setTitle(activity.get('title'));
        	
        	var memo = scheduleView.down('#memo');
			memo.setText(activity.get('memo'));
        	
        	var time = scheduleView.down('#time');
			time.setText(activity.get('startTime') + ' - ' + record.get('endTime'));
        	
        	var resource = activity.resource().getAt(0);
        	me.resource = resource;
        	
        	var image = scheduleView.down('#image');
			image.setHtml("<img src='" + YourTour.util.Context.getImageResource(resource.get('imageUrl')) + "' style='width:100%; max-height:150px'>");
        	
        	var address = scheduleView.down('#address');
			address.setText(resource.get('address'));
        	
        	var phone = scheduleView.down('#phone');
			phone.setText(resource.get('phone'));
        	
        	var resName = scheduleView.down('#resName');
			resName.setText(resource.get('name'));
        	
        	var services = scheduleView.down('#services');
			services.setStore(activity.servicesStore);

			var items = scheduleView.down('#items');
			items.setStore(activity.itemsStore);
    	});
    },
    
    onShowResourceView:function(){
		var view = this.getScheduleFormView();
		var activity = view.getData();
		var resource = activity.resourceStore.first();
		var resourceController = this.getApplication().getController('ResourceMainCtrl');
		resourceController.showResourcePage(resource.get('type'), resource.get('id'));
    },
    
    onShowResourceMap:function(){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.resource.ResourceMapView'));
    	
    	var map = new BMap.Map('map');//指向map的容器
        map.centerAndZoom(new BMap.Point(121.491, 31.233), 11);
        window.setTimeout(function(){  
        		map.panTo(new BMap.Point(121.488032,31.239148));
        }, 
        2000);
    },
    
    onScheduleServiceTap:function(dataview, index, item, record,e){
		var controller = this.getApplication().getController('ServiceMainCtrl');
		controller.showExpertService(record, 'cancel')
    },

	onScheduleActivityItemTap:function(dataview, index, item, record,e){
		var controller = this.getApplication().getController('ResourceMainCtrl');
		controller.showResourceActivity(record);
	}
});