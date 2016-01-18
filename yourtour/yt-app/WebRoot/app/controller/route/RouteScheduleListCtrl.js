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
		var me = this;

    	this.routeId = routeId;
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteScheduleListView'));
    	var view = me.getRouteScheduleListView();

		var options = {
			model:'YourTour.model.RouteModel',
			url:'/routes/' + routeId +'/query',
			success:function(store){
				me.store = store;

				var record = store.first();

				var imageEl = view.down('#imageUrl');
				imageEl.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "' style='width:100%; max-height:150px'>");

				var scheduleList = view.down('#RouteScheduleList');
				scheduleList.setStore(record.schedulesStore);
			}
		};
		me.getApplication().query(options);
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
		var scheduleView = me.getScheduleFormView();

		var options = {
			model:'YourTour.model.RouteActivityModel',
			url:'/routes/activity/' + record.get('id'),
			success:function(store){
				var activity = store.first();
				scheduleView.setData(activity);

				var headerbar = scheduleView.down('#headerbar');
				headerbar.setTitle(activity.get('title'));

				var services = scheduleView.down('#services');
				services.setStore(activity.servicesStore);
			}
		};
		me.getApplication().query(options);
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