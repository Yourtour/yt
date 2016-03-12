Ext.define('YourTour.view.MainView', {
	extend: 'Ext.NavigationView',
    requires:['Ext.tab.Panel'],
    xtype: 'MainView',
    config: {
    	id:'MainView',
    	navigationBar:false,
    	layout: {
            type: 'card',
            animation: {
                duration: 300,
                easing: 'ease-out',
                type: 'slide',
                direction: 'left'
            }
        },

        items: [
        	{
        		xtype:'tabpanel',
        		itemId:'menuTab',
        		tabBarPosition:'bottom',
        		tabBar : {
                    defaults: {
                        flex:1
                    }
                },
        	    items:[
					{
						xtype:'HomeMainView',
						itemId:'btnHome',
					    title:'推荐',
					    iconCls:'home',
					    iconAlign:'top'
					},

					{
                        xtype:'PlaceMainView',
						itemId:'btnPlace',
						title:'目的地',
						iconCls:'locate',
						iconAlign:'top'
					},

					{
                        xtype:'RouteMainView',
					    itemId:'btnRoute',
					    title:'行程',
					    iconCls:'bookmarks',
					    iconAlign:'top'
					},

					{
                        xtype:'UserMainView',
						itemId:'btnPersonal',
						title:'个人',
					    iconCls:'user',
					    iconAlign:'top'
					}
        	    ]
        	}
        ]
    }
});
