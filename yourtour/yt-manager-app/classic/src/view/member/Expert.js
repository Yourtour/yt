/**
 * Created by john on 15-8-31.
 */
Ext.define('yt_manager_app.view.member.Expert', {
    extend: 'Ext.container.Container',
    xtype: 'expert',

    controller: 'expert',

    defaults: {
        bodyPadding: 10,
        scrollable: true
    },

    columns: [{
        text: 'Code',
        dataIndex: 'code',
        width: 120,
        sortable: true
    }, {
        text: 'Name',
        dataIndex: 'name',
        width: 150,
        sortable: true
    }, {
        text: 'Age',
        dataIndex: 'age',
        width: 100,
        sortable: true
    }]
    //items: []
});