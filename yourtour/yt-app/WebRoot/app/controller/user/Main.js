Ext.define('YourTour.controller.user.Main', {
    extend: 'Ext.app.Controller',
    config: {
       refs:{
    	   register:'#register'
       },
       
       control:{
    	   '#loginview #btnRegister':{
    		   tap:'onRegister'
    	   },
    	   
    	   '#loginview #btnLogin':{
    		   tap:'doLogin'
    	   },
    	   
    	   '#btnRegisterProfile':{
    		   tap:'doRegisterProfile'
    	   },
    	   
    	   '#btnRegisterAccount':{
    		   tap:'doRegisterAccount'
    	   },
    	   
    	   '#registerprofile #btnRegisterDone':{
    		   tap:'doneRegister'
    	   }
       },
    },
    
    onRegister:function(){
    	this.doRegister();
    },
    
    doRegister:function(){
    	Ext.Viewport.setActiveItem(Ext.create('YourTour.view.user.Register'));
    },
    
    doLogin:function(){
    	Ext.Viewport.setActiveItem(Ext.create('YourTour.view.Main'));
    },
    
    doRegisterAccount:function(){
    	this.getRegister().setActiveItem('registeraccount');
    },
    
    doRegisterProfile:function(){
    	this.getRegister().setActiveItem('registerprofile');
    },
    
    doneRegister:function(){
    	Ext.Viewport.setActiveItem(Ext.create('YourTour.view.Main'));
    }
});
