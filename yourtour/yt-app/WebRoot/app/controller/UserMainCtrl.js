Ext.define('YourTour.controller.UserMainCtrl', {
    extend: 'Ext.app.Controller',
    config: {
       refs:{
    	   userMainView:'#UserMainView',

           userProfileView:'#UserProfileView',
           genderPicker:'#UserProfileView #genderPicker',
           imagePicker:'#UserProfileView #imagePicker',

           fieldEditView:'#FieldEditView',

           placeList:'#PlaceChangeView #placeList'
       },
       
       control:{
    	   '#UserMainView #expert':{
    		   tap:'onExpertTap'
    	   },

           '#UserMainView #profile':{
               tap:'onUserProfileTap'
           },

           '#UserMainView #btnSetting':{
                tap:'onSettingTap'
           },

           '#UserProfileView #gender':{
               tap:'onGenderTap'
           },

           '#UserProfileView #nickName':{
               tap:'onNickNameFieldTap'
           },

           '#UserProfileView #state':{
               tap:'onStateFieldTap'
           },

           '#UserProfileView #userLogoPanel':{
               tap:'onUserLogoTap'
           },

           genderPicker:{
               donetap:'onGenderPick'
           },

           imagePicker:{
               donetap:'onImagePick'
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

        var profile = this.getApplication().getUserProfile();
    	var userLogo = userMainView.down('#userLogo');
    	userLogo.setHtml("<img src='" + YourTour.util.Context.getImageResource(profile.imageUrl) + "' style='width:64px; height:64px'>");
    },

    onExpertTap:function(){
    	this.redirectTo('/expert');
    },

    onUserProfileTap:function(){
        var me = this;

        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.user.UserProfileView'));

        var profile = this.getApplication().getUserProfile();
        me.fillProfileInfo(profile);
    },

    /**
     *
     * @param profile
     */
    fillProfileInfo:function(profile){
        var userProfileView = this.getUserProfileView();

        var imageEl = userProfileView.down('#userLogo');
        imageEl.setHtml("<img src='" + YourTour.util.Context.getImageResource(profile.imageUrl) + "'>");

        var nickNameEl = userProfileView.down('#nickName');
        nickNameEl.setHtml(profile.nickName);
    },

    onSettingTap:function(){
        this.redirectTo('/user/setting');
    },

    onGenderTap:function(){
        this.getGenderPicker().show();
    },

    onGenderPick:function(picker, value, text, eOpts ){
        var userProfileView = this.getUserProfileView();
        var gender = userProfileView.down('#gender');

        gender.setValue(value);
        gender.setText(text);

        this.getGenderPicker().hide();
    },

    onNickNameFieldTap:function(){
        var userProfileView = this.getUserProfileView();
        var nickNameEl = userProfileView.down('#nickName');
        var nickName = nickNameEl.getHtml();

        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.common.FieldEditView'));

        var fieldEditView = this.getFieldEditView();
        var field = fieldEditView.down('#field');
        field.setValue(nickName);

        var btnSave = fieldEditView.down('#btnSave');
        btnSave.on('tap', function(){
            nickNameEl.setHtml(field.getValue());
            Ext.ComponentManager.get('MainView').pop();
        })
    },

    onStateFieldTap:function(){
        var me = this;

        me.redirectTo('/place/change');
        me.getPlaceList().on('itemtap', function(record, e, eOpts){
            Ext.ComponentManager.get('MainView').pop();

            var userProfileView = me.getUserProfileView();
            var stateEl = userProfileView.down('#state');
            stateEl.setValue(record.get('id'));
            stateEl.setText(record.get('name'));
        });
    },

    onUserLogoTap:function(){
        this.getImagePicker().show();
    },

    onImagePick:function(picker, value, text, eOpts ){
        if(value == 'PhotoLib'){
            this.getApplication().getPhoto(navigator.camera.PictureSourceType.PHOTOLIBRARY,onImagePickSuccess);
        }else{
            this.getApplication().getPhoto(navigator.camera.PictureSourceType.CAMERA,onImagePickSuccess);
        }
    },

    onImagePickSuccess:function(image_uri) {
        var userProfileView = this.getUserProfileView();
        var img = view.down('#portrait');
        img.setSrc(image_uri);
    }
});
