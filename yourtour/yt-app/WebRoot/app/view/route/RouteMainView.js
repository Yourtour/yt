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
                xtype: 'xpagebody',
                itemId:'pageBody',
                layout: 'card',
                items: [
                    {
                        xtype:'panel',
                        itemId:'routePanel',
                        layout:'vbox',
                        items:[
                            {
                                xtype:'panel',
                                itemId:'item',
                                margin:'20 0 0 0',
                                items:[
                                    {
                                        xtype:'xfield',
                                        itemId:'name',
                                        underline:false,
                                        fieldCls:'font-striking font-bold font-large-extra'
                                    },
                                    {
                                        xtype: 'xmultifield',
                                        itemId: 'lineName',
                                        icon:'icon-name',
                                        underline:false,
                                        fieldCls:'font-white'
                                    },

                                    {
                                        xtype: 'xfield',
                                        itemId: 'time',
                                        icon:'icon-time',
                                        underline:false,
                                        margin:'0 0 20 0',
                                        fieldCls:'font-white'
                                    }
                                ]
                            },

                            {
                                xtype:'panel',
                                layout:'hbox',
                                docked: 'bottom',
                                defaults:{
                                    flex:1,
                                    cls:'x-xnav-button'
                                },
                                items:[
                                    {
                                        xtype: 'xlabel',
                                        html: '行程',
                                        itemId:'btnRoute',
                                        style:'background-image: url(./resources/icons/48/icon_route.png);'
                                    },

                                    {
                                        xtype: 'xlabel',
                                        html: '服务',
                                        itemId:'btnService',
                                        style:'background-image: url(./resources/icons/48/icon_service.png);'
                                    },

                                    {
                                        xtype: 'xlabel',
                                        html: '伙伴',
                                        itemId:'btnMember',
                                        style:'background-image: url(./resources/icons/48/icon_partner.png);'
                                    },

                                    {
                                        xtype: 'xlabel',
                                        html: '费用',
                                        itemId:'btnCharge',
                                        style:'background-image: url(./resources/icons/48/icon_expense.png);'
                                    }
                                ]
                            }

                        ]
                    }
                ]
            }
        ]
    }
});

