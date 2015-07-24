Ext.define('YourTour.controller.route.ScheduleCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.RouteStore', 'YourTour.model.RouteModel','YourTour.view.route.schedule.ScheduleListItem'],
    
    config: {
       refs: {
       		page:'#scheduleListView',
       		sceneSchedulePage:'scenescheduleview',
       		foodSchedulePage:'foodscheduleview',
       		hotelSchedulePage:'hotelscheduleview',
       		
       		sehducleList:'#scheduleListView #scheduleList'
       },
       
       control:{
    	   newRoute:{
    		   tap:'newRoute'
    	   },
    	   
    	   sehducleList:{
    	   	   edit:'onEdit',
    	   	   itemtap:'onItemTap'
    	   },
    	   
    	   '#scenescheduleview #close':{
    	   	   tap:'onBackTap'
    	   },
    	   
    	   '#foodscheduleview #close':{
    	   	   tap:'onBackTap'
    	   },
    	   
    	   '#hotelscheduleview #close':{
    	   	   tap:'onBackTap'
    	   }
       },
       
       routes:{
        	'/route/schedule/:routeId':'showPage'
       },
       
       store:null
    },
    
    init:function(){
		this.store = Ext.create('YourTour.store.RouteStore');	    	
    },
    
    onBackTap:function(){
    	this.showPage();	
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
    		this.showSceneSchedulePage(dataview, index);
    	}else if(record.get('type') == 'food'){
    		this.showFoodSchedulePage(dataview, index);
    	}else if(record.get('type') == 'hotel'){
    		this.showHotelSchedulePage(dataview, index);
    	}
    },
    
    showPage:function(routeId){
    	this.show('scheduleListView','YourTour.view.route.schedule.ScheduleListView');
		
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
	 	   	
	 	   	var scheduleStore = Ext.create('Ext.data.Store',{model:'YourTour.model.ScheduleModel', data:schedules});
	 	   	scheduleList.setStore(scheduleStore);
    	};
 	   	
 	   	store.load(showView,this);
    },
    
    showSceneSchedulePage:function(dataview, index){
    	this.show('scenescheduleview','YourTour.view.route.schedule.SceneScheduleView');
    	
    	var store = this.getSehducleList().getStore();
    	var record = store.getAt(index);
    	
    	var page = this.getSceneSchedulePage();
    	var imageUrlEl = page.down('#imageUrl');
    	imageUrlEl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:150px'>");
    	
    	var resNameEl = page.down('#resName');
    	resNameEl.setHtml(record.get('resName'));

    	var addressEl = page.down('#address');
    	addressEl.setHtml('地址:' + record.get('address'));
    	
    	var phoneEl = page.down('#phone');
    	phoneEl.setHtml('电话:' + record.get('phone'));
    },
    
    showFoodSchedulePage:function(dataview, index){
    	this.show('foodscheduleview','YourTour.view.route.schedule.FoodScheduleView');
    	
    	var store = this.getSehducleList().getStore();
    	var record = store.getAt(index);
    	
    	var page = this.getFoodSchedulePage();
    	var imageUrlEl = page.down('#imageUrl');
    	imageUrlEl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:150px'>");
    	
    	var resNameEl = page.down('#resName');
    	resNameEl.setHtml(record.get('resName'));

    	var addressEl = page.down('#address');
    	addressEl.setHtml('地址:' + record.get('address'));
    	
    	var phoneEl = page.down('#phone');
    	phoneEl.setHtml('电话:' + record.get('phone'));
    },
    
    showHotelSchedulePage:function(dataview, index){
    	this.show('hotelscheduleview','YourTour.view.route.schedule.HotelScheduleView');
    	
    	var store = this.getSehducleList().getStore();
    	var record = store.getAt(index);
    	
    	var page = this.getHotelSchedulePage();
    	var imageUrlEl = page.down('#imageUrl');
    	imageUrlEl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:150px'>");
    	
    	var resNameEl = page.down('#resName');
    	resNameEl.setHtml(record.get('resName'));

    	var addressEl = page.down('#address');
    	addressEl.setHtml('地址:' + record.get('address'));
    	
    	var phoneEl = page.down('#phone');
    	phoneEl.setHtml('电话:' + record.get('phone'));
    }
});