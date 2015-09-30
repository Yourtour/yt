/**
 * Created by john on 15-8-21.
 */
Ext.define('yt_manager_app.view.resource.RestaurantController', {
    extend: 'yt_manager_app.view.widget.GeneralCRUDController',
    alias: 'controller.restaurant',

    config: {
        popupWindowName: 'restaurantPopupWindow',
        formWindowName: 'formWindow',
        name: '饭店'
    },

    init: function () {
        var me = this,
            grid = me.lookupReference('restaurant_crud_grid_paging');
        me.setCurrentGrid(grid);
        var store = grid.getStore();
        store.load();
    },

    createNewWindow: function () {
        return new yt_manager_app.view.resource.RestaurantWindow();
    },

    dowithRecord: function (data) {
        if (data == null) {
            return new yt_manager_app.model.resource.Restaurant({id: null});
        } else {
            return data;
        }
    },

    setRecordProxy: function (store) {
        yt_manager_app.model.resource.Restaurant.setProxy(store);
    },

    afterUpdateRecord: function (record, editable) {
        var me = this,
            star = me.lookupReference('star'),
            ratingParent = me.lookupReference('ratingParent'),
            placeParent = me.lookupReference('placeParent');
        record.set('star', star.getValue());
        if (ratingParent) {
            ratingParent.setDisabled(!editable);
        }
        placeParent.setDisabled(!editable);
    },

    afterLoadRecord: function (record) {
        var me = this,
            star = me.lookupReference('star');
        star.setValue(record.get('star'));
    },

    onPopupDivisionSelectWindow: function() {
        var me = this,
            division = me.lookupReference('place');
        // TODO 获取当前设定的区划
        var initDivision = me.getData().getData().place;
        var win = new yt_manager_app.view.basedata.DivisionSelectWindow({
            initDivision: initDivision,
            parentWindow: me
        });
        win.show();
    },

    setPlaceData: function(data) {
        var me = this,
            view = me.getView(),
            place = me.lookupReference('place'),
            record = me.getData();
        place.setValue(data.text);
        record.set('place', data.text);
        record.set('placeId', data.id);
    },

    onTabChange: function (tabs, newTab, oldTab) {
        var me = this;
        var id = newTab.id;
        if (id == 'restaurant-crud-tab') {
            // all member tab
            me.setCurrentGrid(me.lookupReference('restaurant_crud_grid_paging'));
        } else if (id == 'restaurant-cypher-tab') {
            // cyphere tab
            me.setCurrentGrid(me.lookupReference('restaurant_cypher_grid_paging'));
        } else {
            // graph tab
            // TODO 图关系
        }
    }
});