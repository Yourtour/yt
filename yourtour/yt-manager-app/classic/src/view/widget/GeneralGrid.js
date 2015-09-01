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
    flex: 1,

    initComponent: function() {
        var store = this.getStore();
        Ext.apply(this, {
            bbar: {
                xtype: 'pagingtoolbar',
                displayInfo: true,
                store: store,
                displayMsg: '{2}中的{0}-{1}',
                emptyMsg: '无数据'
            }
        });

        this.callParent();
    }
});