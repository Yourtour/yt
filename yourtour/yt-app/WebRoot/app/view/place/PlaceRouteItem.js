Ext.define('YourTour.view.place.PlaceRouteItem', {
	extend: 'Ext.Container',
    xtype: 'PlaceRouteItem',
    requires:['YourTour.view.widget.XDataView','YourTour.view.route.RouteRecommendDataItem'],
    config: {
        layout:'vbox',
      	items:[
            {
                xtype: 'xspacer'
            },
            {
                xtype: 'xlabel',
                itemId:'placeMorelines',
                cls: 'underline x-xlabel-normal',
                indicator:'nav-arrow',
                html: '游徒行程'
            }
        ]
    }
});

