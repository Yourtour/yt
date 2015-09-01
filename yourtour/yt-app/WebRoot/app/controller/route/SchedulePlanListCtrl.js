Ext.define('YourTour.controller.route.SchedulePlanListCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.RouteStore', 'YourTour.model.RouteModel','YourTour.view.route.schedule.ScheduleListItem'],
    
    config: {
       refs: {
       		page:'#SchedulePlanListView',
       		schedulePlanList:'#SchedulePlanListView #schedulePlanList'
       },
       
       control:{
    	   schedulePlanList:{
    	   	   itemtap:'onItemTap'
    	   },
    	   
    	   '#SchedulePlanListView #toolbar':{
    	   	   tap:'onBackTap'
    	   }
       },
       
       routes:{
        	'/route/schedule/plan/:routeId':'showPage'
       },
       
       store:null
    },
    
    init:function(){
		this.store = Ext.create('YourTour.store.RouteStore', {storeId:'ScheduleListStore'});	    	
    },
    
    onEdit:function(dataview, record){
		var type = record.get('type');
		if(type == 'prepare'){
			this.redirectTo('/schedule/prepar');
		}else{
			this.redirectTo('/schedule/resource/selection');
		}
    },
    
    onItemTap:function(dataview, index, item, record,e){
    	if(record.get('type') == 'scene'){
    		this.redirectTo('/route/schedule/scene/' + index);
    	}else if(record.get('type') == 'food'){
    		this.redirectTo('/route/schedule/food/' + index);
    	}else if(record.get('type') == 'hotel'){
    		this.redirectTo('/route/schedule/hotel/' + index);
    	}
    },
    
    showPage:function(routeId){
    	this.show('SchedulePlanListView','YourTour.view.route.schedule.SchedulePlanListView');
		
    	var store = this.store, page=this.getPage();
    	var showView=function(){
    		var record = store.getAt(0);
	    	
	    	var scheduleList = page.down('#schedulePlanList');
	 	   	var schedules = [];
	 	   	record.schedules().each(function(schedule){
	 	   		schedules.push(schedule.data);
	 	   	});
	 	   	
	 	   	var scheduleStore = Ext.create('Ext.data.Store',{model:'YourTour.model.ScheduleModel', data:schedules});
	 	   	scheduleList.setStore(scheduleStore);
    	};
 	   	
 	   	store.load(showView,this);
    },
    
    onBackTap:function(){
    	this.show('ScheduleListView','YourTour.view.route.schedule.ScheduleListView');
    }
});