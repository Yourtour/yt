Ext.define('YourTour.view.along.AlongListView', {
    extend: 'YourTour.view.widget.XPage',
    requires: ['YourTour.view.widget.XHeaderBar', 'YourTour.view.along.AlongListDataItem'],
    config: {
        id: 'AlongListView',
        layout: 'card',
        items: [
            {
                xtype: 'xheaderbar'
            },

            {
                xtype: 'xdataview',
                itemId: 'alongList',
                defaultType: 'AlongListDataItem'
            }
        ]
    }
});

