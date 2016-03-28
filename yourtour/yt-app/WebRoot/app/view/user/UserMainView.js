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
                        icon: 'resources/icons/24/icon_header_setting.png',
                        align: 'right'
                    }
                ]
            },

            {
                xtype: 'xpagebody',
                layout: 'vbox',
                items: [
                    {
                        xtype: 'container',
                        layout: 'vbox',
                        style: 'background-image: url(./resources/images/img_personal_view.jpg); background-repeat: no-repeat; ackground-position: center center;',
                        height:200,
                        items: [
                            {
                                xtype:'container',
                                centered:true,
                                layout: 'vbox',
                                items:[
                                    {
                                        xtype:'container',
                                        itemId:'profilePanel',
                                        layout:'hbox',

                                        items:[
                                            {
                                                xtype:'spacer',
                                                flex:1
                                            },
                                            {
                                                xtype: 'xuserlogo',
                                                itemId: 'userLogo',
                                                cls:'x-xmedium',
                                            },
                                            {
                                                xtype:'spacer',
                                                flex:1
                                            }
                                        ]
                                    },

                                    {
                                        xtype:'xfield',
                                        itemId:'profile',
                                        margin:'5 0 0 0',
                                        padding:'5 10',
                                        fieldCls:'font-white text-align-center',
                                        underline:false
                                    }
                                ]
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

