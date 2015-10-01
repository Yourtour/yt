/**
 * Created by john on 15-10-1.
 */
Ext.define('yt_manager_app.utils.Uri', {
    singleton: true,

    config: {
        /*
         * 设置跨域的地址，如果不跨域，则设置为：
         * corsAddress: './'
         */
        corsAddress: 'http://localhost:8080/yt-web/'
        //corsAddress: './'
    },

    packageUri: function(uri) {
        var me = this,
            puri = me.config.corsAddress + uri;
        Ext.Boot.debug(puri);
        return puri;
    }
});