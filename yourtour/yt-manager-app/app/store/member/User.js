/**
 * Created by john on 15-8-25.
 */
Ext.define('yt_manager_app.store.member.User', {
    extend: 'Ext.data.Store',
    alias: 'store.user',

    model: 'yt_manager_app.model.member.User',

    pageSize: 20,

    proxy: {
        type: 'rest',
        format: 'json',
        api: {
            create: 'http://localhost:8080/yt-web/rest/users/save',
            read: 'http://localhost:8080/yt-web/rest/users/loadPage',
            update: 'http://localhost:8080/yt-web/rest/users/save',
            destroy: 'http://localhost:8080/yt-web/rest/users/remove'
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