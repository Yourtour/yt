
Ext.define('YourTour.view.Main', {
    extend: 'Ext.tab.Panel',
    xtype: 'mainview',
    requires: [
        'YourTour.view.HomeMain',
        'YourTour.view.RouteMain',
        'YourTour.view.Community',
        'YourTour.view.PersonalMain'
    ],
    
    config: {
        tabBarPosition: 'bottom',
        
        items: [
            {
                title:'首页',
                xtype:'homemain',
                iconCls:'home'
            },
            {
                title:'行程',
                xtype:'routemain',
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
                iconCls:'home'
            },
           
        ]
    }
});
