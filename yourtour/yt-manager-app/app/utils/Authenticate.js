/**
 * Created by john on 15-10-1.
 */
Ext.define('yt_manager_app.utils.Authenticate', {
    singleton: true,

    authenticate: function () {
        var loginState = sessionStorage.getItem('yt_manager_app.loginState');

        if (!loginState) {
            // 没有登录
            Ext.create({xtype: 'login'});
        } else {
            // 已经登录
            Ext.create({xtype: 'app-main'});
        }
    },

    login: function(view, username) {
        sessionStorage.setItem('yt_manager_app.loginName', username);
        sessionStorage.setItem('yt_manager_app.loginTime', new Date());
        sessionStorage.setItem('yt_manager_app.loginState', true);

        view.destroy();
        Ext.create({ xtype: 'app-main' });
    },

    logout: function(view) {
        var username = sessionStorage.getItem('yt_manager_app.loginName');
        Ext.Ajax.request({
            asyn: true,
            url: (function() {
                return yt_manager_app.utils.Uri.packageUri('rest/users/logout/' + username);
            })(),
            success: function (response, opts) {
                sessionStorage.removeItem('yt_manager_app.loginState');
                view.destroy();
                Ext.create({ xtype: 'login' });
            },
            failure: function (response, opts) {
                Ext.MessageBox.alert('Error', Ext.String.format('登出系统失败！错误码： {0}。', response.status));
            }
        });
    }
});