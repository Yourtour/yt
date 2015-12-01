Ext.define('YourTour.view.resource.ResourcePlayListView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.resource.ResourcePlayDataItem'],
    xtype:'ResourcePlayListView',
    config: {
    	itemId:'ResourcePlayListView',
    	layout:'vbox',
    	fullscreen: true,
        items: [
			{
				itemId:'ResourceList',
				xtype:'dataview',
				flex:1,
			    useComponents: true,
			    defaultType: 'ResourcePlayDataItem'
			}
        ]
    }
});

