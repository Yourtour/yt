Ext.define('YourTour.controller.route.ScheduleReferenceCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.model.RouteModel'],
    
    config: {
       refs: {
       		page:'#schedulereference',
       		sceneSchedulePage:'scenescheduleview',
       		foodSchedulePage:'foodscheduleview',
       		hotelSchedulePage:'hotelscheduleview',
       		
       		sehducleList:'#schedulereference #scheduleList'
       },
       
       control:{
    	   sehducleList:{
    	   	   itemtap:'onItemTap'
    	   },
    	   
    	   '#schedulereference #close':{
    	   	   tap:'backToRecommendReference'
    	   },
    	   
    	   '#scenescheduleview #close':{
    	   	   tap:'onBackTap'
    	   },
    	   
    	   '#foodscheduleview #close':{
    	   	   tap:'onBackTap'
    	   },
    	   
    	   '#hotelscheduleview #close':{
    	   	   tap:'onBackTap'
    	   },
    	   
    	   '#schedulereference #copy':{
    	   		tap:'onCopyTap'
    	   }
       },
       
       routes:{
        	'/route/reference/schedule/:routeId':'showPage'
       },
       
       store:null,
       
       routeId:null
    },
    
    backToRecommendReference:function(){
    	this.show('linerecommendview','YourTour.view.line.RecommendView');
    },
    
    onBackTap:function(){
    	this.showPage();	
    },
    
    onCopyTap:function(){
    	this.redirectTo('/route/schedule/' + this.routeId);
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
    	this.routeId = routeId;
    	this.show('schedulereference','YourTour.view.route.schedule.ScheduleReferenceListView');
		
    	var store = this.store, page=this.getPage();
    	var showView=function(){
    		var record = store.getAt(0);
	    	
	    	var scheduleList = page.down('#scheduleList');
	 	   	var schedules = [];
	 	   	record.schedules().each(function(schedule){
	 	   		schedules.push(schedule.data);
	 	   	});
	 	   	
	 	   	var scheduleStore = Ext.create('Ext.data.Store',{model:'YourTour.model.RouteScheduleModel', data:schedules});
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