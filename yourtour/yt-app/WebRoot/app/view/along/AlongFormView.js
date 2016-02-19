Ext.define('YourTour.view.along.AlongFormView', {
    extend: 'YourTour.view.widget.XPage',
    requires: ['YourTour.view.widget.XHeaderBar', 'YourTour.view.along.AlongView'],
    config: {
        id: 'AlongFormView',
        layout: 'card',
        items: [
            {
                xtype: 'xheaderbar',
                title: '结伴',
                items:[
                    {
                        xtype: 'xbutton',
                        itemId: 'btnNew',
                        align: 'right',
                        icon:'resources/icons/24/icon_header_add.png'
                    },

                    {
                        xtype: 'xbutton',
                        itemId: 'btnEdit',
                        align: 'right',
                        icon:'resources/icons/24/icon_edit.png'
                    }
                ]
            },

            {
                xtype:'panel',
                itemId:'addPanel'
            },

            {
                xtype:'alongview',
                itemId:'contentPanel'
            }
        ]
    }
});

