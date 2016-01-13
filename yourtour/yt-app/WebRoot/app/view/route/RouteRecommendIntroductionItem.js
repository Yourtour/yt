Ext.define('YourTour.view.route.RouteRecommendIntroductionItem', {
    extend: 'YourTour.view.common.CarouselItem',
    requires: ['Ext.Panel'],
    xtype: 'RouteRecommendIntroductionItem',
    config: {
        label: '简介',
        layout: 'vbox',
        items: [
            {
                xtype: 'panel',
                layout: 'vbox',
                height: 150,
                items: [
                    {
                        itemId: 'image',
                        xtype: 'image',
                        mode: 'tag'
                    },

                    {
                        xtype: 'panel',
                        layout: 'hbox',
                        cls: 'row underline',
                        padding: '0 10 0 10',
                        docked: 'bottom',
                        bottom: 0,
                        style: 'background-color:grey;opacity:0.2; width:100%; text-align:center',
                        items: [
                            {
                                itemId: 'price',
                                xtype: 'label',
                                style: 'color:#FFFFFF;font-size:14px; font-weight:bold;',
                                html: '¥ 450元/人/天'
                            },

                            {
                                xtype: 'spacer',
                                flex: 1
                            },

                            {
                                xtype: 'image',
                                src: 'resources/images/raty_32.png',
                                mode: 'tag'
                            }
                        ]
                    }
                ]
            },

            {
                xtype: 'xlabel',
                itemId: 'lineName',
                padding: '0 10 0 10',
                cls: 'row underline font-bold font-medium'
            },
            {
                xtype: 'toolbar',
                padding: '0 10 0 10',
                cls: 'toolbar-row underline font-grey font-medium',
                defaults: {
                    flex: 1
                },
                items: [
                    {
                        xtype: 'button',
                        text: '100',
                        icon: 'resources/icons/icon_eye.png',
                        itemId: 'readNum'
                    }, {
                        xtype: 'button',
                        text: '100',
                        icon: 'resources/icons/icon_ok.png',
                        itemId: 'usedNum'
                    }, {
                        xtype: 'button',
                        text: '100',
                        iconCls: 'refresh',
                        itemId: 'commentNum'
                    }, {
                        xtype: 'button',
                        text: '100',
                        icon: 'resources/icons/icon_favorite.png',
                        itemId: 'favoriteNum'
                    }
                ]
            },

            {
                xtype: 'hspacer'
            },

            {
                xtype: 'panel',
                tappable: true,
                layout: 'hbox',
                padding: '0 10 0 10',
                cls: 'underline font-grey font-medium nav-arrow',
                items: [
                    {
                        itemId: 'expertImage',
                        xtype: 'image',
                        mode: 'tag',
                    }
                ]
            },

            {
                xtype: 'hspacer'
            },

            {
                xtype: 'xlabel',
                itemId: 'featureTitle',
                html: '线路特点',
                tappable: true,
                padding: '0 10 0 10',
                cls: 'row underline font-bold font-medium nav-arrow'
            },

            {
                xtype: 'xmultifield',
                itemId: 'feature',
                padding: '0 10 0 10',
            },

            {
                xtype: 'hspacer'
            },

            {
                xtype: 'xlabel',
                itemId: 'reasonTitle',
                html: '推荐理由',
                tappable: true,
                padding: '0 10 0 10',
                cls: 'row underline font-bold font-medium nav-arrow'
            },

            {
                xtype: 'xmultifield',
                itemId: 'reason',
                padding: '0 10 0 10'
            }
        ]
    }
});

