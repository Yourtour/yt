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
        		ui:'dark',
				cls:'tabbar',
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
					    title:'首页',
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
<<<<<<< HEAD
                        xtype:'CartMainView',
						itemId:'btnTravelCart',
						title:'旅行车',
=======
						xtype:'UserMainView',
						itemId:'btnPersonal',
						title:'个人',
>>>>>>> 2e51e1ee7f1bc2c3cf4126351b6534d4636dac1d
					    iconCls:'user',
					    iconAlign:'top'
					},

					{
						xtype:'DiscoveryMainView',
						itemId:'btnExplorer',
						title:'探索',
						iconCls:'user',
						iconAlign:'top'
					}
        	    ]
        	}
        ]
    }
});
