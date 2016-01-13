Ext.define('YourTour.view.route.RouteRecommendListView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','Ext.Img', 'Ext.DataView','YourTour.view.widget.XHeaderBar', 'Ext.Spacer','YourTour.view.widget.XBack','YourTour.view.widget.ToolButton','YourTour.view.route.RouteRecommendDataItem'],
    config: {
    	id:'RouteRecommendListView',
    	layout:'vbox',
		scrollable: {
			direction: 'vertical',
			indicators: false
		},
        items: [
        	{    
				xtype: 'xheaderbar',
				items:[
					{
	                	xtype: "toolbutton",
	                    ui: "normal",
	                	text:'定制',
	                	itemId:'btnCustomize',
	                	align:'right'
	                }
                ]
			},

			{
				xtype:'panel',
				height:150,
				items:[
					{
						itemId : 'image',
						xtype : 'image',
						mode : 'tag',
						src:'resources/icons/expert.jpg',
						width:'100%',
						height:150
					}
				]
			},

			{
				xtype:'panel',
				cls:'spacer'
			}
        ]
    }
});

