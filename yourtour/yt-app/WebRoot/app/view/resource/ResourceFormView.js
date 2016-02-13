Ext.define('YourTour.view.resource.ResourceFormView', {
    extend: 'YourTour.view.widget.XPage',
    requires: ['Ext.Panel', 'Ext.Img', 'YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.XProcessing', 'YourTour.view.resource.ResourceSceneView'],
    config: {
        id: 'ResourceFormView',
        layout: 'card',
        items: [
            {
                xtype: 'xheaderbar',
                items:[
                    {
                        xtype:'xbutton',
                        text:'详情',
                        align:'right',
                        padding:'0 10 0 20'
                    },

                    {
                        xtype:'xbutton',
                        text:'行程',
                        align:'right',
                        padding:'0 10 0 10'
                    },

                    {
                        xtype:'xbutton',
                        text:'达人',
                        align:'right',
                        padding:'0 10 0 10'
                    }
                ]
            },

            {
                xtype: 'xprocessing'
            },

            {
                xtype: 'xpagebody',
                layout: 'vbox',
                items: [

                    {
                        xtype: 'xtoolbar',
                        docked: 'bottom',
                        itemId: 'toolbar',
                        items: [
                            {
                                xtype: 'spacer',
                                flex: 1
                            },
                            {
                                xtype: 'xbutton',
                                text: '收藏',
                                ui: 'normal',
                                icon: 'resources/icons/24/icon_favorite.png',
                                itemId: 'btnFavorite'
                            }, {
                                xtype: 'spacer',
                                flex: 1
                            },{
                                xtype: 'xbutton',
                                text: '分享',
                                ui: 'normal',
                                icon: 'resources/icons/24/icon_share.png',
                                itemId: 'btnShare'
                            },{
                                xtype: 'spacer',
                                flex: 1
                            },{
                                xtype: 'xbutton',
                                text: '纠错',
                                ui: 'normal',
                                icon: 'resources/icons/24/icon_warn.png',
                                itemId: 'btnCorrect'
                            },{
                                xtype: 'spacer',
                                flex: 1
                            },
                            {
                                xtype: 'xbutton',
                                text: '加入日程',
                                ui: 'normal',
                                icon: 'resources/icons/16/icon_add.png',
                                itemId: 'btnResourceAddTap'
                            }, {
                                xtype: 'spacer',
                                flex: 1
                            },
                        ]
                    }
                ]
            }
        ]
    }
});

