Ext.define('YourTour.view.route.RoutePlaceEditView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel', 'YourTour.view.widget.XHeaderBar','YourTour.view.route.RoutePlaceDataItem'],
    config: {
    	id:'RoutePlaceEditView',
		layout:'card',
    	items:[
    		{    
				xtype: 'xheaderbar',
				title:'想去哪儿玩',
				items:[
					{
	                	xtype: "xbutton",
	                	itemId:'btnNext',
						icon:'resources/icons/icon_header_next.png',
	                	align:'right'
	                }
		        ]
			},

			{
				xtype: 'xprocessing'
			},

			{
				xtype: 'xpagebody',
				layout: 'vbox',
				items: [
					{
						xtype: 'xdataview',
						itemId: 'selectedList',
						defaultType: 'RoutePlaceDataItem',
						flex: 1
					},

					{
						xtype:'xlabel',
						itemId:'btnAdd',
						cls:'row',
						tappable:true,
						style:'background-image: url(./resources/icons/icon_add_32.png);background-repeat: no-repeat;background-position: center center;'
					},

					{
						xtype: 'xspacer'
					},

					{
						xtype: 'panel',
						layout: 'vbox',
						flex: 2,
						items: [
							{
								xtype: 'xlabel',
								itemId: 'message',
								cls: 'row font-medium font-bold underline',
								style: 'text-align:center;'
							}, {
								xtype: 'xdataview',
								itemId: 'relatedList',
								defaultType: 'RoutePlaceDataItem',
								flex: 1
							}
						]
					}
				]
			}
        ]
    }
});

