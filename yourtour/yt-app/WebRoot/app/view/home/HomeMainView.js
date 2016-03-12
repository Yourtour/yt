Ext.define('YourTour.view.home.HomeMainView', {
    extend: 'Ext.Container',
    xtype: 'HomeMainView',
    config: {
        id: 'HomeMainView',
        layout:'vbox',
        items: [
            {
                xtype: 'xheaderbar',
                title: '首页',
                backButton: false,
                items:[
                    {
                        xtype: "xbutton",
                        icon:'resources/icons/24/icon_header_position.png',
                        itemId:'btnPosition',
                        align:'right'
                    }
                ]
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

