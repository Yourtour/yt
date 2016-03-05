Ext.define('YourTour.controller.route.ScheduleDetailCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.model.RouteModel'],
    
    config: {
       refs: {
       		sceneSchedulePage:'SceneScheduleView',
       		foodSchedulePage:'FoodScheduleView',
       		hotelSchedulePage:'HotelScheduleView'
       },
       
       routes:{
        	'/route/schedule/scene/:index':'showSceneSchedulePage',
        	'/route/schedule/food/:index':'showFoodSchedulePage',
        	'/route/schedule/hotel/:index':'showHotelSchedulePage'
       },
       
       store:null
    },
    
    showSceneSchedulePage:function(index){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.schedule.SceneScheduleView'));
    	
    	var store = this.store;
    	
		var record = store.getAt(0).schedulesStore.getAt(index);
    	
    	var page = this.getSceneSchedulePage();
    	var imageUrlEl = page.down('#imageUrl');
    	imageUrlEl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:150px'>");
    	
    	var resNameEl = page.down('#resName');
    	resNameEl.setHtml(record.get('resName'));

    	var addressEl = page.down('#address');
    	addressEl.setHtml(record.get('address'));
    	
    	var phoneEl = page.down('#phone');
    	phoneEl.setHtml(record.get('phone'));
    },
    
    showFoodSchedulePage:function(index){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.schedule.FoodScheduleView'));
    	
    	var store = this.store;
    	var record = store.getAt(0).schedulesStore.getAt(index);
    	
    	var page = this.getFoodSchedulePage();
    	var imageUrlEl = page.down('#imageUrl');
    	imageUrlEl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:150px'>");
    	
    	var resNameEl = page.down('#resName');
    	resNameEl.setHtml(record.get('resName'));

    	var addressEl = page.down('#address');
    	addressEl.setHtml(record.get('address'));
    	
    	var phoneEl = page.down('#phone');
    	phoneEl.setHtml(record.get('phone'));
    },
    
    showHotelSchedulePage:function(index){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.schedule.HotelScheduleView'));

    	var store = this.store;
    	var record = store.getAt(0).schedulesStore.getAt(index);
    	
    	var page = this.getHotelSchedulePage();
    	var imageUrlEl = page.down('#imageUrl');
    	imageUrlEl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:150px'>");
    	
    	var resNameEl = page.down('#resName');
    	resNameEl.setHtml(record.get('resName'));

    	var addressEl = page.down('#address');
    	addressEl.setHtml(record.get('address'));
    	
    	var phoneEl = page.down('#phone');
    	phoneEl.setHtml(record.get('phone'));
    }
});