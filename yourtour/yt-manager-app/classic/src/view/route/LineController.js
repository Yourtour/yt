/**
 * Created by john on 15-8-21.
 */
Ext.define('yt_manager_app.view.route.LineController', {
    extend: 'yt_manager_app.view.widget.GeneralCRUDController',
    alias: 'controller.line',

    config: {
        popupWindowName: 'linePopupWindow',
        formWindowName: 'formWindow',
        name: '线路'
    },

    init: function () {
        var me = this,
            grid = me.lookupReference('line_crud_grid_paging');
        me.setCurrentGrid(grid);
        var store = grid.getStore();
        store.load();
    },

    createNewWindow: function () {
        return new yt_manager_app.view.route.LineWindow();
    },

    createNewRecord: function (data) {
        if (data == null) {
            return new yt_manager_app.model.route.Line({id: null});
        } else {
            return new yt_manager_app.model.route.Line(data);
        }
    },

    setRecordProxy: function (store) {
        yt_manager_app.model.route.Line.setProxy(store);
    },

    onTabChange: function (tabs, newTab, oldTab) {
        var me = this;
        var id = newTab.id;
        if (id == 'line-crud-tab') {
            // all member tab
            me.setCurrentGrid(me.lookupReference('line_crud_grid_paging'));
        } else if (id == 'line-cypher-tab') {
            // cyphere tab
            me.setCurrentGrid(me.lookupReference('line_cypher_grid_paging'));
        } else {
            // graph tab
            // TODO 图关系
        }
    }
});