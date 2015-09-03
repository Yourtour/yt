/**
 * Created by john on 15-8-25.
 */
Ext.define('yt_manager_app.store.Route', {
    extend: 'Ext.data.Store',
    alias: 'store.route',

    fields: [{
        name: 'id', type: 'int'
    }, {
        name: 'name', type: 'string'
    }, {
        name: 'imageUrl', type: 'string'
    }, {
        name: 'lineName', type: 'string'
    }, {
        name: 'intro', type: 'string'
    }, {
        name: 'feature', type: 'string'
    }, {
        name: 'reason', type: 'reason'
    }, {
        name: 'place', type: 'string'
    }, {
        name: 'startTime', type: 'int'
    }, {
        name: 'endTime', type: 'int'
    }, {
        name: 'period', type: 'number'
    }, {
        name: 'status', type: 'string'
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
        },
        writer: {
            type: 'json'
        }
    }
});