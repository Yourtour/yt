Ext.define('YourTour.view.user.RegisterAccountView', {
    extend: 'Ext.form.Panel',
    requires: ['Ext.Panel', 'YourTour.view.widget.XSpacer', 'YourTour.view.widget.XTitleBar', 'Ext.field.Password', 'YourTour.view.widget.XTextField', 'YourTour.view.widget.XButton', 'YourTour.view.widget.ToolButton'],
    xtype: 'RegisterAccountView',
    config: {
        id: 'RegisterAccountView',
        layout: 'vbox',
        defaults: {
            padding: '0 10 0 10'
        },
        scrollable: 'none',

        items: [
            {
                xtype: 'xtitlebar',
                docked: 'top',
                title: '账号注册',
                items: [{
                    xtype: "image",
                    itemId: 'back',
                    id: 'back',
                    mode: 'tag',
                    margin: '0 0 0 5',
                    src: 'resources/icons/icon_back.png',
                    align: 'left'
                }]
            },

            {
                xtype: 'xspacer'
            },

            {
                xtype: 'panel',
                layout: 'hbox',
                cls: 'row underline',
                style: 'background:white',
                items: [
                    {
                        xtype: 'image',
                        src: 'resources/icons/icon_mobile.png',
                        mode: 'tag'
                    },

                    {
                        xtype: 'xlabel',
                        html: '|',
                        margin: '0 10 0 10'
                    },
                    {
                        xtype: 'xtextfield',
                        name: 'mobile',
                        flex: 1,
                        placeHolder: '请输入手机号码(11位数字)'
                    }
                ]
            },

            {
                xtype: 'panel',
                layout: 'hbox',
                cls: 'row underline',
                style: 'background:white',
                items: [
                    {
                        xtype: 'image',
                        src: 'resources/icons/icon_code.png',
                        mode: 'tag'
                    },

                    {
                        xtype: 'xlabel',
                        html: '|',
                        margin: '0 10 0 10'
                    },
                    {
                        xtype: 'panel',
                        layout: 'hbox',
                        flex: 1,
                        items: [
                            {
                                xtype: 'xtextfield',
                                flex: 3,
                                placeHolder: '请输入验证码',
                                name: 'authcode'
                            },
                            {
                                xtype: 'xbutton',
                                itemId: 'getCode',
                                flex: 2,
                                cls: 'x-button-primary',
                                html: '获取验证码'
                            }
                        ]
                    }
                ]
            },

            {
                xtype: 'panel',
                layout: 'hbox',
                cls: 'row underline',
                style: 'background:white',
                items: [
                    {
                        xtype: 'image',
                        src: 'resources/icons/icon_password.png',
                        mode: 'tag'
                    },

                    {
                        xtype: 'xlabel',
                        html: '|',
                        margin: '0 10 0 10'
                    },
                    {
                        xtype: 'passwordfield',
                        flex: 1,
                        placeHolder: '请输入密码(8-16位字母、数字)',
                        name: 'password'
                    }
                ]
            }, {
                xtype: 'panel',
                layout: 'hbox',
                cls: 'row underline',
                style: 'background:white',
                items: [
                    {
                        xtype: 'image',
                        src: 'resources/icons/icon_password.png',
                        mode: 'tag'
                    },

                    {
                        xtype: 'xlabel',
                        html: '|',
                        margin: '0 10 0 10'
                    },
                    {
                        xtype: 'passwordfield',
                        flex: 1,
                        placeHolder: '请输入验证密码(8-16位字母、数字)',
                        name: 'confirmPassword'
                    }
                ]
            },

            {
                xtype: 'xspacer'
            },

            {
                xtype: 'xbutton',
                id: 'btnNext',
                cls: 'x-button-primary',
                text: '下一步'
            }
        ]
    }
});

