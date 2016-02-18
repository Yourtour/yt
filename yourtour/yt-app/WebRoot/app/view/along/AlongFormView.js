Ext.define('YourTour.view.along.AlongFormView', {
    extend: 'YourTour.view.widget.XPage',
    requires: ['YourTour.view.widget.XHeaderBar', 'YourTour.view.along.AlongListDataItem'],
    config: {
        id: 'AlongFormView',
        layout: 'card',
        items: [
            {
                xtype: 'xheaderbar',
                title: '结伴公告'
            },

            {
                xtype:'panel',
                itemId:'addPanel',
                html:'add'
            },

            {
                xtype:'panel',
                itemId:'contentPanel',
                html:'adsfsadf'
            }
        ]
    }
});

