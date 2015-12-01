Ext.define('YourTour.view.resource.ResourceSelectionView', {
    extend: 'YourTour.view.widget.XPage',
    xtype: 'SelectionListView',
    requires:['YourTour.view.widget.XToolbar', 'Ext.field.Select','YourTour.view.widget.ToolButton', 'YourTour.view.resource.ResourcePlayListView','YourTour.view.resource.ResourceFoodListView'],
    config: {
    	id:'ResourceSelectionView',
    	layout:'vbox',
        items: [
            {    
				xtype: 'xheaderbar',
				title:'行程安排',
			    items:[
					
			    ]
			},
    		
    		{	
        		xtype:'tabpanel',
        		itemId:'resourceCategory',
        		ui:'dark',
        		flex:1,
        		tabBarPosition:'bottom',
        		tabBar : {
                    defaults: {
                        flex:1
                    }
                },
        	    items:[
					{
						xtype:'ResourcePlayListView',
						itemId:'ResourcePlayListView', 
					    title:'游玩',
					    iconCls:'play',
					    iconAlign:'top'
					},
					{
						xtype:'ResourceFoodListView',
					    itemId:'ResourceFoodListView',
					    title:'餐饮',
					    iconCls:'food',
					    iconAlign:'top'
					},
					{
						itemId:'btnHotel',
						title:'酒店',
					    iconCls:'hotel',
					    iconAlign:'top'
					},
					{
						itemId:'btnTraffic',
						title:'交通',
					    iconCls:'traffic',
					    iconAlign:'top'
					}
        	    ]
        	}
        ]
    }
});

