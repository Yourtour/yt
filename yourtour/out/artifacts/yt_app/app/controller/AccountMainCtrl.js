Ext.define('YourTour.controller.AccountMainCtrl', {
    extend: 'Ext.app.Controller',
    config: {
        refs: {
            loginMainView: '#LoginMainView',
            loginView: '#LoginView',
            rgisterAccountView: '#RegisterAccountView',
            registerProfileView: '#RegisterProfileView',
            tagList: '#RegisterProfileView #tagList'
        },

        control: {
            '#LoginView #btnLogin': {
                tap: 'doLogin'
            },

            '#LoginView #btnRegister': {
                tap: 'doAccountRegister'
            },

            '#RegisterAuthView #getCode': {
                tap: 'doGetAuthCode'
            },

            '#RegisterAccountView #btnRegisterAccount': {
                tap: 'doRegisterUserAccount'
            },

            '#RegisterProfileView #btnRegisterDone': {
                tap: 'doRegisterProfile'
            },

            '#RegisterProfileView #tagList':{
                itemtap:'selectTags'
            }
        },
    },

    init: function () {
        this.model = Ext.create('YourTour.model.UserModel', {graphId: -1});
    },

    showLoginView: function () {
        var loginMainView = Ext.create('YourTour.view.user.LoginMainView');
        Ext.Viewport.setActiveItem(loginMainView);
    },

    doAccountRegister: function () {
        this.getLoginMainView().setActiveItem(1);

        var me = this, tagList = me.getTagList(), store = this.getApplication().getBaseStore().first().tagsStore;
        tagList.setStore(store);
    },

    doGetAuthCode: function () {

    },

    /**
     * 账户注册
     */
    doRegisterUserAccount: function () {
        var me = this, accountview = this.getRgisterAccountView();
        var values = accountview.getValues();

        var mobile = values.mobile;
        if (mobile == '') {
            Ext.Msg.alert('请输入手机号码。');
            return;
        }

        var authcode = values.authcode;
        if (authcode == '') {
            Ext.Msg.alert('请输入验证码。');
            return;
        }

        var pass = values.password, confirm = values.confirmPassword;
        if (pass == '' || confirm == '') {
            Ext.Msg.alert('请输入密码或者验证密码。');
            return;
        }

        if (pass != confirm) {
            Ext.Msg.alert('两次输入的密码不一致，请重新输入。');
            return;
        }

        delete values['confirmPassword'];

        this.getApplication().callService({
            url: '/users/account/register',
            method: "POST",
            params: values,
            success: function (response) {
                me.getApplication().store([{key: 'account.authenticated', value: '1'}, {
                    key: 'user.profile',
                    value: Ext.JSON.encode(response)
                }]);

                me.getLoginMainView().setActiveItem(2);
            }
        });
    },

    /**
     *
     */
    doRegisterProfile: function () {
        var me = this, profileview = this.getRegisterProfileView();

        var options = {url: '/users/profile/save'}, params = {};
        params.nickName = profileview.down('#nickName').getValue();
        if(params.nickName == ''){
            this.getApplication().alert('请输入昵称');
            return;
        }
        params.gender = profileview.down('#gender').getValue();
        if(params.gender == ''){
            this.getApplication().alert('请选择性别');
            return;
        }

        var store = me.getTagList().getStore();
        var tags='';
        store.each(function(item){
            if(item.get('selected')){
                if(tags != '') tags += '|';

                tags += item.get('id') + ',' + item.get('name');
            };
        });
        params.tags = tags;

        params.id = me.getApplication().getUserId();
        options.params = params;

        options.fileContainer = profileview.down('#userLogo');

        options.success=function(response){
            me.getApplication().store([{key: 'account.authenticated', value: '1'}, {
                key: 'user.profile',
                value: Ext.JSON.encode(response)
            }]);

            var controller = me.getApplication().getController('MainCtrl');
            controller.showMainPage();
        }

        this.getApplication().uploadService(options);
    },

    selectTags:function(dataview, index, item, record, e){
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

    /**
     * 登录
     */
    doLogin: function () {
        var me = this;
        var loginInfo = {};

        var page = this.getLoginView();
        var mobileEl = page.down('#mobile');
        loginInfo.mobile = mobileEl.getValue();
        if (loginInfo.mobile == '') {
            Ext.Msg.alert('请输入登录手机号');
            return;
        }

        var passwordEl = page.down('#password');
        loginInfo.password = passwordEl.getValue();
        if (loginInfo.password == '') {
            Ext.Msg.alert('请输入登录密码');
            return;
        }

        this.getApplication().callService({
            url: '/users/login',
            method: "POST",
            params: loginInfo,
            success: function (response) {
                me.getApplication().store([{key: 'account.authenticated', value: '1'}, {
                    key: 'user.profile',
                    value: Ext.JSON.encode(response)
                }]);

                var controller = me.getApplication().getController('MainCtrl');
                controller.showMainPage();
            }
        });
    }
});
