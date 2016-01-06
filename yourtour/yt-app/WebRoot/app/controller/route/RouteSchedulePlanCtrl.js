Ext.define('YourTour.controller.route.RouteSchedulePlanCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.RouteStore', 'YourTour.model.RouteModel'],
    
    config: {
       refs: {
    	   routeSchedulePlanView:'#RouteSchedulePlanView',
    	   routeScheduleList:'#RouteSchedulePlanView #RouteScheduleList',
    	   toolbar:'#RouteSchedulePlanView #toolbar',

    	   routeActivityEditView:'#RouteActivityEditView',
    	   routeProvisionEditView:'#RouteProvisionEditView',
    	   routeScheduleEditView:'#RouteScheduleEditView',
    	   routeDiscussView:'#RouteDiscussView',
    	   settingView:'#RouteSettingView',
		   placeChangeView:'#PlaceChangeView',

		   routeRecommendListView:'#RouteRecommendListView',
		   recommendRouteList:'#RouteRecommendListView #recommendRouteList',
		   routeRecommendIntroductionView:'#RouteRecommendIntroductionView',
		   recommendCarousel:'#RouteRecommendIntroductionView #recommendCarousel',
		   scheduleList:'#RouteRecommendIntroductionView #scheduleList',
       },
       
       control:{
    	   RouteSchedulePlanView:{
    		 destroy:'onRouteSchedulePlanViewDestroy'  
    	   },
    	   
    	   '#RouteSchedulePlanView #discuss':{
    		   tap:'onRouteDiscuss'
    	   },
    	   
    	   routeScheduleList:{
    		   itemtap:'onItemTap',
    		   itemlongtap:'onItemLongTap'
    	   },
    	   
    	   toolbar:{
    		   activate:'onToolbarActivate'
    	   },
    	   
    	   '#toolbar #newProvision':{
    		   tap : 'onNewProvisionItem'
    	   },
    	   
    	   '#RouteProvisionEditView #btnDelete':{
     	   		tap:"onDeleteProvisionItem"	
     	   },
     	   
     	   '#RouteProvisionEditView #save':{
     	   		tap:"onSaveProvisionItem"	
     	   },
     	   	
     	   '#toolbar #insertShcedule':{
    		   tap : 'onInsertSchedule'
    	   },
    	   	
    	   '#RouteScheduleEditView #btnSave':{
      		   tap:'onSaveSchedule'
      	   },
      	   	
    	   '#toolbar #newActivity':{
    		   tap : 'onNewScheduleActivity'
     	   },
    	   
     	   '#RouteActivityEditView #btnDelete':{
    	   		tap:"onDeleteScheduleActivity"	
    	   },
    	   	
    	   '#RouteActivityEditView #btnSave':{
     		   tap:'onSaveScheduleActivity'
     	   },

	   	   '#RouteSettingView #fromPlace':{
	   		   tap:"onFromPlaceTap"
	   	   },

		   '#RouteSettingView #toPlaces':{
			   tap:"onToPlacesTap"
		   },
	   	   
	   	   '#RouteSettingView #next':{
	   		   tap:'OnNextTap'
	   	   },

		   '#LineRecommendListView #btnCustomize':{
			   tap:'onRouteCustomizeTap'
		   },

		   '#RouteRecommendListView #recommendRouteList':{
			   itemtap:'onRecommendRouteItemTap'
		   },

		   '#RouteRecommendIntroductionView #recommendCarousel':{
			   activeitemchange:'onRecommendCarouselItemChange'
		   },

		   '#RouteRecommendIntroductionView #btnClone':{
			   tap:'onRouteCloneTap'
		   }
       },
       
       	store:null,
       
       	index:0,
       
       	routeId:null,

		route:null
    },
    
	createNewRoute:function(){
		Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteSettingView'));
	},

	loadStep1Info:function(info){
		Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteSettingView'));
		var view = this.getSettingView();

		var id = view.down('#id');
		id.setValue(info.get('id'));

		var name = view.down('#name');
		name.setValue(info.get('name'));

		var fromPlace = view.down('#fromPlace');
		fromPlace.setPair(info.get('fromPlace'));

		var toPlaces = view.down('#toPlaces');
		toPlaces.setPair(info.get('toPlaces'));

		var adultNum = view.down('#adultNum');
		adultNum.setValue(info.get('adultNum'));

		var childNum = view.down('#childNum');
		childNum.setValue(info.get('childNum'));

		var olderNum = view.down('#olderNum');
		olderNum.setValue(info.get('olderNum'));
	},

	getRoute:function(){
		return this.route;
	},

	/**
	 * 目的地选择
	 */
	onToPlacesTap:function(){
		var me = this;

    	this.redirectTo('/place/multiSelection');

		var controller = me.getApplication().getController('PlaceSelectionCtrl');
		controller.bindHandler('#btnOk','tap',function(){
			var ids = '', names = '';
			var selections = controller.getSelectedPlaces();
			selections.getItems().each(function(item){
				if(ids != ''){
					ids = ids + ',';
					names = names + ",";
				}

				ids = ids + item.model.get('id');
				names = names + item.model.get('name');
			});

			var toPlaces = me.getSettingView().down('#toPlaces');
			toPlaces.setValue(ids);
			toPlaces.setText(names);

			Ext.ComponentManager.get('MainView').pop();
		});
    },

	/**
	 * 出发地选择
	 */
	onFromPlaceTap:function(){
		var me = this;
    	me.redirectTo('/place/singleSelection');

		var controller = me.getApplication().getController('PlaceSelectionCtrl');
		controller.bindHandler('#placeList','itemtap',function(record, e, eOpts){
			var fromPlace = me.getSettingView().down('#fromPlace');
			fromPlace.setText(record.get('name'));
			fromPlace.setValue(record.get('id'));

			Ext.ComponentManager.get('MainView').pop();
		});
    },

	OnNextTap:function(){
    	var me = this;
    	var view = me.getSettingView();
    	route = {};

		var id = view.down('#id').getValue();
    	var name = view.down('#name').getValue();
    	var startDate = new Date(view.down('#startDate').getValue());
		var endDate = new Date(view.down('#endDate').getValue());
		var fromPlace = view.down('#fromPlace');
		var toPlaces = view.down('#toPlaces');
		var adultNum = view.down('#adultNum');
		var childNum = view.down('#childNum');
		var olderNum = view.down('#olderNum');

		route.id=id;
		route.name = name;
		route.startDate = startDate.getTime();
		route.endDate = endDate.getTime();
		route.fromPlace = fromPlace.getPair();
		route.toPlaces = toPlaces.getPair();
		route.adultNum = adultNum.getValue();
		route.childNum = childNum.getValue();
		route.olderNum = olderNum.getValue();

    	Ext.Ajax.request({
    	    url : YourTour.util.Context.getContext('/routes/main/save'),
    	    method : "POST",
    	    params : Ext.JSON.encode(route),
    	    success : function(response) {
    	    	var data = Ext.JSON.decode(response.responseText);
    	    	if(data.errorCode != '0'){
    	    		Ext.Msg.alert(data.errorText);
    	    		return;
    	    	};

				me.routeId = data.data;

				route.id=data.data;
				me.getRecommendRoutes(5, route.toPlaces);
    	    },
    	    failure : function(response) {
    	        var respObj = Ext.JSON.decode(response.responseText);
    	        Ext.Msg.alert("Error", respObj.status.statusMessage);
    	    }
    	});
    },

	/**
	 *
	 * @param duration
	 * @param places
	 */
	getRecommendRoutes:function(duration, places){
		duration = 5;
		var ids = '', names = '';
		var pArray = places.split('|');
		for(var index = 0; index < pArray.length; index++){
			if(index > 0){
				ids = ids + ',';
				names = names + ',';
			}

			var array = pArray[index].split(',');
			ids = ids + array[0];
			names = names + array[1];
		}

		var me = this;
		Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteRecommendListView'));

		var view = this.getRouteRecommendListView();
		var headerbar = view.down('#headerbar');
		headerbar.setTitle(names);

		var store = Ext.create('YourTour.store.AjaxStore', {storeId:'recommendItem',model:'YourTour.model.RouteModel'});
		var proxy = store.getProxy();
		proxy.setUrl(YourTour.util.Context.getContext('/routes/recommend/' + ids + '/' + duration));
		store.load(function(){
			me.getRecommendRouteList().setStore(store);
		})
	},

	/**
	 *
	 * @param dataview
	 * @param index
	 * @param item
	 * @param record
	 * @param e
	 */
	onRecommendRouteItemTap:function(dataview, index, item, record,e){
		var me = this;
		Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteRecommendIntroductionView'));
		var view = me.getRouteRecommendIntroductionView();
		view.setAttrs({record:record});

		var store = Ext.create('YourTour.store.AjaxStore', {storeId:'recommend', model:'YourTour.model.RouteModel'});
		var proxy = store.getProxy();
		proxy.setUrl(YourTour.util.Context.getContext('/routes/recommend/' + record.get('id')));
		store.load(function(){
			var record = store.first();
			var expertRecord = record.userStore.first();


			var image = view.down('#image');
			image.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "' style='width:100%; max-height:150px'>");

			var lineName = view.down('#lineName');
			lineName.setHtml(record.get('lineName'));

			var expertImage = view.down('#expertImage');
			expertImage.setHtml("<img src='" + YourTour.util.Context.getImageResource(expertRecord.get('imageUrl'), 'medium') + "' style='width:64px; height:px'>");

			var feature = view.down('#feature');
			feature.setHtml(Ext.String.ellipsis(record.get('feature'),70,false));

			var reason = view.down('#reason');
			reason.setHtml(Ext.String.ellipsis(record.get('reason'),70,false));
		})
	},

	/**
	 *
	 */
	onRouteCustomizeTap:function(){
		var me = this;
		var store = null;

		var routeId = route.id;
		Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteSchedulePlanView'));
		var showView=function(){
			var record = store.first();

			var scheduleList = me.getRouteScheduleList();
			scheduleList.setStore(record.schedulesStore);
		};

		store = Ext.create('YourTour.store.AjaxStore', {storeId:'recommendItem', model:'YourTour.model.RouteModel'});
		store.getProxy().setUrl(YourTour.util.Context.getContext('/routes/' + routeId +'/query'));
		store.load(showView,this);
	},

    /**
     * 
     */
    updateRouteSchedule:function(store){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteSchedulePlanView'));

		var me = this;
		me.store = store;

		var record = store.first();
		var scheduleList = me.getRouteScheduleList();
		scheduleList.setStore(record.schedulesStore);
    },
    
    /**
     * 
     */
    onItemTap:function(dataview, index, item, record,e){
    	this.index = index;
    	
    	if(item.longpressed){
    		item.longpressed = false;
    		return;
    	}
    	
    	this.getToolbar().hide();
    	var type = record.get('type');
    	if(type == 'ProvisionItem'){
    		this.editProvisionItem(record);
    	}else if(type == 'Schedule'){
    		this.editSchedule(record);
    	}else if(type == 'ScheduleItem'){
    		this.editScheduleActivity(record);
    	}
    },
    
    /**
     * 
     */
    onItemLongTap:function(dataview, index, item, record, e){
    	var type = record.get('type');
    	if(! (type == 'Provision' || type == 'Schedule')){
    		this.getToolbar().hide();
    		return;
    	}
    	
    	this.getToolbar().show();
		this.getToolbar().getItems().each(function(item){
			item.show();
			
			if(type == 'Provision' && (item.getItemId() == 'insertShcedule' || item.getItemId() == 'newActivity')){
				item.hide();
			}else if(type == 'Schedule' && item.getItemId() == 'newProvision'){
				item.hide();
			}
		});
    },
    
    /**
     * 新建准备事项
     */
    onNewProvisionItem:function(){
    	var store = this.getRouteScheduleList().getStore();
    	var i;
    	var len = store.getData().length;
    	for(i = this.index ; i < len; i++){
    		if(store.getAt(i).get('type') == 'Schedule'){
    			break;
    		}
    	}
    	
    	this.editProvisionItem();
    },
    
    /**
     * 编辑行程准备事项
     */
    editProvisionItem:function(provisionItem){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteProvisionEditView'));
    	
    	var me = this;
    	var view = me.getRouteProvisionEditView();
    	
    	var id = view.down('#id');
    	if(provisionItem != undefined){
	    	var title = view.down('#title');
	    	var memo = view.down('#memo');
	    	
	    	title.setValue(provisionItem.get('title'));
	    	memo.setValue(provisionItem.get('memo'));
	    	
	    	id.setValue(provisionItem.get('id'));
    	}else{
    		id.setValue('0');
    	}
    	
    	var routeId = view.down('#routeId');
    	routeId.setValue(this.routeId);
    },
    
    /**
     * 
     */
    onDeleteProvisionItem:function(){
    	var me = this;
    	
    	Ext.Ajax.request({
    	    url : YourTour.util.Context.getContext('/routes/provision/' + me.provision.get('id') + '/delete'),
    	    method : "GET",
    	    success : function(response) {
    	    	var respObj = Ext.JSON.decode(response.responseText);
    	    	if(respObj.errorCode != '0'){
    	    		Ext.Msg.alert(resp.errorText);
    	    		return;
    	    	};
    	    	
    	    	var scheduleStore = me.getRouteScheduleList().getStore();
    	    	var provision = scheduleStore.getAt(me.index);
	    	    scheduleStore.remove(provision);
    	    	
    	    	Ext.ComponentManager.get('MainView').pop();
    	    },
    	    
    	    failure : function(response) {
    	        var respObj = Ext.JSON.decode(response.responseText);
    	        Ext.Msg.alert("Error", respObj.status.statusMessage);
    	    }
    	});
    },
    
    /**
     * 
     */
    onSaveProvisionItem:function(record){
    	var me = this;
    	
    	var view = this.getRouteProvisionEditView();
    	var title = view.down('#title');
    	var memo = view.down('#memo');
    	var id = view.down('#id');
    	
    	var data = {};
    	data.routeId = this.routeId;
    	data.id = id.getValue();
    	
    	if(data.id == '0')
    		data.index = me.getNewIndex();
    	else
    		data.index = me.getIndex();
    			
    	data.title = title.getValue();
    	data.memo = memo.getValue();
    	
    	try{
	    	Ext.Ajax.request({
	    		url : YourTour.util.Context.getContext('/routes/' + this.routeId + '/provision/save'),
	    	    method : "POST",
	    	    data:Ext.JSON.encode(data),
	    	    success : function(response) {
	    	    	var respObj = Ext.JSON.decode(response.responseText);
	    	    	if(respObj.errorCode != '0'){
	    	    		Ext.Msg.alert(resp.errorText);
	    	    		return;
	    	    	};
	    	    	
	    	    	var scheduleStore = me.getRouteScheduleList().getStore();
	    	    	if(data.id == '0'){
		    	    	data.id = respObj.data;
		    	    	data.type='ProvisionItem';
		    	    	var schedule = Ext.create('YourTour.model.RouteScheduleModel',data);
				    	scheduleStore.insert(data.index,schedule);
		    	    }else{
		    	    	var provision = scheduleStore.getAt(me.index);
		    	    	provision.set('title',data.title);
		    	    	provision.set('memo',data.memo);
		    	    }
	    	    	
	    	    	Ext.ComponentManager.get('MainView').pop();
	    	    }
	    	});
    	}catch(e){
    		alert('错误' + e.message + '发生在' +   e.lineNumber + '行');
    	}
    },
    
    onInsertSchedule:function(){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteScheduleEditView'));
    },
    
    /**
     * 保存日程
     */
    onSaveSchedule:function(){
    	var me = this;
    	
    	var view = this.getRouteScheduleEditView();
    	var date = view.down('#date');
    	date.setValue(record.get('date'));
    	
    	var title = view.down('#title');
    	title.setValue(record.get('title'));
    	
    	var memo = view.down('#memo');
    	memo.setValue(record.get('memo'));
    	
    	Ext.Ajax.request({
    	    url : YourTour.util.Context.getContext('/routes/' + me.getRouteId() + '/schedule/save'),
    	    method : "POST",
    	    params : Ext.JSON.encode(data),
    	    success : function(response) {
    	    	var respObj = Ext.JSON.decode(response.responseText);
    	    	if(respObj.errorCode != '0'){
    	    		Ext.Msg.alert(respObj.errorText);
    	    		return;
    	    	};
    	    	
    	    	if(data.id == '0'){
	    	    	data.id = respObj.data;
	    	    	data.type='ScheduleItem';
	    	    	var schedule = Ext.create('YourTour.model.RouteScheduleModel',data);
			    	scheduleStore.insert(data.index,schedule);
			    	Ext.ComponentManager.get('MainView').pop(2);
	    	    }else{
	    	    	var activity = scheduleStore.getAt(me.index);
	    	    	activity.set('title',data.title);
	    	    	activity.set('memo',data.memo);
	    	    	Ext.ComponentManager.get('MainView').pop();
	    	    }
    	    },
    	    failure : function(response) {
    	        var respObj = Ext.JSON.decode(response.responseText);
    	        Ext.Msg.alert("Error", respObj.status.statusMessage);
    	    }
    	});
    },
    
    /**
     *编辑日程
     */
    editSchedule:function(record){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteScheduleEditView'));
    	
    	var view = this.getRouteScheduleEditView();
    	var date = view.down('#date');
    	date.setValue(record.get('date'));
    	
    	var title = view.down('#title');
    	title.setValue(record.get('title'));
    	
    	var memo = view.down('#memo');
    	memo.setValue(record.get('memo'));
    },
    
    /**
     * 编辑日程安排
     */
    editScheduleActivity:function(record){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteActivityEditView'));
    	
    	var view = this.getRouteActivityEditView();
    	
    	var id = view.down('#id');
    	id.setValue(record.get('id'));
    	
    	var resourceId = view.down('#resourceId');
    	resourceId.setValue(record.get('resourceId'));
    	
    	var resourceType = view.down('#resourceType');
    	resourceType.setValue(record.get('resourceType'));
    	
 	   	var title = view.down('#title');
 	    title.setValue(record.get('title'));
 	    
 	    var memo = view.down('#memo');
 	    memo.setValue(record.get('memo'));
 	    
 	    var startTime = record.get('startTime');

 	    var endTime = record.get('endTime');
    },
    
    /**
     * 新增日程安排
     */
    addScheduleActivity:function(resource){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteActivityEditView'));
    	
    	var view = this.getRouteActivityEditView();
    	
    	var resourceId = view.down('#resourceId');
    	resourceId.setValue(resource.get('id'));
    	
    	var resourceType = view.down('#resourceType');
    	resourceType.setValue(resource.get('type'));
    	
    	var imageUrl = view.down('#imageUrl');
 	   	imageUrl.setHtml("<img src='" + resource.get('imageUrl') + "' style='width:100%; max-height:150px'>");
 	   
 	   	var title = view.down('#title');
 	    title.setValue(resource.get('name'));
    },
    
    onNewScheduleActivity:function(){
    	this.redirectTo('/resource/selection');
    },
    
    /**
     * 保存日程安排
     */
    onSaveScheduleActivity:function(){
    	var me = this;
    	var scheduleStore = me.getRouteScheduleList().getStore();
    	
    	var data = {};
    	data.schedule = {};
    	
    	var item = scheduleStore.getAt(me.index);
    	if(item.get('type') == 'Schedule'){
    		data.schedule.id = item.get('id');
    	}else{
    		data.schedule.id = item.get('parentId');
    	}
    	
    	data.date = item.get('date');
    	
    	var view = this.getRouteActivityEditView();
    	data.resource = {};
    	var resourceId = view.down('#resourceId');
    	data.resource.id = resourceId.getValue();
    	
    	var resourceType = view.down('#resourceType');
    	data.resource.type = resourceType.getValue();
    	
    	var id = view.down('#id');
    	data.id=id.getValue();
    	if(data.id == '0'){
    		data.index = me.getNewIndex();
    	}else{
    		data.index = me.getIndex();
    	}
    	
    	var title = view.down('#title');
    	data.title = title.getValue();

    	var memo = view.down('#memo');
    	data.memo = memo.getValue();
    	
    	var startHour = view.down('#startHourSelect');
    	var startMin = view.down('#startMinSelect');
    	data.startTime = startHour.getValue() + ':' + startMin.getValue();
    	
    	var endHour = view.down('#endHourSelect');
    	var endMin = view.down('#endMinSelect');
    	data.endTime = endHour.getValue() + ':' + endMin.getValue();
    	
    	Ext.Ajax.request({
    	    url : YourTour.util.Context.getContext('/routes/' + this.routeId + '/activity/save'),
    	    method : "POST",
    	    params : Ext.JSON.encode(data),
    	    success : function(response) {
    	    	var respObj = Ext.JSON.decode(response.responseText);
    	    	if(respObj.errorCode != '0'){
    	    		Ext.Msg.alert(respObj.errorText);
    	    		return;
    	    	};
    	    	
    	    	if(data.id == '0'){
	    	    	data.id = respObj.data;
	    	    	data.type='ScheduleItem';
	    	    	var schedule = Ext.create('YourTour.model.RouteScheduleModel',data);
			    	scheduleStore.insert(data.index,schedule);
			    	Ext.ComponentManager.get('MainView').pop(2);
	    	    }else{
	    	    	var activity = scheduleStore.getAt(me.index);
	    	    	activity.set('title',data.title);
	    	    	activity.set('memo',data.memo);
	    	    	Ext.ComponentManager.get('MainView').pop();
	    	    }
    	    },
    	    failure : function(response) {
    	        var respObj = Ext.JSON.decode(response.responseText);
    	        Ext.Msg.alert("Error", respObj.status.statusMessage);
    	    }
    	});
    },
    
    onDeleteScheduleActivity:function(){
    	var me = this;
    	
    	var store = me.getRouteScheduleList().getStore();
    	var schedule = store.getAt(me.index);
    	
    	Ext.Ajax.request({
    	    url : YourTour.util.Context.getContext('/routes/activity/' + schedule.get('id') + '/delete'),
    	    method : "GET",
    	    success : function(response) {
    	    	var respObj = Ext.JSON.decode(response.responseText);
    	    	if(respObj.errorCode != '0'){
    	    		Ext.Msg.alert(resp.errorText);
    	    		return;
    	    	};
	    	    
    	    	var scheduleStore = me.getRouteScheduleList().getStore();
    	    	var activity = scheduleStore.getAt(me.index);
	    	    scheduleStore.remove(activity);
    	    	
    	    	Ext.ComponentManager.get('MainView').pop();
    	    },
    	    
    	    failure : function(response) {
    	        var respObj = Ext.JSON.decode(response.responseText);
    	        Ext.Msg.alert("Error", respObj.status.statusMessage);
    	    }
    	});
    },
    
    /**
     * 获取新增项目的位置索引
     */
    getNewIndex:function(){
    	var newIndex = 0; 
    	var store = this.getRouteScheduleList().getStore();
    	var schedule = store.getAt(this.index);
    	
    	var length = store.getData().length;
    	
    	var parentId;
    	if(schedule.get('type') == 'Schedule' || schedule.get('type') == 'Provision'){
    		parentId = schedule.get('id');
    		
    		var count = this.index;
    		var item;
    		for(var i = this.index; i < length; i++){
    			item = store.getAt(i);
    			if(item.get('id') != parentId && item.get('parentId') != parentId){
    				break;
    			}
    			
    			count += 1;
    		}
    		
    		newIndex = count; 
    	}else{
    		parentId = schedule.get('parentId');
    		newIndex = schedule.get('index');
    		
    		var item;
    		for(var i = this.index; i < length; i++){
    			item = store.getAt(i);
    			
    			if(item.get('parentId') != parentId) break;
    			
    			item.set('index', item.get('index') + 1);
    		}
    	}
    	
    	return newIndex;
    },
    
    onRouteSchedulePlanViewDestroy:function(){
    	//this.store.setData('');
    },
    
    onRouteDiscuss:function(){
    	var sessionId = '111';
    	this.redirectTo('/message/session/' + sessionId);
    },

	onRecommendCarouselItemChange:function(carousel, value, oldValue, eOpts){
		var me = this;
		var view = me.getRouteRecommendIntroductionView();
		var headerbar = view.down('#headerbar');

		if(value.getItemId() == 'scheduleList') {
			headerbar.setTitle('行程安排');
			var record = view.getAttrs().record;
			var routeId = record.get('id');

			var store = me.getScheduleList().getStore();
			if (!store) {
				var showView=function(){
					var record = store.first();

					var scheduleList = me.getScheduleList();
					scheduleList.setStore(record.schedulesStore);
				};

				store = Ext.create('YourTour.store.AjaxStore', {storeId:'recommendItem', model:'YourTour.model.RouteModel'});
				store.getProxy().setUrl(YourTour.util.Context.getContext('/routes/' + routeId +'/query'));
				store.load(showView,this);
			}
		}else{
			headerbar.setTitle('行程概要');
		}
	},

	onRouteCloneTap:function(){
		var me = this;
		var view = me.getRouteRecommendIntroductionView();
		var record = view.getAttrs().record;
		var sourceId = record.get('id');

		Ext.Ajax.request({
			url : YourTour.util.Context.getContext('/routes/clone/' + sourceId + '/' + me.routeId),
			method : "GET",
			success : function(response) {
				var respObj = Ext.JSON.decode(response.responseText);
				if(respObj.errorCode != '0'){
					Ext.Msg.alert(resp.errorText);
					return;
				};

				var store = Ext.create('YourTour.store.AjaxStore', {model:'YourTour.model.RouteModel'});
				store.getProxy().setUrl(YourTour.util.Context.getContext('/routes/' + me.routeId +'/query'));
				store.load(function(){
					me.updateRouteSchedule(store);
				},this);
			},

			failure : function(response) {
				var respObj = Ext.JSON.decode(response.responseText);
				Ext.Msg.alert("Error", respObj.status.statusMessage);
			}
		});

	}
});