
Ext.define('YourTour.view.Main', {
    extend: 'Ext.tab.Panel',
    xtype: 'mainview',
    requires: [
        'YourTour.view.home.Main',
        'YourTour.view.route.MainView',
        'YourTour.view.community.Main',
        'YourTour.view.personal.Main'
    ],
    
    config: {
        tabBarPosition: 'bottom',
        
        tabBar : {
            layout: {
                type: 'hbox'
            },
            
            defaults: {
                flex:1
            }
        },
        
        items: [
            {
                title:'首页',
                xtype:'homemain',
                iconCls:'home'
            },
            {
                title:'行程',
                xtype:'routemainview',
                iconCls:'home'
            },
            {
                title:'社区',
                xtype:'communityview',
                iconCls:'home'
            },
            {
                title:'个人',
                xtype:'personalmain',
                iconCls:'user'
            }
        ]
    }
});
