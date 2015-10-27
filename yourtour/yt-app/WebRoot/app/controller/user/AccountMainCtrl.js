Ext.define('YourTour.controller.user.AccountMainCtrl', {
    extend: 'Ext.app.Controller',
    config: {
       refs:{
    	   loginMainView:'#LoginMainView',
    	   registerAuthView:'#RegisterAuthView',
    	   rgisterAccountView:'#RegisterAccountView',
    	   registerProfileView:'#RegisterProfileView',
    	   portraitOptions:'#RegisterProfileView #portraitOptions'	   
       },
       
       control:{
    	   '#LoginView #btnRegister':{
    		   tap:'onRegisterTap'
    	   },
    	   
    	   '#LoginView #btnLogin':{
    		   tap:'onLoginTap'
    	   },
    	   
    	   '#RegisterAuthView #btnNext':{
    		   tap:'onRegisterAuthTap'
    	   },
    	   
    	   '#RegisterAuthView #getCode':{
    		   tap:'onGetAuthCode'
    	   },
    	   
    	   '#RegisterAccountView #btnNext':{
    		   tap:'onRegisterAccountTap'
    	   },
    	   
    	   '#RegisterProfileView #btnRegisterDone':{
    		   tap:'onRegisterDoneTap'
    	   },
    	   
    	   '#RegisterProfileView #portrait':{
    		   tap:'onPortraitTap'
    	   }
    	   
       },
       
       routes:{
       		'/login':'showLoginView'	
       },
       
       store:null,
       
       model:null
    },
    
    init:function(){
    	this.model = Ext.create('YourTour.model.UserAccountModel',{graphId:'-1'});
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
    onRegisterAuthTap:function(){
    	var me = this;
    	
    	var authview = me.getRegisterAuthView();
    	var values = authview.getValues(); 
    	this.model.set('mobile',values.mobile);
    	this.model.set('authcode',values.authcode);
    	
    	var proxy = this.model.getProxy();
    	proxy.setExtraParams({'step':'1'});
    	proxy.setUrl(YourTour.util.Context.getContext('/users/account/register'));
    	var success = function(){
    		//me.model = proxy.getModel();
    		me.getLoginMainView().setActiveItem(me.getRgisterAccountView());
    	};
    	this.model.save({success:success});
    },
    
    /**
     * 
     */
    onRegisterAccountTap:function(){
    	var me = this;
    	
    	var accountview = this.getRgisterAccountView();
    	var values = accountview.getValues(); 
    	this.model.set('userName',values.userName);
    	this.model.set('password',values.password);
    	
    	var proxy = this.model.getProxy();
    	proxy.setExtraParams('step', 2);
    	proxy.setUrl(YourTour.util.Context.getContext('/users/account/register'));
    	var success = function(){
    		me.getLoginMainView().setActiveItem(me.getRegisterProfileView());
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
    	proxy.setUrl(YourTour.util.Context.getContext('/users/account/register'));
    	var success = function(){
    		//本地缓存
    		me.getApplication().localStorage.setItem(YourTour.util.Context.getUserKey(),model.rowKey);  
    		
    		me.redirectTo('/mainpage');
    	};
    	
    	var failure = function(){
    		console.log('failure');
    	};
    	this.model.save({success:success, failure:failure});
    },
    
    onPortraitTap:function(){
    	this.getPortraitOptions().show();
    },
    
    onLoginTap:function(){
    	this.redirectTo('/mainpage');
    }
});