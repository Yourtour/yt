Ext.define('YourTour.view.resource.ResourceActivityItemListView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.resource.ResourcePlayDataItem'],
    xtype:'ResourceActivityItemListView',
    config: {
    	itemId:'ResourceActivityItemListView',
    	layout:'vbox',
    	fullscreen: true,
        items: [
			{
				xtype: 'xheaderbar',
				title:'活动安排'
			},

			{
				itemId:'activityList',
				xtype:'dataview',
				flex:1,
			    useComponents: true,
			    defaultType: 'ResourcePlayDataItem'
			}
        ]
    }
});

