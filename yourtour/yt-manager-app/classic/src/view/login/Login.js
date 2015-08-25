/**
 * Created by john on 15-7-11.
 */

Ext.define('yt_manager_app.view.login.Login', {
    extend: 'Ext.window.Window',
    xtype: 'login',

    requires: [
        'yt_manager_app.view.login.LoginController',
        'Ext.form.Panel'
    ],

    controller: 'login',
    bodyPadding: 10,
    title: 'Login window',
    closable: false,
    autoShow: true,
    modal: true,

    items: {
        xtype: 'form',
        reference: 'form',
        defaultType: 'textfield',
        items: [{
            name: 'username',
            fieldLabel: 'User name',
            allowBlank: false,
            maxLength: 20
        }, {
            name: 'password',
            fieldLabel: 'Password',
            inputType: 'password',
            allowBlank: false,
            minLength: 4
        }],
        buttons: [{
            text: 'Login',
            formBind: true,
            listeners: {
                click: 'onClickLogin'
            }
        }]
    }
});