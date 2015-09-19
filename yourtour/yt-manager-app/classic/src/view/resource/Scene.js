/**
 * Created by john on 15-8-27.
 */
Ext.define('yt_manager_app.view.resource.Scene', {
    extend: 'Ext.tab.Panel',
    xtype: 'scene',

    controller: 'scene',

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
        var sceneGridColumns = [{
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
            text: '简介',
            dataIndex: 'intro',
            width: 200,
            sortable: false
        }, {
            text: '目的地',
            dataIndex: 'place',
            width: 100,
            sortable: true
        }, {
            text: '必游景点',
            dataIndex: 'specialScene',
            width: 200,
            sortable: false,
            flex: 1
        }];

        var generalStore = new yt_manager_app.store.resource.Scene();

        var generalTab = Ext.create('yt_manager_app.view.widget.GeneralCRUDPanel', {
            id: 'scene-crud-tab',
            nameEN: 'scene',
            nameZHCN: '景点',
            gridName: 'scene_crud_grid_paging',
            icon: '/resources/images/toggle-icon.png',
            gridColumns: sceneGridColumns,
            store: generalStore
        });

        Ext.apply(this, {
            items: [generalTab]
        });
        this.callParent();
    }
});