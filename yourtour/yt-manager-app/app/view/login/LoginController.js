/**
 * Created by john on 15-7-11.
 */

Ext.define('yt_manager_app.view.login.LoginController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.login',

    requires: [
    ],

    onClickLogin: function () {
        var view = this.getView(),
            form = this.lookupReference('form'),
            username = form.getValues().username,
            password = form.getValues().password;

        Ext.Boot.debug(Ext.String.format('User login: username={0}, password={1}.', username, password));

        var authenticate = {code: username, password: password};
        var url = yt_manager_app.utils.Uri.packageUri('rest/users/login');
        Ext.Ajax.request({
            url: url,
            async: false,
            jsonData: authenticate,
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            success: function (response, opts) {
                var res = Ext.decode(response.responseText);
                if (res.errorCode > 0) {
                    Ext.MessageBox.alert('登录失败', res.errorText);
                    return;
                }
                yt_manager_app.utils.Authenticate.login(view, username);
            },
            failure: function (response, opts) {
                Ext.Boot.debug(Ext.String.format('User[{0}] login fail, status: {1}.', username, response.status));

                Ext.Msg.alert(Ext.String.format('Error', 'User[{0}] login fail, status: {1}.', username, response.status));
            }
        });
    }
});