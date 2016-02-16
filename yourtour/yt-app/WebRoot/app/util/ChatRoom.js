/**
 * Created by john on 16-2-15.
 */
Ext.define('YourTour.util.ChatRoom', {
    singleton: true,
    alias: 'ChatRoom',
    alternateClassName: 'YourTour.ChatRoom',
    config: {
        userKey: 'user',
        url: '',

        //server:'192.168.1.174:8080'
        //server:'192.168.2.102:8080'
        //server: '120.55.76.201:8080'
        server: 'localhost:8080',

        webSocket: null
    },

    constructor: function (config) {
        this.initConfig(config);
    },

    openChatRoom: function (url, fnOnOpen, fnOnClose, fnOnMessage) {
        if (url != this.getUrl()) {
            this.closeChatRoom();
        }

        var s = 'http://' + this.getServer() + '/yt-web/ws/chart';
        if (url == '') {
            throw new Error('The url is empty.');
        }
        if (url.substr(0, 1) != '/') {
            s += '/';
        }
        s += url;

        // create the WebSocket
        var ws = null;
        if ('WebSocket' in window) {
            ws = new WebSocket(s);
        } else if ('MozWebSocket' in window) {
            ws = new MozWebSocket(s);
        } else {
            throw new Error('The browser not support websocket!');
        }

        if (fnOnOpen != undefined) {
            ws.onopen = fnOnOpen;
        }
        if (fnOnClose != undefined) {
            ws.onclose = fnOnClose;
        }
        if (fnOnMessage != undefined) {
            ws.onmessage = fnOnMessage;
        }
        this.setWebSocket(ws);
        this.setUrl(url);
    },

    closeChatRoom: function () {
        if (this.getWebSocket() != null) {
            this.getWebSocket.close('Client was closed.');
        }
        this.setWebSocket(null);
        this.setUrl('');
    },

    sendMessage: function (message) {
        if (this.getWebSocket() == null) {
            throw new Error('The chat room not initialized, please invoke openChatRoom() at first.');
        }
        this.getWebSocket().send(message);
    }

});