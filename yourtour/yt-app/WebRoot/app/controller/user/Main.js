Ext.define('YourTour.controller.user.Main', {
    extend: 'Ext.app.Controller',
    config: {
       refs:{
    	   loginMainView:'#LoginMainView'
       },
       
       control:{
    	   '#LoginView #btnRegister':{
    		   tap:'onRegisterTap'
    	   },
    	   
    	   '#LoginView #btnLogin':{
    		   tap:'onLoginTap'
    	   },
    	   
    	   '#RegisterAccountView #btnRegisterProfile':{
    		   tap:'onRegisterProfileTap'
    	   },
    	   
    	   '#RegisterAuthView #btnRegisterAccount':{
    		   tap:'onRegisterAccountTap'
    	   },
    	   
    	   '#RegisterProfileView #btnRegisterDone':{
    		   tap:'onRegisterDoneTap'
    	   }
       },
       
       routes:{
       		'/login':'showLoginView'	
       }
    },
    
    showLoginView:function(){
    	var loginMainView = Ext.create('YourTour.view.user.LoginMainView');
    	Ext.Viewport.setActiveItem(loginMainView);	
    },
    
    onRegisterTap:function(){
    	this.getLoginMainView().setActiveItem('#RegisterAuthView');
    },
    
    onRegisterAccountTap:function(){
    	this.getLoginMainView().setActiveItem('#RegisterAccountView');
    },
    
    onRegisterProfileTap:function(){
    	this.getLoginMainView().setActiveItem('#RegisterProfileView');
    },
    
    onRegisterDoneTap:function(){
    	this.redirectTo('/mainpage');
    },
    
    onLoginTap:function(){
    	this.redirectTo('/mainpage');
    }
});
