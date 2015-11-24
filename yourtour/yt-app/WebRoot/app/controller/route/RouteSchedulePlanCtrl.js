Ext.define('YourTour.controller.route.RouteSchedulePlanCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.RouteStore', 'YourTour.model.RouteModel'],
    
    config: {
       refs: {
    	   routeSchedulePlanView:'#RouteSchedulePlanView',
    	   routeScheduleList:'#RouteSchedulePlanView #RouteScheduleList',
    	   toolbar:'#RouteSchedulePlanView #toolbar'
       },
       
       control:{
    	   routeScheduleList:{
    		   itemtap:'onItemTap'
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
    	
    	this.store = this.getApplication().getController('route.RouteScheduleCtrl').store;
    	var record = this.store.getAt(0);
    	
    	this.getRouteScheduleList().setStore(record.schedulesStore);
    },
    
    onItemTap:function(dataview, index, item, record,e){
    	this.index = index;
    	
    	this.getToolbar().show();
    	this.getToolbar().getAt(0).hide();
		this.getToolbar().getAt(1).hide();
		this.getToolbar().getAt(2).hide();
		this.getToolbar().getAt(3).hide();
		this.getToolbar().getAt(4).hide();
		this.getToolbar().getAt(5).hide();
		this.getToolbar().getAt(6).hide();
		
    	if(record.get('type') == 'Provision'){
    		this.getToolbar().getAt(0).show();
    	}else if(record.get('type') == 'ProvisionItem'){
    		this.getToolbar().getAt(1).show();
    		this.getToolbar().getAt(2).show();
    	}else if(record.get('type') == 'Schedule'){
    		this.getToolbar().getAt(3).show();
    		this.getToolbar().getAt(4).show();
    	}else if(record.get('type') == 'ScheduleItem'){
    		this.getToolbar().getAt(5).show();
    		this.getToolbar().getAt(6).show();
    	}
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
    
    onEditProvisionTap:function(){
    	this.redirectTo('/route/provision/' + this.routeId + '/' + this.index + '/edit');
    },
    
    onInsertScheduleTap:function(){
    	this.redirectTo('/route/schedule/insert');
    },
    
    onNewActivityTap:function(){
    	this.redirectTo('/route/activity/new');
    },
    
    onInsertActivityTap:function(){
    	this.redirectTo('/route/activity/insert');
    }
});