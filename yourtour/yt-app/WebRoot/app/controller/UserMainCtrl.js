Ext.define('YourTour.controller.UserMainCtrl', {
    extend: 'Ext.app.Controller',
    config: {
        refs: {
            userMainView: '#UserMainView',

            userProfileView: '#UserProfileView',
            genderPicker: '#UserProfileView #genderPicker',

            fieldEditView: '#FieldEditView',

            placeList: '#PlaceChangeView #placeList'
        },

        control: {
            '#UserMainView #expert': {
                tap: 'onExpertTap'
            },

            '#UserMainView #userLogo': {
                tap: 'showUserProfileView'
            },

            '#UserMainView #footprint': {
                tap: 'showFootprintPage'
            },


            '#UserMainView #btnSetting': {
                tap: 'onSettingTap'
            },

            '#UserProfileView #gender': {
                tap: 'onGenderTap'
            },

            '#UserProfileView #btnSave': {
                tap: 'doSaveProfileInfo'
            },

            '#UserProfileView #userLogoPanel': {
                tap: 'doChangeUserLogo'
            },

            '#UserProfileView #state': {
                tap: 'onStateFieldTap'
            },

            genderPicker: {
                donetap: 'onGenderPick'
            }
        }
    },

    showMainPage: function () {
        var me = this;
        YourTour.util.Context.mainview = me.getUserMainView();
        var mainview = this.getUserMainView();

        var profileInfo = this.getApplication().getUserProfile();
        var userLogo = mainview.down('#userLogo');
        userLogo.setSrc(YourTour.util.Context.getImageResource(profileInfo.imageUrl));

        var nickName = profileInfo.nickName, genderIcon = profileInfo.gender == 'M' ? 'icon-male' : 'icon-female';

        mainview.down('#profile').setText('<span class="' + genderIcon + '">' + nickName + '</span>');
    },

    doChangeUserLogo: function () {
        var me = this, view = me.getUserProfileView(), userLogo = view.down('#userLogo');

        userLogo.showPicker();
    },

    doSaveProfileInfo: function () {
        var me = this, view = me.getUserProfileView(), params = {};

        var nickName = view.down('#nickName');
        if(nickName.isModified()){
            params.nickName = nickName.getValue();
        }

        var slogan = view.down('#slogan');
        if(slogan.isModified()){
            params.slogan = slogan.getValue();
        }

        var birthday = view.down('#birthday');
        if(birthday.isModified()){
            params.birthday = birthday.getValue();
        }

        var gender = view.down('#gender');
        if(gender.isModified()){
            params.gender = gender.getValue();
        }

        var residence = view.down('#residence');
        if(residence.isModified()){
            params.residence = residence.getValue();
        }

        var nativePlace = view.down('#nativePlace');
        if(nativePlace.isModified()){
            params.nativePlace = nativePlace.getValue();
        }

        var tags = view.down('#tags');
        if(tags.isModified()){
            params.tags = tags.getValue();
        }

        options.success=function(response){
            me.getApplication().store([{
                key: 'user.profile',
                value: Ext.JSON.encode(response)
            }]);

            Ext.ComponentManager.get('MainView').pop();
        }

        this.getApplication().uploadService(options);
    },

    onExpertTap: function () {
        this.redirectTo('/expert');
    },

    showUserProfileView: function () {
        var me = this;

        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.user.UserProfileView'));

        var profile = this.getApplication().getUserProfile();
        me.fillProfileInfo(profile);
    },

    /**
     *
     * @param profile
     */
    fillProfileInfo: function (profile) {
        var userProfileView = this.getUserProfileView();

        var userLogo = userProfileView.down('#userLogo');
        userLogo.setSrc(YourTour.util.Context.getImageResource(profile.imageUrl));

        var nickName = userProfileView.down('#nickName');
        nickName.setText(profile.nickName);

        var slogan = userProfileView.down('#slogan');
        slogan.setText('喜欢一个事物光有自己的勇气是不行的，一定要让别人觉得你喜欢的东西是世界上最好的，而且要大声地说，大胆地说，理直气壮的说。');
    },

    onSettingTap: function () {
        this.redirectTo('/user/setting');
    },

    onGenderTap: function () {
        this.getGenderPicker().show();
    },

    onGenderPick: function (picker, value, text, eOpts) {
        var userProfileView = this.getUserProfileView();
        var gender = userProfileView.down('#gender');

        gender.setValue(value);
        gender.setText(text);

        this.getGenderPicker().hide();
    },

    onStateFieldTap: function () {
        var me = this;

        me.redirectTo('/place/change');
        me.getPlaceList().on('itemtap', function (record, e, eOpts) {
            Ext.ComponentManager.get('MainView').pop();

            var userProfileView = me.getUserProfileView();
            var stateEl = userProfileView.down('#state');
            stateEl.setValue(record.get('id'));
            stateEl.setText(record.get('name'));
        });
    }
});
