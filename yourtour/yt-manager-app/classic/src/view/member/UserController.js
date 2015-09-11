/**
 * Created by john on 15-8-21.
 */
Ext.define('yt_manager_app.view.member.UserController', {
    extend: 'yt_manager_app.view.widget.GeneralCypherController',
    alias: 'controller.user',

    config: {
        popupWindowName: 'userPopupWindow',
        formWindowName: 'formWindow',
        name: '用户'
    },

    init: function () {
        var me = this,
            grid = me.lookupReference('user_crud_grid_paging');
        me.setCurrentGrid(grid);
        var store = grid.getStore();
        store.load();
    },

    createNewWindow: function () {
        return new yt_manager_app.view.member.UserWindow();
    },

    createNewRecord: function (data) {
        if (data == null) {
            return new yt_manager_app.model.member.User();
        } else {
            return new yt_manager_app.model.member.User(data);
        }
    },

    setRecordProxy: function (store) {
        yt_manager_app.model.member.User.setProxy(store);
    },

    onTabChange: function (tabs, newTab, oldTab) {
        var me = this;
        var id = newTab.id;
        if (id == 'user-crud-tab') {
            // all member tab
            me.setCurrentGrid(me.lookupReference('user_crud_grid_paging'));
        } else if (id == 'user-cypher-tab') {
            // cyphere tab
            me.setCurrentGrid(me.lookupReference('user_cypher_grid_paging'));
        } else {
            // graph tab
            // TODO 图关系
        }
    }
});