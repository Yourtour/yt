Ext.define('YourTour.view.along.AlongEditView', {
    extend: 'YourTour.view.widget.XPage',
    requires: ['YourTour.view.widget.XHeaderBar', 'YourTour.view.along.AlongListDataItem'],
    config: {
        id: 'AlongEditView',
        layout: 'card',
        items: [
            {
                xtype: 'xheaderbar',
                title: '发布结伴'
            }
        ]
    }
});

