/**
 * Created by john on 15-9-16.
 */
Ext.define('yt_manager_app.view.widget.DivisionTree', {
    extend: 'Ext.tree.Panel',
    alias: 'widget.divisionTree',
    xtype: 'divisionTree',

    store: (function() {
        return new yt_manager_app.store.basedata.Division();
    })(),

    useArrows: true,
    rootVisible: false,

    columns: [{
        xtype: 'treecolumn',
        text: 'Name',
        width: 200,
        sortable: true,
        dataIndex: 'text'
    }, {
        text: 'Code',
        flex: 1,
        width: 80,
        dataIndex: 'code',
        sortable: true
    }, {
        text: 'shorter',
        flex: 1,
        width: 80,
        dataIndex: 'shorter',
        sortable: true
    }],

    selModel: {
        allowDeselect: true,
        listeners: {
            selectionchange: 'onSelectionChange'
        }
    }
});