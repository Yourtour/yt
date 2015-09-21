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
            create: 'http://localhost:8080/yt-web/rest/hotels/save',
            read: 'http://localhost:8080/yt-web/rest/hotels/loadPage',
            update: 'http://localhost:8080/yt-web/rest/hotels/save',
            destroy: 'http://localhost:8080/yt-web/rest/hotels/remove'
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