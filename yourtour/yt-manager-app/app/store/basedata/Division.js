/**
 * Created by john on 15-9-16.
 */
Ext.define('yt_manager_app.store.basedata.Division', {
    extend: 'Ext.data.TreeStore',
    alias: 'store.division',

    model: 'yt_manager_app.model.basedata.Division',

    idProperty: 'graphId',
    pageSize: 0,

    proxy: {
        type: 'rest',
        format: 'json',
        api: {
            create: (function () {
                return Ext.Boot.packageUri('rest/divisions/save');
            })(),
            read: (function () {
                return Ext.Boot.packageUri('rest/divisions/load');
            })(),
            update: (function () {
                return Ext.Boot.packageUri('rest/divisions/save');
            })(),
            destroy: (function () {
                return Ext.Boot.packageUri('rest/divisions/remove');
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
            rootProperty: 'data'
        },
        writer: {
            type: 'json',
            writeAllFields: true
        }
    }
});