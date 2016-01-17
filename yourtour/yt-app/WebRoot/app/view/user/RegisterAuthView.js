Ext.define('YourTour.view.user.RegisterAuthView', {
    extend: 'Ext.form.Panel',
    xtype: 'RegisterAuthView',
    requires: ['Ext.Panel', 'Ext.TitleBar', 'Ext.Img', 'YourTour.view.widget.XSpacer', 'YourTour.view.widget.XTitleBar', 'Ext.field.Password', 'YourTour.view.widget.XTextField', 'YourTour.view.widget.XButton', 'YourTour.view.widget.ToolButton'],
    config: {
        id: 'RegisterAuthView',
        layout: 'vbox',
        defaults: {
            padding: '0 0 0 10'
        },
        items: [
            {
                xtype: 'xtitlebar',
                title: '注册验证',
                items: [{
                    xtype: "image",
                    itemId: 'back',
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
                xtype: 'xspacer'
            },

            {
                xtype: 'xbutton',
                itemId: 'btnNext',
                cls: 'x-button-primary',
                text: '下一步'
            }
        ]
    }
});

