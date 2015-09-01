/**
 * Created by john on 15-8-27.
 */
Ext.define('yt_manager_app.view.route.Route', {
    extend: 'Ext.tab.Panel',
    xtype: 'route',

    controller: 'route',

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
        var routeGridColumns = [{
            text: 'name',
            dataIndex: 'name',
            width: 150,
            sortable: true
        }, {
            text: 'Line name',
            dataIndex: 'lineName',
            width: 150,
            sortable: true
        }, {
            text: 'Place',
            dataIndex: 'place',
            width: 150,
            sortable: true
        }, {
            text: 'Period',
            dataIndex: 'period',
            width: 120,
            sortable: true
        }, {
            text: 'Feature',
            dataIndex: 'feature',
            width: 120,
            sortable: false,
            flex: 1
        }];

        var generalTab = Ext.create('yt_manager_app.view.widget.GeneralCRUDPanel', {
            id: 'route-crud-tab',
            nameEN: 'route',
            nameZHCN: '行程',
            gridName: 'route_crud_grid_paging',
            icon: '/resources/images/toggle-icon.png',
            gridColumns: routeGridColumns
        });

        Ext.apply(this, {
            items: [generalTab]
        });
        this.callParent();
    }
});