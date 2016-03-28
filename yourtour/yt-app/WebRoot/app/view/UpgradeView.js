/**
 * 客户端升级提示页面
 */
Ext.define('YourTour.view.UpgradeView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XProcessBar'],
    config: {
    	layout:'card',
    	id:'UpgradeView',
        items: [
            {
                xtype: 'xheaderbar',
                title: '版本更新',
                backButton: false
            },

            {
                xtype:'xmultifield',
                itemId:'memo',
                flex:1
            },
            {
                xtype: 'xtoolbar',
                docked: 'bottom',
                itemId:'toolbar',
                defaults:{
                    flex:1
                },
                items: [
                    {
                        xtype: 'xbutton',
                        text: '立即更新',
                        itemId: 'btnUpgrade'
                    }, {
                        xtype: 'xbutton',
                        text: '以后再说',
                        itemId: 'btnCancel'
                    },
                    {
                        xtype: 'xprocessbar',
                        itemId: 'progressbar',
                        hidden:true
                    }
                ]
            }
        ]
    }
});

