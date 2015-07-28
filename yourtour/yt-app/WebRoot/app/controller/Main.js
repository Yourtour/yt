Ext.define('YourTour.controller.Main', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       	refs:{
    	   	navigationBar:'#mainview #navigationBar'  
       	},
       
       	control:{
			'#mainview #btnHome':{
				tap:'onHomeTap'
			},
			
			'#mainview #btnRoute':{
				tap:'onRouteTap'			
			},
			
			'#mainview #btnCommunity':{
				tap:'onRouteTap'
			},
			
			'#mainview #btnPersonal':{
				tap:'onRouteTap'
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
    	this.show('homemain','YourTour.view.home.HomeMain');
    },
    
    onRouteTap:function(){
    	this.redirectTo("/main/route");
    }
});
