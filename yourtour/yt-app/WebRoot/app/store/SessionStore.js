/**
 * 用来存储本次访问会话数据，会话数据在访问结束后由webview自动清除，无需额外编写代码干预
 */
Ext.define('YourTour.store.SessionStore', {
    extend: 'Ext.data.Store',

    config:{
        fields: ['key', 'value'],

        proxy: {
            type: 'sessionstorage',
            id  : 'YTKey'
        }
    }
});
