Ext.define('YourTour.controller.MessageMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires: ['YourTour.util.ChatRoom'],
    config: {
        refs: {
            messageMainView: '#MessageMainView',
            messageList:'#MessageMainView #MessageList'
        },

        control: {
            '#MessageMainView #group': {
                tap: 'onMessageGroup'
            },

            '#MessageMainView #btnSend': {
                tap: 'onSendMessage'
            }
        },

        routes: {
            '/message/session/:sessionId': 'showMainPage'
        }
    },

    init: function () {

    },

    showMainPage: function (chatRoomJson) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.common.MessageMainView'));

        var me = this,
            messageMainView = me.getMessageMainView(),
            headerBar = messageMainView.down('#headerbar'),
            roomType = chatRoomJson.type,
            roomCode = chatRoomJson.roomCode,
            messageList = me.getMessageList();

        var messageStore = Ext.create('Ext.data.Store', {model:'YourTour.model.MessageContentModel'});
        messageList.setStore(messageStore);

        if (roomType == 'place') {
            headerBar.setTitle('目的地聊天室');
            YourTour.util.ChatRoom.openChatRoom('place/' + roomCode, function (event) {
                me.onCommonOpen(event);
            }, function (event) {
                me.onCommonClose(event);
            }, function (event) {
                me.onCommonMessage(event);
            });
        } else if (roomType == 'route') {
            headerBar.setTitle('行程聊天室');
            YourTour.util.ChatRoom.openChatRoom('route/' + roomCode, function (event) {
                me.onCommonOpen(event);
            }, function (event) {
                me.onCommonClose(event);
            }, function (event) {
                me.onCommonMessage(event);
            });
        } else {
            headerBar.setTitle('动态聊天室');
            YourTour.util.ChatRoom.openChatRoom('dynamic/' + roomCode, function (event) {
                me.onCommonOpen(event);
            }, function (event) {
                me.onCommonClose(event);
            }, function (event) {
                me.onCommonMessage(event);
            });
        }
    },

    onCommonOpen: function (event) {
        // 通用的打开WebSocket操作
        console.log('WebSocket onOpen() ->');
        console.log(event);
    },

    onCommonClose: function (event) {
        // 通用的关闭WebSocket操作
        console.log('WebSocket onClose() ->');
        console.log(event);
    },

    onCommonMessage: function (event) {
        var me = this, messageList = me.getMessageList(), data = event.data;

        var messageStore = messageList.getStore();
        var model = Ext.create('YourTour.model.MessageContentModel', data);
        messageStore.add(model);

        // 通用的接收到WebSocket实时消息
        console.log('WebSocket onMessage() ->');
        console.log(event);
    },

    onMessageGroup: function () {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.common.MessageGroupView'));
    },

    onSendMessage: function () {
        var me = this,
            messageMainView = me.getMessageMainView(),
            content = messageMainView.down('#content');

        var message = {type: 'MESSAGE', messageType: 'text/plain', notice: false, textMessage: content.getValue()};
        console.log(message);
        YourTour.util.ChatRoom.sendMessage(message);
    }
});
