Ext.define('YourTour.controller.route.RouteScheduleListCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.RouteStore', 'YourTour.model.RouteModel'],
    
    config: {
       refs: {
    	   routeScheduleListView:'#RouteScheduleListView',
    	   schedulePlanList:'#RouteScheduleListView #RouteScheduleList',
    	   
    	   scheduleFormView:'#RouteScheduleFormView',

		   routeCheckinView:'#RouteCheckinView'
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
    	   
		   '#RouteScheduleFormView #items':{
			   itemtap:'onScheduleActivityItemTap'
		   },

		   '#RouteScheduleFormView #btnCheckin':{
			   tap:'doRouteCheckin'
		   },

		   '#RouteCheckinView #imgAdd':{
			   tap:'addCheckInImage'
		   }
       },
       
       routes:{
        	'/route/load/:routeId':'showPage'
       },
       
       store:null,
       
       routeId : null,
       
       resource:null
    },
    
    init:function(){
		this.store = Ext.create('YourTour.store.RouteStore', {storeId:'RouteDetailStore'});	    	
    },
    
    onEditTap:function(){
		var editController = this.getApplication().getController('route.RouteSchedulePlanCtrl');
		editController.updateRouteSchedule(this.store);
    },

    showPage:function(data){
		var me = this;
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteScheduleListView'));

		var options = {
			model: 'YourTour.model.RouteModel',
			url: '/routes/' + data + '/query',
			success: function (store) {
				me.showRouteScheduleInfo(store);
			}
		};
		me.getApplication().query(options);
    },

	/**
	 * 显示行程计划信息
	 * @param store
	 */
	showRouteScheduleInfo:function(store){
		var me = this;
		var view = me.getRouteScheduleListView();

		me.store = store;
		var record = store.first();
		me.routeId = record.get('id');

		var headerbar = view.down('#headerbar');
		headerbar.setTitle(record.get('name'));

		var imageEl = view.down('#imageUrl');
		imageEl.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "' style='width:100%; max-height:150px'>");

		var schedulesStore = record.schedulesStore;
		var scheduleList = view.down('#RouteScheduleList');

		var type;
		schedulesStore.each(function(record){
			type = record.get('type');

			record.set('status', 'new');

			if(type == 'Provision' || type == 'ProvisionItem'){
				record.set('viewhidden', true);
			}
		});
		scheduleList.setStore(schedulesStore);
	},
    
    onItemTap:function(dataview, index, item, record){
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

				var items = scheduleView.down('#items');
				items.setStore(activity.itemsStore);
				if(items.getStore().getAllCount() == 0){
					scheduleView.down('#activityItem').setText('没有具体的行程安排。');
				}else{
					scheduleView.down('#activityItem').setText('');
				}
			}
		};
		me.getApplication().query(options);
    },
    
    onShowResourceView:function(){
		var view = this.getScheduleFormView();
		var activity = view.getData();
		var resource = activity.resourceStore.first();
		var resourceController = this.getApplication().getController('ResourceMainCtrl');
		resourceController.showResourcePage(resource);
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
    
    onScheduleServiceTap:function(dataview, index, item, record){
		var controller = this.getApplication().getController('ServiceMainCtrl');
		controller.showExpertService(record, 'cancel')
    },

	onScheduleActivityItemTap:function(dataview, index, item, record){
		var controller = this.getApplication().getController('ResourceMainCtrl');
		controller.showResourceActivity(record);
	},

	doRouteCheckin:function(){
		Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteCheckinView'));
	},

	addCheckInImage:function(){
		YourTour.util.Context.imageCapture(function(){
			alert('afafa');
		});
	}
});