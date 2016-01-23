Ext.define('YourTour.view.resource.ResourceFormView', {
    extend: 'YourTour.view.widget.XPage',
    requires: ['Ext.Panel', 'Ext.Img', 'YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.XProcessing', 'YourTour.view.resource.ResourceSceneView'],
    config: {
        id: 'ResourceFormView',
        layout: 'card',
        items: [
            {
                xtype: 'xheaderbar',
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
                                text: '加入日程',
                                ui: 'normal',
                                iconCls: 'action',
                                itemId: 'btnResourceAddTap'
                            }, {
                                xtype: 'xbutton',
                                text: '收藏',
                                ui: 'normal',
                                iconCls: 'favorites',
                                itemId: 'btnFavorite'
                            }, {
                                xtype: 'xbutton',
                                text: '分享',
                                ui: 'normal',
                                iconCls: 'action',
                                itemId: 'btnShare'
                            }
                        ]
                    }
                ]
            }
        ]
    }
});

