Ext.define('YourTour.view.route.Plan', {
    extend: 'Ext.Panel',
    requires:['Ext.tab.Panel', 'Ext.Toolbar', 'Ext.Panel'],
    xtype: 'routeplan',
    config: {
    	itemId:'routeplan',
    	id:'routeplan',
    	fullscreen: true,
    	layout:'vbox',
    	cls:'clsMainBody',
    	
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
				xtype: 'toolbar',
				itemId:'tbPlan',
				id:'tbPlan',
				items:[{
					itemId:'btnRecommend',
					id:'btnRecommend',
					text:'线路推荐',
					flex:1
				},{
					text:'线路定制',
					itemId:'btnCustomer',
					id:'btnCustomer',
					flex:1
				}]
            },
            
            {
            	xtype: 'panel',
				itemId:'panels',
				id:'panels',
				layout:{
					type:'card',
					animation:'slide'
				},
				style:'height:100%',
				items:[{
					xtype:'lineListView',
					itemId:'lineListView',
					id:'lineListView'
				},{
					xtype:'scheduleListView',
					itemId:'scheduleListView',
					id:'scheduleListView'
				}]
            }
        ]
    }
});

