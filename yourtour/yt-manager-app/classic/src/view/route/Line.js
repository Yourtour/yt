/**
 * Created by john on 15-8-27.
 */
Ext.define('yt_manager_app.view.route.Line', {
    extend: 'Ext.tab.Panel',
    xtype: 'line',

    controller: 'line',

    defaults: {
        bodyPadding: 10,
        scrollable: true
    },

    cls: 'tab-panel',

    defaults: {
        width: 500,
        height: 300
    },

    listeners: {
        tabchange: 'onTabChange'
    },

    initComponent: function () {
        var lineGridColumns = [{
            text: '名称',
            dataIndex: 'name',
            width: 150,
            sortable: true
        }, {
            text: '简介',
            dataIndex: 'intro',
            width: 200,
            sortable: true
        }, {
            text: '目的地',
            dataIndex: 'place',
            width: 150,
            sortable: true
        }, {
            text: '特点',
            dataIndex: 'feature',
            width: 200,
            sortable: false,
            flex: 1
        }];

        var generalStore = new yt_manager_app.store.route.Line();

        var generalTab = Ext.create('yt_manager_app.view.widget.GeneralCRUDPanel', {
            id: 'line-crud-tab',
            nameEN: 'line',
            nameZHCN: '线路',
            gridName: 'line_crud_grid_paging',
            icon: './resources/images/toggle-icon.png',
            gridColumns: lineGridColumns,
            store: generalStore
        });

        Ext.apply(this, {
            items: [generalTab]
        });
        this.callParent();
    }
});