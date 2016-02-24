Ext.define('YourTour.controller.AccountMainCtrl', {
    extend: 'Ext.app.Controller',
    config: {
       refs:{
    	   loginMainView:'#LoginMainView',
    	   loginView:'#LoginView',
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
    	   
    	   '#RegisterAuthView #getCode':{
    		   tap:'onGetAuthCode'
    	   },
    	   
    	   '#RegisterAccountView #btnRegisterAccount':{
    		   tap:'registerUserAccount'
    	   },

    	   '#RegisterProfileView #btnRegisterDone':{
    		   tap:'onRegisterDoneTap'
    	   },
    	   
    	   '#RegisterProfileView #portrait':{
    		   tap:'onPortraitTap'
    	   },
    	   
    	   '#RegisterProfileView #btnCamera':{
    		   tap:'onBtnCameralTap'
    	   },
    	   
    	   '#RegisterProfileView #btnPhoto':{
    		   tap:'onBtnPhotoTap'
    	   }
       },
    },
    
    init:function(){
    	this.model = Ext.create('YourTour.model.UserModel',{graphId:-1});
    },

    showLoginView:function(){
    	var loginMainView = Ext.create('YourTour.view.user.LoginMainView');
    	Ext.Viewport.setActiveItem(loginMainView);	
    },
    
    onRegisterTap:function(){
    	this.getLoginMainView().setActiveItem(1);
    },
    
    onGetAuthCode:function(){
    	
    },

    /**
     * 账户注册
     */
	registerUserAccount:function(){
    	var me = this;
    	
    	/*var accountview = this.getRgisterAccountView();
    	var values = accountview.getValues();

    	var mobile = values.mobile;
    	if(mobile == ''){
    		Ext.Msg.alert('请输入手机号码。');
    		return;
    	}
    	
    	var authcode = values.authcode;
    	if(authcode == ''){
    		Ext.Msg.alert('请输入验证码。');
    		return;
    	}
    	
    	var pass = values.password, confirm = values.confirmPassword;
    	if(pass == '' || confirm == ''){
    		Ext.Msg.alert('请输入密码或者验证密码。');
    		return;
    	}
    	
    	if(pass != confirm){
    		Ext.Msg.alert('两次输入的密码不一致，请重新输入。');
    		return;
    	}
    	
    	delete values['confirmPassword'];*/

		me.getLoginMainView().setActiveItem(2);

		/*this.getApplication().callService({
			url: '/users/account/register',
			method: "POST",
			params: values,
			success: function (response) {
				me.getApplication().store({key:'user.profile', value:Ext.JSON.encode(response)});

				me.getLoginMainView().setActiveItem(2);
			}
		});*/
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

	/**
	 * 登录
	 */
    onLoginTap:function(){
    	var me = this;
    	var loginInfo = {};
    	
    	var page = this.getLoginView();
    	var mobileEl = page.down('#mobile');
    	loginInfo.mobile=mobileEl.getValue();
    	if(loginInfo.mobile == ''){
    		Ext.Msg.alert('请输入登录手机号');
    		return;
    	}
    	
    	var passwordEl = page.down('#password');
    	loginInfo.password=passwordEl.getValue();
    	if(loginInfo.password == ''){
    		Ext.Msg.alert('请输入登录密码');
    		return;
    	}

    	this.getApplication().callService({
    	    url : '/users/account/login',
    	    method : "POST",
    	    params : loginInfo,
    	    success : function(response) {
    	    	me.getApplication().store([{key:'account.authenticated', value:'1'}, {key:'user.profile', value:Ext.JSON.encode(response)}]);

				var controller = me.getApplication().getController('MainCtrl');
				controller.showMainPage();
    	    }
    	});
    },
    
    onBtnCameralTap:function(){
    	this.getPhoto(navigator.camera.PictureSourceType.CAMERA);
    },
    
    onBtnPhotoTap:function(){
    	this.getPhoto(navigator.camera.PictureSourceType.PHOTOLIBRARY);
    },
    
    getPhoto: function(source) {
        var me = this;

        navigator.camera.getPicture(me.success, me.failure, {
            quality: 50,
            destinationType: navigator.camera.DestinationType.FILE_URI,
            sourceType: source 
        });
    },

    success: function(image_uri) {
    	var view = this.getRegisterProfileView();
        var img = view.down('#portrait');
        img.setSrc(image_uri);
    },

    failure: function(message) {
    }
});