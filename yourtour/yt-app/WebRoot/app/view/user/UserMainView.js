Ext.define('YourTour.view.user.UserMainView', {
    extend: 'YourTour.view.widget.XPage',
    requires: ['YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.XLabel'],
    xtype: 'UserMainView',
    config: {
        id: 'UserMainView',
        layout: 'vbox',
        items: [
            {
                xtype: 'xheaderbar',
                backButton: false,
                title: '个人中心',
                items: [
                    {
                        xtype: "xbutton",
                        itemId: 'btnSetting',
                        icon: 'resources/icons/icon_header_setting.png',
                        align: 'right'
                    }
                ]
            },

            {
                xtype: 'xpagebody',
                layout: 'vbox',
                items: [
                    {
                        xtype: 'xpanel',
                        layout: 'hbox',
                        itemId: 'profile',
                        style: 'background-color:white',
                        cls: 'nav-arrow',
                        padding: 10,
                        items: [
                            {
                                itemId: 'userLogo',
                                xtype: 'image',
                                model: 'tag'
                            },
                            {
                                xtype: 'xlabel',
                                html: '我是达人',
                                flex: 1
                            }
                        ]
                    },

                    {
                        xtype: 'xspacer'
                    },

                    {
                        xtype: 'panel',
                        layout: 'vbox',
                        items: [
                            {
                                xtype: 'xbutton',
                                itemId:'btnApply',
                                cls:'x-button-primary',
                                text:'达人申请'
                            },

                            {
                                xtype: 'xlabel',
                                itemId: 'expert',
                                html: '达人服务',
                                cls: 'row font-medium underline font-grey',
                                indicator: 'nav-arrow',
                                padding: '0 10 0 10',
                                hidden:true
                            }
                        ]
                    },

                    {
                        xtype: 'xspacer'
                    },
                    {
                        xtype: 'panel',
                        layout: 'hbox',
                        cls: 'font-medium underline font-grey',
                        defaults: {
                            flex:1,
                            cls:'text-align-center',
                        },
                        items: [
                            {
                                xtype: 'xlabel',
                                itemId: 'message',
                                html: '我的消息'
                            },

                            {
                                xtype: 'xlabel',
                                itemId: 'comment',
                                html: '我的点评',
                                style:'border-left: 1px solid #EDEDED;border-right: 1px solid #EDEDED;'
                            },

                            {
                                xtype: 'xlabel',
                                itemId: 'favorite',
                                html: '我的收藏'
                            }
                        ]
                    },

                    {
                        xtype: 'xspacer'
                    },

                    {
                        xtype: 'panel',
                        layout: 'vbox',
                        defaults: {
                            cls: 'row font-medium underline font-grey',
                            indicator: 'nav-arrow',
                            padding: '0 10 0 10'
                        },
                        items: [
                            {
                                xtype: 'xlabel',
                                itemId: 'equity',
                                html: '我的权益'
                            },

                            {
                                xtype: 'xlabel',
                                itemId: 'order',
                                html: '我的订单'
                            },

                            {
                                xtype: 'xlabel',
                                itemId: 'footprint',
                                html: '我的足迹'
                            },

                            {
                                xtype: 'xlabel',
                                itemId: 'charge',
                                html: '我的费用'
                            }
                        ]
                    }
                ]
            }
        ]
    }
});

