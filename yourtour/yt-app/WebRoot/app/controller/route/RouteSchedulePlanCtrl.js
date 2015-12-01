Ext.define('YourTour.controller.route.RouteSchedulePlanCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.RouteStore', 'YourTour.model.RouteModel'],
    
    config: {
       refs: {
    	   routeSchedulePlanView:'#RouteSchedulePlanView',
    	   routeScheduleList:'#RouteSchedulePlanView #RouteScheduleList',
    	   toolbar:'#RouteSchedulePlanView #toolbar',
    	   
    	   routeActivityEditView:'#RouteActivityEditView'
       },
       
       control:{
    	   routeScheduleList:{
    		   itemtap:'onItemTap',
    		   itemlongtap:'onItemLongTap'
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
    
    onActivityEdit:function(resource){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteActivityEditView'));
    	
    	/*var hourOptions = [], minOptions = [];
    	var value;
    	for(var index = 1; index < 24; index++){
    		if(index < 10){
    			value = '0' + index;
    		}else{
    			value = '' + index;
    		}
    		
    		hourOptions.push({text:value, value:value});
    	}
    	var hourSelect = this.getRouteActivityEditView().down('#hourSelect');
    	hourSelect.options = hourOptions;
    	
    	for(var index = 1; index < 60; index++){
    		if(index < 10){
    			value = '0' + index;
    		}else{
    			value = '' + index;
    		}
    		
    		minOptions.push({text:value, value:value});
    	}
    	var minSelect = this.getRouteActivityEditView().down('#minSelect');
    	minSelect.options = minOptions;*/
    }
});

