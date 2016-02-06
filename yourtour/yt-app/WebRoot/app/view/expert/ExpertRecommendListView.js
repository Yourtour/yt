Ext.define('YourTour.view.expert.ExpertRecommendListView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','Ext.Img', 'Ext.DataView','YourTour.view.widget.XHeaderBar', 'Ext.Spacer','YourTour.view.widget.ToolButton'],
    config: {
    	id:'ExpertRecommendListView',
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
			}
        ]
    }
});

