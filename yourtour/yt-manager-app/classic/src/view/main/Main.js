/**
 * This class is the main view for the application. It is specified in app.js as the
 * "mainView" property. That setting automatically applies the "viewport"
 * plugin causing this view to become the body element (i.e., the viewport).
 *
 */
Ext.define('yt_manager_app.view.main.Main', {
    extend: 'Ext.panel.Panel',
    xtype: 'app-main',

    controller: 'main',
    viewModel: 'main',
    plugins: 'viewport',

    requires: [
        'yt_manager_app.store.NavigationTree',
        'Ext.list.Tree'
    ],

    layout: {
        type: 'hbox',
        align: 'stretch'
    },
    bodyBorder: true,

    defaults: {
        bodyPadding: 0
    },
    items: [{
        reference: 'left_panel',
        cls: 'treelist-with-nav',
        bind: {
            width: '{toggle.length}'
        },
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        border: false,
        items: [{
            xtype: 'toolbar',
            cls: 'yt-logo',
            items: [{
                xtype: 'button',
                tooltip: 'Toggle the left panel.',
                html: '<img class="main-logo" src="./resources/images/toggle-icon.png" alt="Toggle">',
                handler: 'onToggleNavigationSize'
            }, {
                xtype: 'tbtext',
                reference: 'main_text',
                cls: 'main-text',
                flex: 1,
                bind: {
                    html: '{logoHTML}'
                }
            }, '']
        }, {
            xtype: 'toolbar',
            cls: 'yt-logo',
            items: [{
                xtype: 'button',
                tooltip: 'Logout current user',
                html: '<img class="user-profile-image" src="./resources/images/user-profile/3.png" alt="Logout">',
                handler: 'onLogoutClicked'
            }, '->', {
                xtype: 'tbtext',
                cls: 'login-text',
                bind: {
                    text: 'user: <b>{username}</b>'
                }
            }]
        }, {
            xtype: 'container',
            cls: 'treelist-with-nav',
            reference: 'tree_list_container',
            layout: {
                type: 'vbox',
                align: 'stretch'
            },
            border: false,
            scrollable: 'y',
            flex: 1,
            items: [{
                xtype: 'treelist',
                reference: 'tree_list',
                ui: 'navigation',
                store: {
                    type: 'navigationTree'
                },
                scrollable: 'y',
                expanderFirst: false,
                expanderOnly: false,
                listeners: {
                    selectionchange: 'onTreelistSelectionChange'
                }
            }]
        }]
    }, {
        reference: 'right_panel',
        layout: 'vbox',
        border: false,
        flex: 1,
        items: [{
            reference: 'view_title',
            bind: {
                html: '<div class="view-title">{toggle.currentViewName}</div>'
            }
        }, {
            reference: 'main_card',
            itemId: 'contentPanel',
            flex: 1,
            layout: {
                type: 'card',
                anchor: '100%'
            }
        }]
    }]
});
