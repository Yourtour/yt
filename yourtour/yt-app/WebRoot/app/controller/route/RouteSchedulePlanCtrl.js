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
    	   
    	   '#toolbar #insertShcedule':{
    		   tap : 'onInsertScheduleTap'
    	   },
    	   
    	   '#toolbar #newActivity':{
    		   tap : 'onNewActivityTap'
    	   },
    	   
    	   '#toolbar #insertActivity':{
    		   tap : 'onInsertActivityTap'
    	   }
       },
       
       routes:{
        	'/route/edit/:routeId':'showPage'
       },
       
       store:null
    },
    
    init:function(){
    },
    
    showPage:function(routeId){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteSchedulePlanView'));
    	
    	this.store = this.getApplication().getController('route.RouteScheduleCtrl').store;
    	var record = this.store.getAt(0);
    	
    	this.getRouteScheduleList().setStore(record.schedulesStore);
    },
    
    onItemTap:function(dataview, index, item, record,e){
    	this.getToolbar().show();
    	this.getToolbar().getAt(0).hide();
		this.getToolbar().getAt(1).hide();
		this.getToolbar().getAt(2).hide();
		this.getToolbar().getAt(3).hide();
		this.getToolbar().getAt(4).hide();
		
    	if(record.get('type') == 'Provision'){
    		this.getToolbar().getAt(0).show();
    	}else if(record.get('type') == 'ProvisionItem'){
    		this.getToolbar().getAt(1).show();	
    	}else if(record.get('type') == 'Schedule'){
    		this.getToolbar().getAt(2).show();
    		this.getToolbar().getAt(3).show();
    	}else if(record.get('type') == 'ScheduleItem'){
    		this.getToolbar().getAt(4).show();
    	}
    },
    
    onNewProvisionTap:function(){
    	this.redirectTo('/route/provision/new');
    },
    
    onInsertProvisionTap:function(){
    	this.redirectTo('/route/provision/insert');
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