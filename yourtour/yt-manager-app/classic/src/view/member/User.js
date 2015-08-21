/**
 * Created by john on 15-8-21.
 */
Ext.define('yt_manager_app.view.member.User', {
    extend: 'Ext.tab.Panel',
    xtype: 'user',

    controller: 'user',

    width: 400,
    height: 300,
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
        title: '所有会员',
        icon: '/resources/images/toggle-icon.png',
        items: [{
            xtype: 'grid',
            reference: 'grid_paging',
            loadMask: true,
            viewConfig: {
                trackOver: false,
                stripeRows: false
            },
            columns: [{
                id: 'userName',
                text: 'User name',
                dataIndex: 'userName',
                width: 120,
                sortable: true
            }, {
                id: 'nickName',
                text: 'Nick name',
                dataIndex: 'nickName',
                sortable: true
            }, {
                id: 'realName',
                text: 'Real name',
                dataIndex: 'realName',
                width: 120,
                sortable: true
            }, {
                id: 'role',
                text: 'Role',
                dataIndex: 'role',
                width: 100,
                sortable: true
            }, {
                id: 'mobileNo',
                text: 'Mobile',
                dataIndex: 'mobileNo',
                width: 150,
                sortable: true
            }, {
                id: 'email',
                text: 'Email',
                dataIndex: 'email',
                flex: 1,
                sortable: true
            }],
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
            },
            bbar: {
                xtype: 'pagingtoolbar',
                displayInfo: true,
                displayMsg: '{3}中的{0}-{1}',
                emptyMsg: '无数据'
            },
            flex: 1
        }]
    }, {
        title: '条件查询',
        icon: '/resources/images/user-profile/3.png',
        html: 'tab pane 2.'
    }, {
        title: '会员关系',
        icon: '/resources/images/user-profile/3.png',
        html: 'tab pane 3.'
    }]
});
