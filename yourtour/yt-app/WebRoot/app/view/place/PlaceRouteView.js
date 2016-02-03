Ext.define('YourTour.view.place.PlaceRouteItem', {
    extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar','YourTour.view.route.RouteRecommendDataItem'],
    config: {
        id:'PlaceRouteItem',
        layout:'card',
      	items:[
            {
                xtype: 'xheaderbar',
            },

            {
                xtype:'xprocessing'
            },

            {
                xtype: 'xpagebody',
                layout: 'vbox',
                items: [
                    {
                        xtype: 'xdataview',
                        itemId: 'placeRouteList',
                        defaultType: 'RouteRecommendDataItem'
                    }
                ]
            }
        ]
    }
});

