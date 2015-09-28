/**
 * Created by john on 15-8-25.
 */
Ext.define('yt_manager_app.store.resource.Scene', {
    extend: 'Ext.data.Store',
    alias: 'store.scene',

    model: 'yt_manager_app.model.resource.Scene',

    pageSize: 20,

    proxy: {
        type: 'rest',
        format: 'json',
        api: {
            create: (function() {
                return Ext.Boot.packageUri('rest/scenes/save');
            })(),
            read: (function() {
                return Ext.Boot.packageUri('rest/scenes/loadPage');
            })(),
            update: (function() {
                return Ext.Boot.packageUri('rest/scenes/save');
            })(),
            destroy: (function() {
                return Ext.Boot.packageUri('rest/scenes/remove');
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