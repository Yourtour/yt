Ext.define('YourTour.view.user.FootprintMainView', {
    extend: 'YourTour.view.widget.XPage',
    requires: ['YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.XLabel'],
    config: {
        id: 'FootprintMainView',
        layout: 'vbox',
        items: [
            {
                xtype: 'xheaderbar',
                title: '我的足迹',
                items: [
                ]
            },

            {
                xtype: 'xpagebody',
                layout: 'vbox',
                items: [

                ]
            }
        ]
    }
});

