Ext.define('YourTour.controller.Main', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       	refs:{
    	   	navigationBar:'#mainview #navigationBar'  
       	},
       
       	control:{
			'#mainview #btnHome':{
				tap:'onRouteTap'
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
       }
    },
    
    onRouteTap:function(){
    	this.redirectTo("/main/route");
    }
});
