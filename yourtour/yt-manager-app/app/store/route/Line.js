/**
 * Created by john on 15-8-25.
 */
Ext.define('yt_manager_app.store.route.Line', {
    extend: 'Ext.data.Store',
    alias: 'store.line',

    model: 'yt_manager_app.model.route.Line',

    pageSize: 20,

    proxy: {
        type: 'rest',
        format: 'json',
        api: {
            create: 'http://localhost:8080/yt-web/rest/lines/save',
            read: 'http://localhost:8080/yt-web/rest/lines/loadPage',
            update: 'http://localhost:8080/yt-web/rest/lines/save',
            destroy: 'http://localhost:8080/yt-web/rest/lines/remove'
        },
        actionMethods: {
            create: 'POST',
            read: 'GET',
            update: 'POST',
            destroy: 'GET'
        },
        reader: {
            type: 'json',
            rootProperty: 'data',
            totalProperty: 'totalCount'
        },
        writer: {
            type: 'json',
            writeAllFields: true
        }
    }
});