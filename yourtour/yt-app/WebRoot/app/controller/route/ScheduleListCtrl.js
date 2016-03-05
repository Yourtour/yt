Ext.define('YourTour.controller.route.ScheduleListCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.model.RouteModel'],
    
    config: {
       refs: {
       		page:'#ScheduleListView',
       		sceneSchedulePage:'SceneScheduleView',
       		foodSchedulePage:'FoodScheduleView',
       		hotelSchedulePage:'HotelScheduleView',
       		
       		scheduleList:'#ScheduleListView #scheduleList'
       },
       
       control:{
    	   newRoute:{
    		   tap:'newRoute'
    	   },
    	   
    	   scheduleList:{
    	   	   itemtap:'onItemTap'
    	   },
    	   
    	   '#ScheduleListView #plan':{
    		   tap:'onPlanTap'
    	   }
       },
       
       routes:{
        	'/route/schedule/:routeId':'showPage'
       },
       
       store:null
    },
    
    onPlanTap:function(){
    	this.redirectTo("/route/schedule/plan/1");	
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
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.schedule.ScheduleListView'));
		
    	var store = this.store, page=this.getPage();
    	var showView=function(){
    		var record = store.getAt(0);
	    	var imageUrlEl = page.down('#imageUrl');
	    	imageUrlEl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:150px'>");
    	
	    	var nameEl = page.down('#name');
	    	nameEl.setHtml(record.get('name'));
	    	
	    	var scheduleList = page.down('#scheduleList');
	 	   	var schedules = [];
	 	   	record.schedules().each(function(schedule){
	 	   		schedules.push(schedule.data);
	 	   	});
	 	   	
	 	   	var scheduleStore = Ext.create('Ext.data.Store',{model:'YourTour.model.RouteScheduleModel', data:schedules});
	 	   	scheduleList.setStore(scheduleStore);
    	};
 	   	
 	   	store.load(showView,this);
    }
});