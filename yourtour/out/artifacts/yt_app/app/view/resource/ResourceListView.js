Ext.define('YourTour.view.resource.ResourceListView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.resource.ResourceListDataItem'],
    config: {
		id:'ResourceListView',
    	layout:'card',
        items: [
			{
				xtype: 'xheaderbar'
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
						itemId: 'resourceList',
						flex: 1,
						defaultType: 'ResourceListDataItem'
					}
				]
			}
        ]
    }
});

