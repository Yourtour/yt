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

    dowithRecord: function (data) {
        if (data == null) {
            return new yt_manager_app.model.route.Line({id: null});
        } else {
            return data;
        }
    },

    setRecordProxy: function (store) {
        yt_manager_app.model.route.Line.setProxy(store);
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
            place = me.lookupReference('place'),
            scenes = me.lookupReference('scenes'),
            hotels = me.lookupReference('hotels'),
            restaurants = me.lookupReference('restaurants'),
            record = me.getData();
        place.setValue(data.text);
        record.set('place', data.text);
        record.set('placeId', data.id);

        me.onPlaceChanged();
    },

    onPlaceChanged: function (item, newValue, oldValue, eOpts) {
        var me = this,
            place = me.lookupReference('place'),
            scenes = me.lookupReference('scenes'),
            hotels = me.lookupReference('hotels'),
            restaurants = me.lookupReference('restaurants'),
            record = me.getData();

        var placeId = record.get('placeId');
        // 加载该目的地的景点
        Ext.Ajax.request({
            asyn: true,
            url: 'http://localhost:8080/yt-web/rest/lines/' + placeId + '/scenes',
            success: function (response, opts) {
                var responseData = Ext.decode(response.responseText);
                scenes.getStore().setData(responseData.data);
                scenes.setValue(record.get('scenes'));
            },
            failure: function (response, opts) {
                Ext.MessageBox.alert('Error', Ext.String.format('根据指定的目的地[{0}]获取景点数据失败！错误码： {1}。', placeId, response.status));
            }
        });

        // 加载该目的地的宾馆
        Ext.Ajax.request({
            asyn: true,
            url: 'http://localhost:8080/yt-web/rest/lines/' + placeId + '/hotels',
            success: function (response, opts) {
                var responseData = Ext.decode(response.responseText);
                hotels.getStore().setData(responseData.data);
                hotels.setValue(record.get('hotels'));
            },
            failure: function (response, opts) {
                Ext.MessageBox.alert('Error', Ext.String.format('根据指定的目的地[{0}]获取宾馆数据失败！错误码： {1}。', placeId, response.status));
            }
        });

        // 加载该目的地的酒店
        Ext.Ajax.request({
            asyn: true,
            url: 'http://localhost:8080/yt-web/rest/lines/' + placeId + '/restaurants',
            success: function (response, opts) {
                var responseData = Ext.decode(response.responseText);
                restaurants.getStore().setData(responseData.data);
                restaurants.setValue(record.get('restaurants'));
            },
            failure: function (response, opts) {
                Ext.MessageBox.alert('Error', Ext.String.format('根据指定的目的地[{0}]获取饭店数据失败！错误码： {1}。', placeId, response.status));
            }
        });
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