Ext.define('YourTour.view.route.RouteCheckinView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel', 'YourTour.view.widget.XHeaderBar','YourTour.view.route.RoutePlaceDataItem'],
    config: {
    	id:'RouteCheckinView',
    	items:[
    		{    
				xtype: 'xheaderbar',
				title:'行程签到',
				items:[
					{
	                	xtype: "xbutton",
	                	itemId:'btnSave',
						icon:'resources/icons/icon_header_ok.png',
	                	align:'right'
	                }
		        ]
			},

			{
				xtype:'xtextarea',
				itemId:'memo',
				height:100,
				placeHolder:'签到留言'
			},

			{
				xtype:'xspacer'
			},

			{
				xtype : 'image',
				itemId : 'imgAdd',
				padding:'5',
				src:'resources/icons/icon_add_64.png',
				mode : 'tag'
			}
        ]
    }
});

