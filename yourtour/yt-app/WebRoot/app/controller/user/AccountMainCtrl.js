Ext.define('YourTour.controller.user.AccountMainCtrl', {
    extend: 'Ext.app.Controller',
    config: {
       refs:{
    	   loginMainView:'#LoginMainView',
    	   registerAuthView:'#RegisterAuthView',
    	   rgisterAccountView:'#RegisterAccountView',
    	   registerProfileView:'#RegisterProfileView'
    		   
       },
       
       control:{
    	   '#LoginView #btnRegister':{
    		   tap:'onRegisterTap'
    	   },
    	   
    	   '#LoginView #btnLogin':{
    		   tap:'onLoginTap'
    	   },
    	   
    	   '#RegisterAuthView #btnRegisterProfile':{
    		   tap:'onRegisterAccountTap'
    	   },
    	   
    	   '#RegisterAuthView #getCode':{
    		   tap:'onGetAuthCode'
    	   },
    	   
    	   '#RegisterAuthView #btnRegisterAccount':{
    		   tap:'onRegisterAccountTap'
    	   },
    	   
    	   '#RegisterAccountView #btnRegisterProfile':{
    		   tap:'onRegisterProfileTap'
    	   },
    	   
    	   '#RegisterProfileView #btnRegisterDone':{
    		   tap:'onRegisterDoneTap'
    	   }
       },
       
       routes:{
       		'/login':'showLoginView'	
       },
       
       store:null,
       
       model:null
    },
    
    init:function(){
    	this.model = Ext.create('YourTour.model.UserAccountModel');
    },
    
    showLoginView:function(){
    	var loginMainView = Ext.create('YourTour.view.user.LoginMainView');
    	Ext.Viewport.setActiveItem(loginMainView);	
    },
    
    onRegisterTap:function(){
    	this.getLoginMainView().setActiveItem('#RegisterAuthView');
    },
    
    onGetAuthCode:function(){
    	
    },
    
    /**
     * 
     */
    onRegisterAccountTap:function(){
    	this.getApplication().localStorage.setItem('user', this.model);
    	
    	var me = this.getRegisterAuthView();
    	
    	this.model.set('mobileNo',me.down('#mobile'));
    	this.model.set('authCode',me.down('#authcode'));
    	this.model.set('step', 1);
    	
    	var proxy = this.model.getProxy();
    	proxy.setUrl(YourTour.util.Context.getContext('/account/register'));
    	var success = function(){
    		
    		me.getLoginMainView().setActiveItem('#RegisterAccountView');
    	};
    	this.model.save({success:success});
    },
    
    /**
     * 
     */
    onRegisterProfileTap:function(){
    	var me = this.getRegisterAccountView();
    	
    	this.model.set('userName',me.down('#userName'));
    	this.model.set('password',me.down('#password'));
    	this.model.set('step', 2);
    	
    	var proxy = this.model.getProxy();
    	proxy.setUrl(YourTour.util.Context.getContext('/account/register'));
    	var success = function(){
    		me.getLoginMainView().setActiveItem('#RegisterProfileView');
    	};
    	this.model.save({success:success});
    },
    
    /**
     * 
     */
    onRegisterDoneTap:function(){
    	var me = this.getRegisterProfileView();
    	
    	this.model.set('nickname',me.down('#nickname'));
    	this.model.set('sex',me.down('#sex'));
    	this.model.set('tags','');
    	this.model.set('step', 3);
    	
    	var proxy = this.model.getProxy();
    	proxy.setUrl(YourTour.util.Context.getContext('/account/register'));
    	var success = function(){
    		this.getApplication().localStorage.setItem(user,model.rowKey);  
    		me.redirectTo('/mainpage');
    	};
    	this.model.save({success:success});
    },
    
    onLoginTap:function(){
    	this.redirectTo('/mainpage');
    }
});
