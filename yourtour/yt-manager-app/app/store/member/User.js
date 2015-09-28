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
            create: (function () {
                return Ext.Boot.packageUri('rest/users/save');
            })(),
            read: (function () {
                return Ext.Boot.packageUri('rest/users/loadPage');
            })(),
            update: (function() {
                return Ext.Boot.packageUri('rest/users/save');
            })(),
            destroy: (function() {
                return Ext.Boot.packageUri('rest/users/remove');
            })()
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