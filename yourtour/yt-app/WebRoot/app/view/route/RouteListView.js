Ext.define('YourTour.view.route.RouteListView', {
    extend: 'YourTour.view.widget.XPage',
    requires: ['Ext.Panel', 'YourTour.view.route.RouteListDataItem', 'YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.XProcessing'],
    config: {
        id: 'RouteListView',
        layout: 'vbox',
        items: [
            {
                xtype: 'xheaderbar'
            },

            {
                xtype: 'xdataview',
                itemId: 'routeList',
                flex:1,
                defaultType: 'RouteListDataItem'
            }
        ]
    }
});

