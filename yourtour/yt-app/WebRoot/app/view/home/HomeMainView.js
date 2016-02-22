Ext.define('YourTour.view.home.HomeMainView', {
    extend: 'YourTour.view.widget.XPage',
    xtype: 'HomeMainView',
    config: {
        id: 'HomeMainView',
        layout: 'vbox',

        items: [
            {
                xtype: 'xheaderbar',
                title: '首页',
                backButton: false
            },

            {
                xtype: 'xpagebody',
                layout: 'vbox',
                items: [
                    {
                        xtype: 'img',
                        src: 'resources/images/img_home.jpg',
                        height: 200,
                        margin:'0 0 10 0'
                    },

                    {
                        xtype: 'xdataview',
                        itemId: 'routeList',
                        scrollable: null,
                        defaultType: 'RouteListDataItemHBox'
                    }
                ]
            }
        ]
    }
});

