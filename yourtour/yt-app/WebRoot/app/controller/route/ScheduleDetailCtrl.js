Ext.define('YourTour.controller.route.ScheduleDetailCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.RouteStore', 'YourTour.model.RouteModel','YourTour.view.route.schedule.ScheduleListItem'],
    
    config: {
       refs: {
       		sceneSchedulePage:'SceneScheduleView',
       		foodSchedulePage:'FoodScheduleView',
       		hotelSchedulePage:'HotelScheduleView'
       },
       
       control:{
    	   '#FoodScheduleView #toolbar':{
    	   	   tap:'onBackTap'
    	   },
    	   
    	   '#HotelScheduleView #toolbar':{
    	   	   tap:'onBackTap'
    	   },
    	   
    	   '#SceneScheduleView #toolbar':{
    	   	   tap:'onBackTap'
    	   }
       },
       
       routes:{
        	'/route/schedule/scene/:index':'showSceneSchedulePage',
        	'/route/schedule/food/:index':'showFoodSchedulePage',
        	'/route/schedule/hotel/:index':'showHotelSchedulePage'
       },
       
       store:null
    },
    
    init:function(){
		this.store = Ext.create('YourTour.store.RouteStore');	    	
    },
    
    showSceneSchedulePage:function(index){
    	this.show('SceneScheduleView','YourTour.view.route.schedule.SceneScheduleView');
    	
    	var store = this.store;
    	
    	console.log(store.getAt(0).getSchedules());
		var record = store.getAt(0).schedulesStore.getAt(index);
    	
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
    
    showFoodSchedulePage:function(index){
    	this.show('FoodScheduleView','YourTour.view.route.schedule.FoodScheduleView');
    	
    	var store = this.store;
    	var record = store.getAt(0).schedulesStore.getAt(index);
    	
    	var page = this.getFoodSchedulePage();
    	var imageUrlEl = page.down('#imageUrl');
    	console.log(record.get('imageUrl'));
    	imageUrlEl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:150px'>");
    	
    	var resNameEl = page.down('#resName');
    	resNameEl.setHtml(record.get('resName'));

    	var addressEl = page.down('#address');
    	addressEl.setHtml('地址:' + record.get('address'));
    	
    	var phoneEl = page.down('#phone');
    	phoneEl.setHtml('电话:' + record.get('phone'));
    },
    
    showHotelSchedulePage:function(index){
    	this.show('HotelScheduleView','YourTour.view.route.schedule.HotelScheduleView');
    	
    	var store = this.store;
    	var record = store.getAt(0).schedulesStore.getAt(index);
    	
    	var page = this.getHotelSchedulePage();
    	var imageUrlEl = page.down('#imageUrl');
    	imageUrlEl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:150px'>");
    	
    	var resNameEl = page.down('#resName');
    	resNameEl.setHtml(record.get('resName'));

    	var addressEl = page.down('#address');
    	addressEl.setHtml('地址:' + record.get('address'));
    	
    	var phoneEl = page.down('#phone');
    	phoneEl.setHtml('电话:' + record.get('phone'));
    },
    
    onBackTap:function(){
    	this.show('ScheduleListView','YourTour.view.route.schedule.ScheduleListView');
    }
});