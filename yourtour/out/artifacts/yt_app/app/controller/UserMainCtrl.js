Ext.define('YourTour.controller.UserMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
        refs: {
            userMainView: '#UserMainView',

            userProfileView: '#UserProfileView'
        },

        control: {
            '#UserMainView #userLogo': {
                tap: 'showUserProfileView'
            },

            '#UserMainView #footprint': {
                tap: 'showFootprintPage'
            },

            '#UserMainView #btnSetting': {
                tap: 'onSettingTap'
            },

            '#UserProfileView #btnSave': {
                tap: 'doSaveProfileInfo'
            },

            '#UserProfileView #userLogoPanel': {
                tap: 'doChangeUserLogo'
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

    /**
     * 显示用户信息编辑界面
     */
    showUserProfileView: function () {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.user.UserProfileView'));

        var  me = this, view = me.getUserProfileView(), profile = me.getApplication().getUserProfile();

        var userLogo = view.down('#userLogo');
        userLogo.setSrc(YourTour.util.Context.getImageResource(profile.imageUrl));

        var nickName = view.down('#nickName');
        nickName.setText(profile.nickName);

        var gender = view.down('#gender');
        gender.setText(profile.gender);

        var birthday = view.down('#birthday');
        birthday.setText(profile.birthday);

        var slogan = view.down('#slogan');
        slogan.setText(profile.slogan);

        var nativePlace = view.down('#nativePlace');
        nativePlace.setText(profile.nativePlace);

        var residence = view.down('#residence');
        residence.setText(profile.residence);

        var tags = view.down('#tags'), store = this.getApplication().getBaseStore().first().tagsStore;
        tags.setStore(store);
    },

    /**
     * 修改用户头像
     */
    doChangeUserLogo: function () {
        var me = this, view = me.getUserProfileView(), userLogo = view.down('#userLogo');

        userLogo.showPicker();
    },

    /**
     * 保存用户信息
     */
    doSaveProfileInfo: function () {
        var me = this, view = me.getUserProfileView(), options = {}, params = {};

        params.id = me.getApplication().getUserId();

        var nickName = view.down('#nickName');
        if (nickName.isModified()) {
            params.nickName = nickName.getValue();
        }
        if (params.nickName && (params.nickName == null || params.nickName == '')) {
            this.alert('昵称不能为空。');
            return;
        }

        var slogan = view.down('#slogan');
        if (slogan.isModified()) {
            params.slogan = slogan.getValue();
        }

        var birthday = view.down('#birthday');
        if (birthday.isModified()) {
            params.birthday = birthday.getValue();
        }

        var gender = view.down('#gender');
        if (gender.isModified()) {
            params.gender = gender.getValue();
        }

        var residence = view.down('#residence');
        if (residence.isModified()) {
            params.residence = residence.getValue();
        }

        var nativePlace = view.down('#nativePlace');
        if (nativePlace.isModified()) {
            params.nativePlace = nativePlace.getValue();
        }

        var tags = view.down('#tags');
        if (tags.isModified()) {
            params.tags = tags.getValue();
        }

        options.url = '/users/' + params.id + '/save';
        options.params = params;

        var fileContainer = view.down('#userLogo');
        if(fileContainer.isModified()) {
            options.fileContainer = view.down('#userLogo');
        }

        options.success = function (response) {
            me.getApplication().store([{
                key: 'user.profile',
                value: Ext.JSON.encode(response)
            }]);

            Ext.ComponentManager.get('MainView').pop();
        }

        this.getApplication().uploadService(options);
    },

    changeTagItemStatus:function(dataview, index, item, record, e){
        var selected = record.get('selected');
        if(selected){
            record.set('selected', false);
        }else{
            record.set('selected', true);
        }

        var name = item.down('#name');
        if(record.get('selected')){
            name.removeLabelCls('icon-unchecked');
            name.setLabelCls('icon-checked');
        }else{
            name.removeLabelCls('icon-checked');
            name.setLabelCls('icon-unchecked');
        }
    },

    onSettingTap: function () {
        var controller = this.getApplication().getController('SettingMainCtrl');
        controller.showPage();
    }
});
