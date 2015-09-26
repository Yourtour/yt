Ext.define('YourTour.controller.home.PlaceMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.view.home.LiveItemView', 'YourTour.view.home.ChatItemView','YourTour.view.home.AlongItemView','YourTour.view.home.TalentItemView','YourTour.view.home.CarouselItemView'],
    config: {
       refs:{
       	   	placeMainView:'#PlaceMainView',
       	   	chatList:'#PlaceMainView #chatList',
       	   	liveTitle:'#PlaceMainView #liveTitle',
       	   	toolbar:'#PlaceMainView #toolbar',
			liveContent:'#PlaceMainView #liveContent',
			alongs:'#PlaceMainView #alongs',
			talents:'#PlaceMainView #talents',
			bestView:'#PlaceMainView #bestView',
			placeCarousel:'#PlaceMainView #placeCarousel'
				
       },
       
       control:{
       	   	'#HomeMain #btnSearch':{
       	   		tap:'onSearchTap'	
       	   	}
       },
       
       store : null,
       
       place:null
    },
    
    init: function(){
    	this.store = Ext.create('YourTour.store.PlaceStore'); 
    },
    
    onSearchTap:function(){
    	var parent = this;
		var store = this.store;
		var success = function(){
			parent.show('PlaceMainView','YourTour.view.home.PlaceMainView');
			
			var model = store.getAt(0);
			var carousels = model.carousels();
    		carousels.each(function(carousel){
 	 	   		parent.getPlaceCarousel().add(Ext.create('YourTour.view.home.CarouselItemView',{itemId:'carousel', model:carousel}));
 	 	   	});
    		
    		parent.getToolbar().applyTitle(model.get('name'));

    		parent.getBestView().removeAll(true, true);
    		parent.getBestView().setModels(model.bests());
    		
    		parent.getTalents().removeAll(true, true);
    		parent.getTalents().setModels(model.talents());
    		
    		parent.getAlongs().removeAll(true, true);
    		parent.getAlongs().setModels(model.alongs()); 	 	   	
    		
    		parent.getChatList().setStore(model.chatsStore);
		};
		
		store.load(success, this);
    }
});
