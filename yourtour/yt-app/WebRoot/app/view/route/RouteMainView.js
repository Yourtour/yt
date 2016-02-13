Ext.define('YourTour.view.route.RouteMainView', {
    extend: 'YourTour.view.widget.XPage',
    xtype: 'RouteMainView',
    requires: ['Ext.Carousel', 'YourTour.view.widget.XHeaderBar','YourTour.view.widget.MarkedLabel', 'YourTour.view.widget.ToolButton', 'Ext.field.Hidden', 'Ext.Img'],
    config: {
        id: 'RouteMainView',
        itemId:'RouteMainView',
        layout: 'card',
        items: [
            {
                xtype: 'xheaderbar',
                itemId: 'headerbar',
                title: '我的行程',
                backButton: false,
                items: [
                    {
                        xtype: 'xbutton',
                        itemId: 'new',
                        align: 'right',
                        icon:'resources/icons/24/icon_header_add.png'
                    },
                    {
                        xtype: 'xbutton',
                        itemId: 'delete',
                        align: 'right',
                        icon:'resources/icons/24/icon_header_delete.png'
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
                        xtype:'xnavigation',
                        itemId:'navigation',
                        docked: 'top',
                        direction:'right',
                        top:30,
                        left: 10,
                        items:[
                            {
                                xtype:'image',
                                itemId:'btnRoute',
                                src:'resources/icons/48/icon_route.png'
                            },

                            {
                                xtype:'image',
                                itemId:'btnMember',
                                src:'resources/icons/48/icon_partner.png'
                            },

                            {
                                xtype:'image',
                                itemId:'btnCharge',
                                src:'resources/icons/48/icon_expense.png'
                            }
                        ]
                    },

                    {
                        xtype: 'panel',
                        layout: 'vbox',
                        bottom: 0,
                        docked: 'bottom',
                        style: 'background-color:grey;opacity:0.8; width:100%;',
                        items: [
                            {
                                xtype: 'markedlabel',
                                pack: 'center',
                                align: 'center',
                                itemId: 'routeName',
                                html: ''
                            },

                            {
                                xtype: 'xmultifield',
                                itemId: 'lineName',
                                paddingLeft:0,
                                icon:'icon-name'
                            },

                            {
                                xtype: 'xfield',
                                itemId: 'time',
                                icon:'icon-time',
                                dataChange:function(field, record){
                                    field.setText(record.get('startDate') +'-' + record.get('endDate') + '  合计：' + record.get('duration')+'天');
                                }
                            },

                            {
                                xtype: 'xmultifield',
                                itemId: 'impression',
                                icon:'icon-impression',
                                ifNull:'赶快记录下你的旅行印象吧.........',
                                ellipsis:{
                                    size:80,
                                    expandable:true
                                }
                            }
                        ]
                    }
                ]
            }
        ]
    }
});

