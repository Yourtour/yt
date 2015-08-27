/**
 * Created by john on 15-8-27.
 */
Ext.define('yt_manager_app.view.widget.GeneralGrid', {
    extend: 'Ext.grid.Panel',
    xtype: 'generalGrid',

    requires: [
        'Ext.data.*',
        'Ext.grid.*',
        'Ext.util.*',
        'Ext.toolbar.Paging'
    ],

    loadMask: true,
    viewConfig: {
        trackOver: false,
        stripeRows: false
    },
    bbar: {
        xtype: 'pagingtoolbar',
        displayInfo: false,
        // TODO store中有数据了，但是显示的信息仍然时“无数据”？
        displayMsg: '{2}中的{0}-{1}',
        emptyMsg: '无数据'
    },
    flex: 1
});