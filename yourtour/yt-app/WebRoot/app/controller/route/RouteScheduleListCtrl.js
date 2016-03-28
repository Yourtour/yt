Ext.define('YourTour.controller.route.RouteScheduleListCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:[ 'YourTour.model.RouteModel'],
    
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
       
       routeId : null,
       resource:null
    },

	showPage:function(data){
		var me = this;
		Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteScheduleListView'));

		if(data instanceof YourTour.store.AjaxStore){
			me.showRouteScheduleInfo(data);
		}else {
			var options = {
				model: 'YourTour.model.RouteModel',
				url: '/routes/' + data + '/query',
				success: function (store) {
					me.showRouteScheduleInfo(store);
				}
			};
			me.getApplication().query(options);
		}
	},

	/**
	 * 显示行程计划信息
	 * @param store
	 */
	showRouteScheduleInfo:function(store){
		var me = this,
			view = me.getRouteScheduleListView(),
			headerbar = view.down('#headerbar'),
			routePanel = view.down('#routePanel');

		view.hideProcessing();

		me.store = store;
		var record = store.first();
		me.routeId = record.get('id');

		var headerbar = view.down('#headerbar');
		headerbar.setTitle(record.get('name'));

		var url = YourTour.util.Context.getImageResource(record.get('imageUrl'));
		var style = {};
		style['background-image'] = 'url(' + url + ')';
		style['background-repeat'] = 'no-repeat';
		style['background-position'] = 'center center';
		style['background-size'] = '100% 100%';
		routePanel.setStyle(style);

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

	onEditTap:function(){
		var editController = this.getApplication().getController('route.RouteSchedulePlanCtrl');
		editController.updateRouteSchedule(this.store);
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
		resourceController.showResourceFormView(resource);
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