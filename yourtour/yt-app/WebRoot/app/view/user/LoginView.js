Ext.define('YourTour.view.user.LoginView', {
    extend: 'Ext.Container',
    requires: ['Ext.Panel', 'YourTour.view.widget.XSpacer', 'YourTour.view.widget.XHeaderBar', 'Ext.field.Password', 'YourTour.view.widget.XTextField', 'YourTour.view.widget.XButton'],
    xtype: 'LoginView',
    config: {
        layout: 'vbox',
        scrollable: false,
        items: [
            {
                xtype: 'xheaderbar',
                title: '会员登录',
                backButton:false,
                items: [{
                    xtype: "xbutton",
                    itemId: 'btnRegister',
                    icon:'resources/icons/24/icon_register.png',
                    align: 'right'
                }]
            },
            {
                xtype: 'panel',
                layout: 'hbox',
                cls: 'row underline icon-user',
                padding: '0 10 0 50',
                items: [
                    {
                        xtype: 'xtextfield',
                        placeHolder: '请输入用户名(邮箱或者手机号)',
                        inputCls: 'font-grey',
                        flex: 1,
                        itemId: 'mobile'
                    }
                ]
            },

            {
                xtype: 'panel',
                layout: 'hbox',
                cls: 'row underline icon-password',
                padding: '0 0 0 50',
                items: [
                    {
                        xtype: 'passwordfield',
                        placeHolder: '请输入密码(8-16位字母、数字)',
                        inputCls: 'borderless',
                        flex: 1,
                        itemId: 'password'
                    },{
                        xtype:'xbutton',
                        itemId:'forgetPassword',
                        text:'忘记密码',
                        width:120,
                        height:43,
                        cls:'x-button-primary'
                    }
                ]
            },

            {
                xtype: 'xspacer'
            },

            {
                xtype: 'xbutton',
                id: 'btnLogin',
                cls: 'x-button-primary',
                text: '登录'
            }
        ]
    }
});

