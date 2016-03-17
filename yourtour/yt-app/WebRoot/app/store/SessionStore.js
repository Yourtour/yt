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
    },

    /**
     * 保存到本地缓存
     * @param values
     */
    save: function (values) {
        var me = this;

        var v = [];
        if (Ext.isArray(values)) {
            v = values;
        } else {
            v.push(values);
        }

        var found = false;
        Ext.Array.forEach(v, function (value) {
            found = false;
            me.each(function (item) {
                if (item.get('key') == value.key) {
                    found = true;
                    item.set('value', value.value);
                }
            });

            if (!found) {
                me.add(v);
            }
        });

        me.sync();
    },

    /**
     * 本地缓存获取
     * @param key
     */
    get: function (key) {
        var me = this;
        var index = me.find('key', key);
        if (index >= 0) {
            var data = me.getAt(index);
            return data.get('value');
        }
    }
});
