Ext.define('YourTour.controller.UserMainCtrl', {
    extend: 'Ext.app.Controller',
    config: {
       refs:{
    	   userMainView:'#UserMainView',

           userProfileView:'#UserProfileView',
           genderPicker:'#UserProfileView #genderPicker',
       },
       
       control:{
    	   '#UserMainView #expert':{
    		   tap:'onExpertTap'
    	   },

           '#UserMainView #profile':{
               tap:'onUserProfileTap'
           },

           '#UserProfileView #btnQuit':{
                tap:'onUserQuitTap'
           },

           '#UserProfileView #gender':{
               tap:'onGenderTap'
           },

           genderPicker:{
               DoneTap:'onGenderDoneTap'
           }

       },
       
       routes:{
    	   '/main/user':'showPage'
       }
    },
    
    init:function(){
    },
    
    showPage:function(){
    	var me = this;
    	
    	YourTour.util.Context.mainview = me.getUserMainView();
    	
    	var userMainView = this.getUserMainView();
    	
    	var userLogo = userMainView.down('#userLogo');
    	userLogo.setHtml("<img src='" + YourTour.util.Context.getImageResource('/resources/images/user_logo_64.png') + "' style='width:64px; height:64px'>");
    },
    
    onExpertTap:function(){
    	this.redirectTo('/expert');
    },

    onUserProfileTap:function(){
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.user.UserProfileView'));

        var localStore =  Ext.StoreManager.get('LocalStore');
        localStore.load();

        var index = localStore.find('key', 'user.profile');
        if(index >= 0){
            var userProfile = localStore.getAt(index);
            var profile = Ext.JSON.decode(userProfile.get('value'));

            var userProfileView = this.getUserProfileView();

            var imageEl = userProfileView.down('#image');
            imageEl.setHtml("<img src='" + YourTour.util.Context.getImageResource(profile.imageUrl) + "'>");

            var nickNameEl = userProfileView.down('#nickname');
            nickNameEl.setHtml(profile.nickname);

        }
    },

    onUserQuitTap:function(){
        this.getApplication().quit();
    },

    onGenderTap:function(){
        this.getGenderPicker().show();
    },

    onGenderDoneTap:function( picker, value, text, eOpts){
        var userProfileView = this.getUserProfileView();
        var gender = userProfileView.down('#gender');
        gender.setValue(value);
        gender.setText(text);
    }
});
