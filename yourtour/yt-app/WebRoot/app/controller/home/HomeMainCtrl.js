Ext.define('YourTour.controller.home.HomeMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.view.home.LiveItemView', 'YourTour.view.home.ChatItemView','YourTour.view.home.TalentItemView','YourTour.view.home.CarouselItemView'],
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
    		'#HomeMainView #btnPlace':{
    			tap:'selectHomePlace'
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
    },
    
    showPage:function() {
		YourTour.util.Context.mainview = this.getHomeMainView();
	},

	selectHomePlace:function(){
		var controller = this.getApplication().getController('PlaceMainCtrl');
		controller.showMainPage(6, '黄山');
    }
});
