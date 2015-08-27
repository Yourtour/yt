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
        var routeGridColumns = [];

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