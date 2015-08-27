/**
 * Created by john on 15-8-25.
 */
Ext.define('yt_manager_app.store.User', {
    extend: 'Ext.data.Store',
    alias: 'store.user',

    fields: [{
        name: 'id', type: 'int'
    }, {
        name: 'userName', type: 'string'
    }, {
        name: 'password', type: 'string'
    }, {
        name: 'realName', type: 'string'
    }, {
        name: 'nickName', type: 'string'
    }, {
        name: 'gender', type: 'string'
    }, {
        name: 'birthday', type: 'int'
    }, {
        name: 'imageUrl', type: 'string'
    }, {
        name: 'character', type: 'string'
    }, {
        name: 'mobileNo', type: 'string'
    }, {
        name: 'email', type: 'string'
    }, {
        name: 'residence', type: 'string'
    }, {
        name: 'nativePlace', type: 'string'
    }, {
        name: 'constellation', type: 'string'
    }, {
        name: 'role', type: 'string'
    }, {
        name: 'rank', type: 'int'
    }, {
        name: 'status', type: 'string'
    }, {
        name: 'slogan', type: 'string'
    }, {
        name: 'createdUserId', type: 'string'
    }, {
        name: 'createdTime', type: 'int'
    }, {
        name: 'updatedUserId', type: 'string'
    }, {
        name: 'updateTime', type: 'int'
    }, {
        name: 'rowKey', type: 'string'
    }],

    proxy: {
        type: 'rest',
        format: 'json',
        api: {
            create: 'http://localhost:8080/yt-web/rest/user/create',
            read: 'http://localhost:8080/yt-web/rest/users/loadPage',
            update: 'http://localhost:8080/yt-web/rest/users/save',
            destroy: 'http://localhost:8080/yt-web/rest/users/remove'
        },
        actionMethods: {
            create: 'POST',
            read: 'GET',
            update: 'POST',
            destroy: 'DELETE'
        },
        reader: {
            type: 'json'
        }
    }
});