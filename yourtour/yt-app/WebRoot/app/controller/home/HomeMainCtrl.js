Ext.define('YourTour.controller.home.HomeMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.view.home.LiveItemView', 'YourTour.view.home.ChatItemView','YourTour.view.home.AlongItemView','YourTour.view.home.TalentItemView','YourTour.view.home.CarouselItemView'],
    config: {
       refs:{
       	   	homeMainView:'#HomeMainView',
       	   	headerbar:'#HomeMainView #headerbar',
       	   	chatList:'#HomeMainView #chatList',
       	   	liveTitle:'#HomeMainView #liveTitle',
			liveContent:'#HomeMainView #liveContent',
			alongView:'#HomeMainView #alongs',
			talentView:'#HomeMainView #talents',
			bestView:'#HomeMainView #bests',
			placeCarousel:'#HomeMainView #placeCarousel'
       },
       
       control:{
    		'#HomeMainView #change':{
    			tap:'onPlaceTap'
       		},
       		
       		bestView:{
    			itemtap:'onItemTap4Best'	
    		},
    		
       		'#HomeMainView #moreBest':{
       			tap:'onMoreBestTap'	
       		},
       		
       		talentView:{
       			itemtap:'onItemTap4Talents'	
       		},
       		
       		'#HomeMainView #moreTalent':{
       			tap:'onMoreTalentTap'	
       		},
       		
       		alongView:{
       			itemtap:'onItemTap4Along'	
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
    	this.store = Ext.create('YourTour.store.HomeStore');
    },
    
    showPage:function(){
    	YourTour.util.Context.mainview = this.getHomeMainView();
    	var parent = this;
		var store = this.store;
		var success = function(){
			var model = store.getAt(0);
			/*var carousels = model.carousels();
    		carousels.each(function(carousel){
 	 	   		parent.getPlaceCarousel().add(Ext.create('YourTour.view.home.CarouselItemView',{itemId:'carousel', model:carousel}));
 	 	   	});*/
    		
    		/*parent.getBestView().removeAll(true, true);
    		parent.getBestView().setModels(model.lines());*/
    		
    		/*parent.getTalentView().removeAll(true, true);
    		parent.getTalentView().setModels(model.talents());
    		
    		parent.getAlongView().removeAll(true, true);
    		parent.getAlongView().setModels(model.alongs()); 	 	   	
*/    		
    		//parent.getChatList().setStore(model.chatsStore);
		};
		
		store.load(success, this);
    },
    
    onCallback:function(record){
    	this.getHeaderbar().setTitle(record.get('name'));
    },
    
    onItemTap4Best:function(record){
    	this.redirectTo('/line/introduction/1');
    },
    
    onMoreBestTap:function(){
    	this.redirectTo('/home/best/list');
    },
    
    onItemTap4Talents:function(record){
    	this.redirectTo('/main/talent/list');
    },
    
    onMoreTalentTap:function(){
    	this.redirectTo('/main/talent/list');
    },

    onItemTap4Along:function(record){
    	this.redirectTo('/along/detail/1');
    },
    
    onMoreAlongTap:function(){
    	this.redirectTo('/along/list');
    },
    
    onPlaceTap:function(){
    	this.redirectTo('/place/change');
    }
});
