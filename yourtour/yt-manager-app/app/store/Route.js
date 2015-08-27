/**
 * Created by john on 15-8-25.
 */
Ext.define('yt_manager_app.store.Route', {
    extend: 'Ext.data.Store',
    alias: 'store.route',

    fields: [{
        name: 'id', type: 'int'
    }, {
        name: 'createdUserId', type: 'string'
    }, {
        name: 'createdTime', type: 'int'
    }, {
        name: 'updatedUserId', type: 'string'
    }, {
        name: 'updatedTime', type: 'int'
    }, {
        name: 'rowKey', type: 'string'
    }, {
        name: 'createdTimeStr', calculate: function(data) {
            var time = data.createdTime,
                date = new Date();
            date.setTime(time);
            return Ext.Date.format(date, 'Y/m/d H:i:s');
        }
    }, {
        name: 'updatedTimeStr', calculate: function(data) {
            if (data.updatedUserId == null || data.updatedUserId == '') {
                return '';
            }
            var time = data.updatedTime,
                date = new Date();
            date.setTime(time);
            return Ext.Date.format(date, 'Y/m/d H:i:s');
        }
    }],
    idProperty:'id',
    pageSize: 20,

    proxy: {
        type: 'rest',
        format: 'json',
        api: {
            create: 'http://localhost:8080/yt-web/rest/routes/create',
            read: 'http://localhost:8080/yt-web/rest/routes/loadPage',
            update: 'http://localhost:8080/yt-web/rest/routes/save',
            destroy: 'http://localhost:8080/yt-web/rest/routes/remove'
        },
        actionMethods: {
            create: 'POST',
            read: 'GET',
            update: 'POST',
            destroy: 'DELETE'
        },
        reader: {
            type: 'json',
            rootProperty: 'data',
            totalProperty: 'totalCount'
        }
    }
});