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

    init: function() {
        var me = this,
            grid = me.lookupReference('user_crud_grid_paging');
        this.config.currentGrid = grid;
        var store = grid.getStore();
        store.load();
    },

    onTabChange: function (tabs, newTab, oldTab) {
        var me = this;
        var id = newTab.id;
        if (id == 'user-crud-tab') {
            // all member tab
            this.config.currentGrid = me.lookupReference('user_crud_grid_paging');
        } else if (id == 'user-cypher-tab') {
            // cyphere tab
            this.config.currentGrid = me.lookupReference('user_cypher_grid_paging');
        } else {
            // graph tab
            // TODO 图关系
        }
    }
});