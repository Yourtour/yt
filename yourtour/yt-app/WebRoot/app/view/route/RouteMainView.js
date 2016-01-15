Ext.define('YourTour.view.route.RouteMainView', {
    extend: 'YourTour.view.widget.XPage',
    xtype: 'RouteMainView',
    requires: ['Ext.Carousel', 'YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.ToolButton', 'Ext.field.Hidden', 'Ext.Img'],
    config: {
        id: 'RouteMainView',
        layout: 'card',
        items: [
            {
                xtype: 'xheaderbar',
                itemId: 'headerbar',
                title: '我的行程',
                backButton: false,
                items: [
                    {
                        xtype: 'toolbutton',
                        itemId: 'new',
                        text: '新建',
                        align: 'right'
                    },
                    {
                        xtype: 'toolbutton',
                        itemId: 'delete',
                        text: '删除',
                        align: 'right'
                    }
                ]
            },

            {
                xtype:'panel',
                layout:'vbox',
                items:[
                    {
                        xtype: 'carousel',
                        itemId: 'routeCarousel',
                        flex: 1,
                        animation: {
                            duration: 250,
                            easing: {
                                type: 'fade'
                            }
                        }
                    },

                    {
                        xtype: 'panel',
                        layout: 'vbox',
                        bottom: 0,
                        docked: 'bottom',
                        style: 'background-color:grey;opacity:0.8; width:100%;',
                        style: 'width:100%;',
                        items: [
                            {
                                xtype: 'markedlabel',
                                pack: 'center',
                                align: 'center',
                                itemId: 'routeName',
                                html: ''
                            },

                            {
                                xtype: 'toolbar',
                                itemId: 'toolbuttons',
                                padding: 5,
                                layout: 'hbox',
                                ui: 'light',
                                defaults: {
                                    flex: 1,
                                    pack: 'center',
                                    align: 'center'
                                },
                                items: [
                                    {
                                        xtype: 'image',
                                        mode: 'tag',
                                        src: 'resources/icons/icon_route.png',
                                        itemId: 'btnRoute'
                                    },
                                    {
                                        xtype: 'image',
                                        mode: 'tag',
                                        src: 'resources/icons/icon_partner.png',
                                        itemId: 'btnMember'
                                    },
                                    {
                                        xtype: 'image',
                                        mode: 'tag',
                                        src: 'resources/icons/icon_expense.png',
                                        itemId: 'btnCharge'
                                    }
                                ]
                            },

                            {
                                xtype: 'xmultifield',
                                itemId: 'lineName',
                                icon:'icon-name'
                            },

                            {
                                xtype: 'xfield',
                                itemId: 'time',
                                icon:'icon-time',
                                updateRecord:function(record){
                                    this.setText(record.get('startDate') +'-' + record.get('endDate') + '  合计：' + record.get('duration')+'天');
                                }
                            },

                            {
                                xtype: 'xmultifield',
                                itemId: 'impression',
                                icon:'icon-impression',
                                ifNull:'赶快记录下你的旅行印象吧.........',
                                size:80
                            }
                        ]
                    }
                ]
            }
        ]
    }
});

