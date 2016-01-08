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
				xtype:'carousel',
				flex:1
			},

			{
				xtype: 'toolbar',
				docked: 'bottom',
				items: [
					{
						xtype: 'spacer',
						flex:1
					},{
						xtype: 'button',
						text: '加入日程',
						ui: 'normal',
						baseCls:'button',
						itemId: 'btnAdd',
						padding:'0 40 0 40'
					}
				]
			}
        ]
    }
});

