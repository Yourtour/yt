/**
 * Created by john on 15-7-11.
 */

Ext.define('yt_manager_app.view.login.LoginController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.login',

    requires: [
        'yt_manager_app.view.main.Main'
    ],

    onClickLogin: function () {
        var view = this.getView(),
            form = this.lookupReference('form'),
            username = form.getValues().username,
            password = form.getValues().password;

        Ext.Boot.debug(Ext.String.format('User login: username={0}, password={1}.', username, password));

        var authenticate = {code: username, password: password};
        var url = localStorage.getItem('yt_manager_app.cors') ? 'http://localhost:8080/yt-web/rest/users/login' : '/rest/users/login';
        Ext.Ajax.request({
            url: url,
            async: false,
            jsonData: authenticate,
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            success: function (response, opts) {
                localStorage.setItem('yt_manager_app.login.username', username);
                localStorage.setItem('yt_manager_app.login.state', true);

                Ext.Boot.debug(Ext.String.format('User[{0}] login success.', username));

                view.destroy();
                Ext.create({
                    xtype: 'app-main'
                });
            },
            failure: function (response, opts) {
                Ext.Boot.debug(Ext.String.format('User[{0}] login fail, status: {1}.', username, response.status));

                Ext.Msg.alert(Ext.String.format('Error', 'User[{0}] login fail, status: {1}.', username, response.status));
            }
        });
    }
});