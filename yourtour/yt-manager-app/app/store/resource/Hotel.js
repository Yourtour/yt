/**
 * Created by john on 15-8-25.
 */
Ext.define('yt_manager_app.store.resource.Hotel', {
    extend: 'Ext.data.Store',
    alias: 'store.hotel',

    model: 'yt_manager_app.model.resource.Hotel',

    pageSize: 20,

    proxy: {
        type: 'rest',
        format: 'json',
        api: {
            create: (function() {
                return Ext.Boot.packageUri('rest/hotels/save');
            })(),
            read: (function() {
                return Ext.Boot.packageUri('rest/hotels/loadPage');
            })(),
            update: (function() {
                return Ext.Boot.packageUri('rest/hotels/save');
            })(),
            destroy: (function() {
                return Ext.Boot.packageUri('rest/hotels/remove');
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