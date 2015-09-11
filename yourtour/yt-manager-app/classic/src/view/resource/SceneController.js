/**
 * Created by john on 15-8-21.
 */
Ext.define('yt_manager_app.view.resource.SceneController', {
    extend: 'yt_manager_app.view.widget.GeneralCRUDController',
    alias: 'controller.scene',

    config: {
        popupWindowName: 'scenePopupWindow',
        formWindowName: 'formWindow',
        name: '景点'
    },

    init: function () {
        var me = this,
            grid = me.lookupReference('scene_crud_grid_paging');
        me.setCurrentGrid(grid);
        var store = grid.getStore();
        store.load();
    },

    createNewWindow: function () {
        return new yt_manager_app.view.resource.SceneWindow();
    },

    createNewRecord: function (data) {
        if (data == null) {
            return new yt_manager_app.model.resource.Scene();
        } else {
            return new yt_manager_app.model.resource.Scene(data);
        }
    },

    setRecordProxy: function (store) {
        yt_manager_app.model.resource.Scene.setProxy(store);
    },

    onTabChange: function (tabs, newTab, oldTab) {
        var me = this;
        var id = newTab.id;
        if (id == 'scene-crud-tab') {
            // all member tab
            me.setCurrentGrid(me.lookupReference('scene_crud_grid_paging'));
        } else if (id == 'scene-cypher-tab') {
            // cyphere tab
            me.setCurrentGrid(me.lookupReference('scene_cypher_grid_paging'));
        } else {
            // graph tab
            // TODO 图关系
        }
    }
});