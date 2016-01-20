Ext.define('YourTour.view.route.RoutePlaceEditView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel', 'YourTour.view.widget.XHeaderBar','YourTour.view.route.RoutePlaceDataItem'],
    config: {
    	id:'RoutePlaceEditView',
    	items:[
    		{    
				xtype: 'xheaderbar',
				title:'想去哪儿玩',
				items:[
					{
	                	xtype: "toolbutton", 
	                    ui: "normal", 
	                	text:'下一步',
	                	itemId:'btnNext',
	                	align:'right'
	                }
		        ]
			},

			{
				xtype:'xdataview',
				itemId:'selectedList',
				defaultType:'RoutePlaceDataItem',
				flex:1
			},

			{
				xtype:'xspacer'
			},

			{
				xtype:'panel',
				layout:'vbox',
				flex:2,
				items:[
					{
						xtype:'xlabel',
						itemId:'message',
						cls:'row font-medium font-bold underline',
						style:'text-align:center;'
					},{
						xtype:'xdataview',
						itemId:'relatedList',
						defaultType:'RoutePlaceDataItem',
						flex:1
					}
				]
			}
        ]
    }
});

