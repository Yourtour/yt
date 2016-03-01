Ext.define('YourTour.view.route.RouteListView', {
    extend: 'YourTour.view.widget.XPage',
    requires: ['Ext.Panel', 'YourTour.view.route.RouteListDataItemHBox','YourTour.view.route.RouteListDataItemVBox', 'YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.XProcessing'],
    config: {
        id: 'RouteListView',
        layout: 'card',
        items: [
            {
                xtype: 'xheaderbar'
            },

            {
                xtype:'xprocessing'
            },

            {
                xtype: 'xpagebody',
                items: [
                    {
                        xtype: 'xdataview',
                        itemId: 'routeList',
                        flex: 1,
                        defaultType: 'RouteListDataItemHBox'
                    }
                ]
            }
        ]
    }
});

