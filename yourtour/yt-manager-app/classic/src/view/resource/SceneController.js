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

    dowithRecord: function (data) {
        if (data == null) {
            return new yt_manager_app.model.resource.Scene({id: null});
        } else {
            return data;
        }
    },

    setRecordProxy: function (store) {
        yt_manager_app.model.resource.Scene.setProxy(store);
    },

    afterUpdateRecord: function (record) {
        var me = this,
            star = me.lookupReference('star');
        record.set('star', star.getValue());
    },

    afterLoadRecord: function (record, editable) {
        var me = this,
            star = me.lookupReference('star'),
            ratingParent = me.lookupReference('ratingParent'),
            placeParent = me.lookupReference('placeParent');
        star.setValue(record.get('star'));
        if (ratingParent) {
            ratingParent.setDisabled(!editable);
        }
        placeParent.setDisabled(!editable);
    },

    onPopupDivisionSelectWindow: function () {
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

    setPlaceData: function (data) {
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