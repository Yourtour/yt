Ext.define('YourTour.view.user.RegisterAccountView', {
    extend: 'Ext.form.Panel',
    requires: ['Ext.Panel', 'YourTour.view.widget.XSpacer', 'Ext.field.Password', 'YourTour.view.widget.XTextField', 'YourTour.view.widget.XButton'],
    xtype: 'RegisterAccountView',
    config: {
        layout: 'vbox',
        defaults: {
            padding: '0 10 0 10',
            style:'background-color:white',
        },
        scrollable: 'none',
        items: [
            {
                xtype: 'xheaderbar',
                title: '账号注册',
                backAction:function(headerbar){
                    headerbar.up('#LoginMainView').setActiveItem(0);
                }
            },

            {
                xtype: 'container',
                layout: 'hbox',
                cls: 'row underline icon-user',
                padding: '0 10 0 50',
                items: [
                    {
                        xtype: 'xtextfield',
                        placeHolder: '请输入手机号码(11位数字)',
                        inputCls: 'font-grey',
                        flex: 1,
                        name: 'mobile',
                        itemId: 'mobile'
                    }
                ]
            },

            {
                xtype: 'container',
                layout: 'hbox',
                cls: 'row underline icon-code',
                padding: '0 0 0 50',
                items: [
                    {
                        xtype: 'xtextfield',
                        flex: 1,
                        placeHolder: '请输入验证码',
                        name: 'authcode'
                    },
                    {
                        xtype: 'xbutton',
                        itemId: 'getCode',
                        cls: 'x-button-primary',
                        width:120,
                        height:43,
                        html: '获取验证码'
                    }
                ]
            },

            {
                xtype: 'container',
                layout: 'hbox',
                cls: 'row underline icon-password',
                padding: '0 10 0 50',
                items: [
                    {
                        xtype: 'passwordfield',
                        placeHolder: '请输入密码(8-16位字母、数字)',
                        inputCls: 'font-grey',
                        flex: 1,
                        name: 'password',
                        itemId: 'password'
                    }
                ]
            },

            {
                xtype: 'container',
                layout: 'hbox',
                cls: 'row underline icon-password',
                padding: '0 10 0 50',
                items: [
                    {
                        xtype: 'passwordfield',
                        placeHolder: '请输入验证密码(8-16位字母、数字)',
                        inputCls: 'font-grey',
                        flex: 1,
                        name: 'confirmPassword',
                        itemId: 'confirmPassword'
                    }
                ]
            },

            {
                xtype: 'xspacer'
            },

            {
                xtype: 'xbutton',
                id: 'btnRegisterAccount',
                cls: 'x-button-primary',
                text: '下一步'
            }
        ]
    }
});

