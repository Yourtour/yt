/**
 * Created by john on 15-8-27.
 */
Ext.define('yt_manager_app.view.resource.Hotel', {
    extend: 'Ext.tab.Panel',
    xtype: 'hotel',

    controller: 'hotel',

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
        var hotelGridColumns = [{
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
            text: '住宿标准',
            dataIndex: 'accommodationStandard',
            width: 200,
            sortable: false
        }, {
            text: '目的地',
            dataIndex: 'place',
            width: 100,
            sortable: true
        }, {
            text: '特色房',
            dataIndex: 'specialRoom',
            width: 200,
            sortable: false,
            flex: 1
        }];

        var generalStore = new yt_manager_app.store.resource.Hotel();

        var generalTab = Ext.create('yt_manager_app.view.widget.GeneralCRUDPanel', {
            id: 'hotel-crud-tab',
            nameEN: 'hotel',
            nameZHCN: '宾馆',
            gridName: 'hotel_crud_grid_paging',
            icon: '/resources/images/toggle-icon.png',
            gridColumns: hotelGridColumns,
            store: generalStore
        });

        Ext.apply(this, {
            items: [generalTab]
        });
        this.callParent();
    }
});