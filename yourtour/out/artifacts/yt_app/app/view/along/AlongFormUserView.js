Ext.define('YourTour.view.along.AlongFormUserView', {
    extend: 'YourTour.view.widget.XPage',
    requires: ['YourTour.view.widget.XHeaderBar', 'YourTour.view.along.AlongView'],
    config: {
        id: 'AlongFormUserView',
        layout: 'card',
        items: [
            {
                xtype: 'xheaderbar',
                title: '结伴',
            },

            {
                xtype:'alongview',
                itemId:'contentPanel'
            },

            {
                xtype: 'xtoolbar',
                docked: 'bottom',
                itemId:'toolbar',
                items: [
                    {
                        xtype:'spacer',
                        flex:1
                    },
                    {
                        xtype: 'xbutton',
                        text: '关注',
                        icon: 'resources/icons/24/icon_favorite.png',
                        itemId: 'btnCheckin'
                    }, {
                        xtype:'xvline',
                        flex:1
                    }, {
                        xtype: 'xbutton',
                        text: '报名',
                        icon: 'resources/icons/24/icon_note.png',
                        itemId: 'btnNotes'
                    }
                    , {
                        xtype:'xvline',
                        flex:1
                    }, {
                        xtype: 'xbutton',
                        text: '留言',
                        icon: 'resources/icons/24/icon_message.png',
                        itemId: 'btnNotes'
                    },
                    {
                        xtype:'spacer',
                        flex:1
                    }
                ]
            }
        ]
    }
});

