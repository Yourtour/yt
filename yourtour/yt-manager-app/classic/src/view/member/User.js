/**
 * Created by john on 15-8-21.
 */
Ext.define('yt_manager_app.view.member.User', {
    extend: 'Ext.tab.Panel',
    xtype: 'user',

    controller: 'user',

    defaults: {
        bodyPadding: 10,
        scrollable: true
    },

    requires: [
        'Ext.data.*',
        'Ext.grid.*',
        'Ext.util.*',
        'Ext.toolbar.Paging'
    ],

    cls: 'tab-panel',

    defaults: {
        width: 500,
        height: 300
    },

    items: [{
        id: 'general-tab',
        title: '所有会员',
        icon: '/resources/images/toggle-icon.png',
        items: [{
            xtype: 'userGrid',
            reference: 'grid_paging',
            tbar: {
                xtype: 'toolbar',
                items: ['->', {
                    xtype: 'button',
                    tooltip: 'Show details.',
                    html: '<img src="/resources/images/user-profile/3.png" alt="Details">',
                    handler: 'onShowDetails'
                }, '-', {
                    xtype: 'button',
                    tooltip: 'Add a user.',
                    html: '<img src="/resources/images/user-profile/3.png" alt="Detail">',
                    handler: 'onAddUser'
                }, {
                    xtype: 'button',
                    tooltip: 'Modify the user.',
                    html: '<img src="/resources/images/user-profile/3.png" alt="Detail">',
                    handler: 'onModifyUser'
                }, '-', {
                    xtype: 'button',
                    tooltip: 'Delete the user.',
                    html: '<img src="/resources/images/user-profile/3.png" alt="Detail">',
                    handler: 'onDeleteUser'
                }]
            }
        }]
    }, {
        id: 'cypher-tab',
        title: '条件查询',
        icon: '/resources/images/user-profile/3.png',
        items: [{
            xtype: 'userGrid',
            reference: 'grid_paging_condition',
            tbar: {
                xtype: 'toolbar',
                items: [{
                    xtype: 'textareafield',
                    fieldLabel: 'Cypher',
                    reference: 'cypher',
                    name: 'cypher',
                    width: 800,
                    minWidth: 500,
                    maxWidth: 1000,
                    shrinkWrap: 0,
                    emptyText: 'Cypher查询语句'
                }, {
                    xtype: 'tool',
                    tooltip: 'Query the cypher statement.',
                    type: 'search',
                    handler: 'onQueryCypher'
                }, '->', {
                    xtype: 'button',
                    tooltip: 'Show details.',
                    html: '<img src="/resources/images/user-profile/3.png" alt="Details">',
                    handler: 'onShowDetails'
                }, '-', {
                    xtype: 'button',
                    tooltip: 'Add a user.',
                    html: '<img src="/resources/images/user-profile/3.png" alt="Detail">',
                    handler: 'onAddUser'
                }, {
                    xtype: 'button',
                    tooltip: 'Modify the user.',
                    html: '<img src="/resources/images/user-profile/3.png" alt="Detail">',
                    handler: 'onModifyUser'
                }, '-', {
                    xtype: 'button',
                    tooltip: 'Delete the user.',
                    html: '<img src="/resources/images/user-profile/3.png" alt="Detail">',
                    handler: 'onDeleteUser'
                }]
            }
        }]
    }, {
        id: 'graph-tab',
        title: '会员关系',
        icon: '/resources/images/user-profile/3.png',
        html: 'tab pane 3.'
    }],

    listeners: {
        tabchange: 'onTabChange'
    }
});
