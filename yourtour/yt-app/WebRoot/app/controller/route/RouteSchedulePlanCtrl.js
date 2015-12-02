Ext.define('YourTour.controller.route.RouteSchedulePlanCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.RouteStore', 'YourTour.model.RouteModel'],
    
    config: {
       refs: {
    	   routeSchedulePlanView:'#RouteSchedulePlanView',
    	   routeScheduleList:'#RouteSchedulePlanView #RouteScheduleList',
    	   toolbar:'#RouteSchedulePlanView #toolbar',
    	   
    	   routeActivityEditView:'#RouteActivityEditView',
       },
       
       control:{
    	   routeScheduleList:{
    		   itemtap:'onItemTap',
    		   itemlongtap:'onItemLongTap'
    	   },
    	   
    	   '#RouteActivityEditView #btnSave':{
    		   tap:'onSaveTap'
    	   },
    	   
    	   toolbar:{
    		   activate:'onToolbarActivate'
    	   },
    	   
    	   '#toolbar #newProvision':{
    		   tap : 'onNewProvisionTap'
    	   },
    	   
    	   '#toolbar #insertProvisiion':{
    		   tap : 'onInsertProvisionTap'
    	   },
    	   
    	   '#toolbar #editProvisiion':{
    		   tap : 'onEditProvisionTap'
    	   },
    	   
    	   '#toolbar #insertShcedule':{
    		   tap : 'onInsertScheduleTap'
    	   },
    	   
    	   '#toolbar #editShcedule':{
    		   tap : 'onEditScheduleTap'
    	   },
    	   
    	   '#toolbar #newActivity':{
    		   tap : 'onNewActivityTap'
    	   },
    	   
    	   '#toolbar #insertActivity':{
    		   tap : 'onInsertActivityTap'
    	   },
    	   
    	   '#toolbar #editActivity':{
    		   tap : 'onEditActivityTap'
    	   },
       },
       
       routes:{
        	'/route/edit/:routeId':'showPage'
       },
       
       store:null,
       
       index:0,
       
       routeId:null
    },
    
    init:function(){
    },
    
    showPage:function(routeId){
    	this.routeId = routeId;
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteSchedulePlanView'));
    	
    	this.store = this.getApplication().getController('route.RouteScheduleListCtrl').store;
    	var record = this.store.getAt(0);
    	
    	this.getRouteScheduleList().setStore(record.schedulesStore);
    },
    
    onItemTap:function(dataview, index, item, record,e){
    	this.index = index;
    	
    	if(item.longpressed){
    		item.longpressed = false;
    		return;
    	}
    	
    	var type = record.get('type');
    	if(type == 'ProvisionItem'){
    		this.redirectTo('/route/provision/' + this.routeId + '/' + this.index + '/edit');
    	}else if(type == 'Schedule'){
    		this.redirectTo('/route/schedule/' + this.routeId + '/' + this.index + '/edit');
    	}else if(type == 'ScheduleItem'){
    		this.redirectTo('/route/schedule/' + this.routeId + '/' + this.index + '/edit');
    	}
    },
    
    onItemLongTap:function(dataview, index, item, record, e){
    	this.getToolbar().show();
		this.getToolbar().getItems().each(function(item){
			if(item.attr == record.get('type')){
				item.show();
			}else{
				item.hide();
			}	
		});
    },
    
    onNewProvisionTap:function(){
    	var store = this.getRouteScheduleList().getStore();
    	var i;
    	var len = store.getData().length;
    	for(i = this.index ; i < len; i++){
    		if(store.getAt(i).get('type') == 'Schedule'){
    			break;
    		}
    	}
    	
    	this.redirectTo('/route/provision/' + this.routeId + '/' + i + '/new');
    },
    
    onInsertProvisionTap:function(){
    	this.redirectTo('/route/provision/' + this.routeId + '/' + this.index + '/new');
    },
    
    onInsertScheduleTap:function(){
    	this.redirectTo('/route/schedule/' + this.routeId + '/' + this.index + '/new');
    },
    
    onNewActivityTap:function(){
    	this.redirectTo('/resource/selection/' + this.routeId + '/' + this.index);
    },
    
    onInsertActivityTap:function(){
    	this.redirectTo('/resource/selection/' + this.routeId + '/' + this.index);
    },
    
    editActivity:function(record){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteActivityEditView'));
    	
    	var view = this.getRouteActivityEditView();
    	
    	var id = view.down('#id');
    	id.setValue(record.get('id'));
    	
    	var imageUrl = view.down('#imageUrl');
 	   	imageUrl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:150px'>");
 	   
 	   	var title = view.down('#title');
 	    title.setValue(record.get('title'));
    },
    
    addActivity:function(resource){
    	console.log(resource);
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
    
    /**
     * 保存日程安排
     */
    onSaveTap:function(){
    	var data = {};
    	
    	var store = this.getRouteScheduleList().getStore();
    	var schedule = store.getAt(this.index);
    	
    	var length = store.getTotalCount();
    	
    	data.schedule = {};
    	if(schedule.get('type') == 'Schedule'){
    		data.schedule.id = schedule.get('id');
    		
    		var count = 1;
    		var item;
    		for(var i = this.index; i < length; i++){
    			item = store.getAt(i);
    			if(item.get('id') != data.schedule.id && item.get('parentId') != data.schedule.id){
    				break;
    			}
    			
    			count += 1;
    		}
    		
    		data.index = count; 
    	}else{
    		data.schedule.id = schedule.get('parentId');
    		data.index = schedule.get('index');
    		
    		var item;
    		for(var i = this.index; i < length; i++){
    			item = store.getAt(i);
    			
    			if(item.get('parentId') != data.schedule.id) break;
    			
    			item.set('index', item.get('index') + 1);
    		}
    	}
    	
    	var view = this.getRouteActivityEditView();
    	
    	var id = view.down('#id');
    	console.log(id);
    	data.id=id.getValue();
    	
    	data.resource = {};
    	var resourceId = view.down('#resourceId');
    	data.resource.id = resourceId.getValue();
    	
    	var resourceType = view.down('#resourceType');
    	data.resource.type = resourceType.getValue();
    	 
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
    	    url : YourTour.util.Context.getContext('/routes/activity/save'),
    	    method : "POST",
    	    params : Ext.JSON.encode(data),
    	    success : function(response) {
    	    	var data = Ext.JSON.decode(response.responseText);
    	    	if(data.errorCode != '0'){
    	    		Ext.Msg.alert(data.errorText);
    	    		return;
    	    	};
    	    	
    	    	var activityId = data.data;
    	    	
    	    	Ext.ComponentManager.get('MainView').pop(2);
    	    },
    	    failure : function(response) {
    	        var respObj = Ext.JSON.decode(response.responseText);
    	        Ext.Msg.alert("Error", respObj.status.statusMessage);
    	    }
    	});
    }
});

