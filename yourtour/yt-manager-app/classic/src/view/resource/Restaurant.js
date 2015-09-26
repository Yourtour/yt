/**
 * Created by john on 15-8-27.
 */
Ext.define('yt_manager_app.view.resource.Restaurant', {
    extend: 'Ext.tab.Panel',
    xtype: 'restaurant',

    controller: 'restaurant',

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
        var restaurantGridColumns = [{
            text: '名称',
            dataIndex: 'name',
            width: 150,
            sortable: true
        }, {
            xtype: 'widgetcolumn',
            text: '星级',
            dataIndex: 'star',
            width: 100,
            widget: {
                xtype: 'rating',
                trackOver: false
            }
        }, {
            text: '餐饮标准',
            dataIndex: 'foodStandard',
            width: 200,
            sortable: false
        }, {
            text: '目的地',
            dataIndex: 'place',
            width: 100,
            sortable: true
        }, {
            text: '特色菜品',
            dataIndex: 'deliciouFood',
            width: 200,
            sortable: false,
            flex: 1
        }];

        var generalStore = new yt_manager_app.store.resource.Restaurant();

        var generalTab = Ext.create('yt_manager_app.view.widget.GeneralCRUDPanel', {
            id: 'restaurant-crud-tab',
            nameEN: 'restaurant',
            nameZHCN: '饭店',
            gridName: 'restaurant_crud_grid_paging',
            icon: './resources/images/toggle-icon.png',
            gridColumns: restaurantGridColumns,
            store: generalStore
        });

        Ext.apply(this, {
            items: [generalTab]
        });
        this.callParent();
    }
});