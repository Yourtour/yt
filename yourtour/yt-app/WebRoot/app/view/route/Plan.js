Ext.define('YourTour.view.route.Plan', {
    extend: 'Ext.Panel',
    requires:['Ext.tab.Panel'],
    xtype: 'routeplan',
    config: {
    	itemId:'routeplan',
    	id:'routeplan',
    	fullscreen: true,
    	layout:'vbox',
        items: [
            {    
				xtype: 'titlebar',
				docked: 'top',
				title: '规划',
				items:[{
					itemId:'close',
					id:'close',
					xtype: "button", 
				    ui: "normal", 
					text:'返回',
					iconCls:'back_arrow',
					iconAlign: 'left',
					align:'left'
				}]
            },
            
            {    
				xtype: 'tabpanel',
				itemId:'tpPlan',
				id:'tpPlan',
				style:'height:100%',
				tabBar : {
					 docked: 'top',//固定在底部
		            layout: {
		                type: 'hbox'
		            },
		            
		            defaults: {
		                flex:1
		            }
		        },
				items:[{
					title:'线路推荐',
					xtype:'lineListView'	
				},{
					title:'线路定制'
				}]
            }
            
        ]
    }
});

