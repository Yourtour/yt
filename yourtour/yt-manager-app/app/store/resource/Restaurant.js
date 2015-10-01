/**
 * Created by john on 15-8-25.
 */
Ext.define('yt_manager_app.store.resource.Restaurant', {
    extend: 'Ext.data.Store',
    alias: 'store.restaurant',

    model: 'yt_manager_app.model.resource.Restaurant',

    pageSize: 20,

    proxy: {
        type: 'rest',
        format: 'json',
        api: {
            create: (function() {
                return yt_manager_app.utils.Uri.packageUri('rest/restaurants/save');
            })(),
            read: (function() {
                return yt_manager_app.utils.Uri.packageUri('rest/restaurants/loadPage');
            })(),
            update: (function() {
                return yt_manager_app.utils.Uri.packageUri('rest/restaurants/save');
            })(),
            destroy: (function() {
                return yt_manager_app.utils.Uri.packageUri('rest/restaurants/remove')
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