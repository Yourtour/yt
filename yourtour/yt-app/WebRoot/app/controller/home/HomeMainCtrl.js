Ext.define('YourTour.controller.home.HomeMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.view.home.LiveItemView', 'YourTour.view.home.ChatItemView','YourTour.view.home.AlongItemView','YourTour.view.home.TalentItemView','YourTour.view.home.CarouselItemView'],
    config: {
       refs:{
       	   	homeMainView:'#HomeMainView',
       	   	chatList:'#HomeMainView #chatList',
       	   	liveTitle:'#HomeMainView #liveTitle',
			liveContent:'#HomeMainView #liveContent',
			alongs:'#HomeMainView #alongs',
			talents:'#HomeMainView #talents',
			bestView:'#HomeMainView #bests',
			placeCarousel:'#HomeMainView #placeCarousel'
       },
       
       control:{
       	   	bestView:{
    			onGridItemTap:'onBestGridItemTap'	
    		},
    		
       		'#HomeMainView #moreBest':{
       			tap:'onMoreBestTap'	
       		},
       		
       		talents:{
       			onGridItemTap:'onTalentGridItemTap'	
       		},
       		
       		'#HomeMainView #moreTalent':{
       			tap:'onMoreTalentTap'	
       		},
       		
       		alongs:{
       			onGridItemTap:'onAlongGridItemTap'	
       		},
       		
       		'#HomeMainView #moreAlong':{
       			tap:'onMoreAlongTap'	
       		}
       },
       
       routes:{
          	'/main/home':'showPage'
       },
       
       store : null,
       
       place:null
    },
    
    init: function(){
    	this.store = Ext.create('YourTour.store.PlaceStore');
    },
    
    showPage:function(){
    	YourTour.util.Context.mainview = this.getHomeMainView();
    	var parent = this;
		var store = this.store;
		var success = function(){
			var model = store.getAt(0);
			var carousels = model.carousels();
    		carousels.each(function(carousel){
 	 	   		parent.getPlaceCarousel().add(Ext.create('YourTour.view.home.CarouselItemView',{itemId:'carousel', model:carousel}));
 	 	   	});
    		
    		parent.getBestView().removeAll(true, true);
    		parent.getBestView().setModels(model.bests());
    		
    		parent.getTalents().removeAll(true, true);
    		parent.getTalents().setModels(model.talents());
    		
    		parent.getAlongs().removeAll(true, true);
    		parent.getAlongs().setModels(model.alongs()); 	 	   	
    		
    		parent.getChatList().setStore(model.chatsStore);
		};
		
		store.load(success, this);
    },
    
    onBestGridItemTap:function(index, record){
    },
    
    onMoreBestTap:function(){
    	this.redirectTo('/main/place/best');
    },
    
    onTalentGridItemTap:function(index, record){
    },
    
    onMoreTalentTap:function(){
    	this.redirectTo('/main/place/talent');
    },

    onAlongGridItemTap:function(index, record){
    },
    
    onMoreAlongTap:function(){
    	this.redirectTo('/main/place/along');
    }
});
