/**
 * Created by john on 15-8-25.
 */
Ext.define('yt_manager_app.view.member.UserGrid', {
    extend: 'Ext.grid.Panel',
    xtype: 'userGrid',

    loadMask: true,
    viewConfig: {
        trackOver: false,
        stripeRows: false
    },
    columns: [{
        text: 'User name',
        dataIndex: 'userName',
        width: 120,
        sortable: true
    }, {
        text: 'Nick name',
        dataIndex: 'nickName',
        sortable: true
    }, {
        text: 'Real name',
        dataIndex: 'realName',
        width: 120,
        sortable: true
    }, {
        text: 'Role',
        dataIndex: 'role',
        width: 100,
        sortable: true
    }, {
        text: 'Mobile',
        dataIndex: 'mobileNo',
        width: 150,
        sortable: true
    }, {
        text: 'Email',
        dataIndex: 'email',
        flex: 1,
        sortable: true
    }],
    bbar: {
        xtype: 'pagingtoolbar',
        displayInfo: true,
        displayMsg: '{3}中的{0}-{1}',
        emptyMsg: '无数据'
    },
    flex: 1
});