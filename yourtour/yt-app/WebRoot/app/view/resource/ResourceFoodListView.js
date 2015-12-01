Ext.define('YourTour.view.resource.ResourceFoodListView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.resource.ResourceFoodDataItem'],
    xtype:'ResourceFoodListView',
    config: {
    	itemId:'ResourceFoodListView',
    	layout:'vbox',
    	fullscreen: true,
        items: [
			{
				itemId:'ResourceList',
				xtype:'dataview',
				flex:1,
			    useComponents: true,
			    defaultType: 'ResourceFoodDataItem'
			}
        ]
    }
});

