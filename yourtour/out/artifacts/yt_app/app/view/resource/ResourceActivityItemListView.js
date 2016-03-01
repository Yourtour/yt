Ext.define('YourTour.view.resource.ResourceActivityItemListView', {
    extend: 'YourTour.view.widget.XPage',
    config: {
    	itemId:'ResourceActivityItemListView',
    	layout:'vbox',
        items: [
			{
				xtype: 'xheaderbar',
				title:'活动安排'
			},

			{
				itemId:'activityList',
				xtype:'carousel',
				flex:1
			},

			{
				xtype: 'xtoolbar',
				docked: 'bottom',
				items: [
					{
						xtype: 'spacer',
						flex:1
					},{
						xtype: 'xbutton',
						text: '加入日程',
						itemId: 'btnAddToRoute',
						icon: 'resources/icons/24/icon_button_add.png',
					}
				]
			}
        ]
    }
});

