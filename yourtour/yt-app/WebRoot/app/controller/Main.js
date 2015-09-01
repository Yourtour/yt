Ext.define('YourTour.controller.Main', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       	refs:{
    	   	navigationBar:'#MainView #navigationBar'  
       	},
       
       	control:{
			'#MainView #btnHome':{
				tap:'onHomeTap'
			},
			
			'#MainView #btnRoute':{
				tap:'onRouteTap'			
			},
			
			'#MainView #btnPersonal':{
				tap:'onPersonalTap'
			}
       },
       
       routes:{
       		'homepage':'showHomePage'
       }
    },
    
    init:function(){
    },
    
    showHomePage:function(){
    	Ext.Viewport.setActiveItem(Ext.create('YourTour.view.MainView'));
    	
    	this.onHomeTap();
    },
    
    onHomeTap:function(){
    	this.show('HomeMain','YourTour.view.home.HomeMain');
    },
    
    onRouteTap:function(){
    	this.redirectTo("/main/route");
    }
});
