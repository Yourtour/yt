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
            text: '星级',
            dataIndex: 'star',
            width: 120,
            sortable: false
        }, {
            text: '地址',
            dataIndex: 'address',
            width: 150,
            sortable: true
        }, {
            text: '必游景点',
            dataIndex: 'specialScene',
            width: 200,
            sortable: false,
            flex: 1
        }];

        var generalTab = Ext.create('yt_manager_app.view.widget.GeneralCRUDPanel', {
            id: 'scene-crud-tab',
            nameEN: 'scene',
            nameZHCN: '景点',
            gridName: 'scene_crud_grid_paging',
            icon: '/resources/images/toggle-icon.png',
            gridColumns: sceneGridColumns
        });

        Ext.apply(this, {
            items: [generalTab]
        });
        this.callParent();
    }
});