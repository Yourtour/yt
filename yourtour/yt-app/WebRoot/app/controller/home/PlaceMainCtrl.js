Ext.define('YourTour.controller.home.PlaceMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.view.home.LiveItemView', 'YourTour.view.home.ChatItemView','YourTour.view.home.AlongItemView','YourTour.view.home.TalentItemView'],
    config: {
       refs:{
       	   	placeMainView:'#PlaceMainView',
       	   	chatTitle:'#PlaceMainView #chatTitle',
       	   	liveTitle:'#PlaceMainView #liveTitle',
       	   	toolbar:'#PlaceMainView #toolbar',
			liveContent:'#PlaceMainView #liveContent',
			chatContent:'#PlaceMainView #chatContent',
			alongs:'#PlaceMainView #alongs',
			talents:'#PlaceMainView #talents'
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
			var model = store.getAt(0);
			
    		parent.show('PlaceMainView','YourTour.view.home.PlaceMainView');
    		parent.getToolbar().applyTitle(model.get('name'));
    		parent.getLiveTitle().setHtml('目前有' + model.get('liveNum') + '人参与行程直播互动.');
    		parent.getChatTitle().setHtml('目前有' + model.get('chatNum') + '人参与目的地聊天.');
    		
    		var lives = model.lives();
    		lives.each(function(live){
 	 	   		parent.getLiveContent().add(Ext.create('YourTour.view.home.LiveItemView',{itemId:'live', model:live}));
 	 	   	});
 	 	   	
 	 	   	var chats = model.chats();
 	 	   	chats.each(function(chat){
 	 	   		parent.getChatContent().add(Ext.create('YourTour.view.home.ChatItemView',{model:chat}));
 	 	   	});
 	 	   	
 	 	   	var alongs = model.alongs();
 	 	   	alongs.each(function(along){
				parent.getAlongs().add(Ext.create('YourTour.view.home.AlongItemView',{model:along})); 	 	   	
 	 	   	});
 	 	   	
 	 	   	var talents = model.talents();
 	 	   	talents.each(function(talent){
				parent.getTalents().add(Ext.create('YourTour.view.home.TalentItemView',{model:talent})); 	 	   	
 	 	   	});
		};
		
		store.load(success, this);
    }
});
