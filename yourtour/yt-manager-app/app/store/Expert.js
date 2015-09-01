/**
 * Created by john on 15-8-31.
 */
Ext.define('yt_manager_app.store.Expert', {
    extend: 'Ext.data.Store',
    model: 'yt_manager_app.model.Expert',

    pageSize: 20,

    data: [{
        code: 'john', name: 'John Peng', age: 42
    }, {
        code: 'tony', name: 'Tony Zhang', age: 42
    }, {
        code: 'nan', name: 'Nan Fang', age: 38
    }],

    proxy: {
        type: 'rest',
        format: 'json',
        api: {
            create: 'http://localhost:8080/yt-web/rest/experts/save',
            read: 'http://localhost:8080/yt-web/rest/experts/loadPage',
            update: 'http://localhost:8080/yt-web/rest/experts/save',
            destroy: 'http://localhost:8080/yt-web/rest/experts/remove'
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