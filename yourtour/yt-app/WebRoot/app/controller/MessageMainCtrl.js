Ext.define('YourTour.controller.MessageMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires: ['YourTour.util.ChatRoom'],
    config: {
       refs:{
    	   messageMainView:'#MessageMainView'
       },
       
       control:{
    	   '#MessageMainView #group':{
    		   tap:'onMessageGroup'
    	   }
       },
       
       routes:{
    	   '/message/session/:sessionId':'showMainPage'
       }
    },
    
    init:function(){
    	
    },
    
    showMainPage:function(chatRoomJson){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.common.MessageMainView'));


    	var me = this;
    	var messageMainView = me.getMessageMainView();
    	var headerBar = messageMainView.down('#headerbar');
        var roomType = chatRoomJson.type, roomCode = chatRoomJson.roomCode;
        if (roomType == 'place') {
            headerBar.setTitle('目的地聊天室');
            YourTour.util.ChatRoom.openChatRoom('place/' + roomCode, function(event) {
                me.onCommonOpen(event);
            }, function(event) {
                me.onCommonClose(event);
            }, function(event) {
                me.onCommonMessage(event);
            });
        } else if (roomType == 'route') {
            headerBar.setTitle('行程聊天室');
            YourTour.util.ChatRoom.openChatRoom('route/' + roomCode, function(event) {
                me.onCommonOpen(event);
            }, function(event) {
                me.onCommonClose(event);
            }, function(event) {
                me.onCommonMessage(event);
            });
        } else {
            headerBar.setTitle('动态聊天室');
            YourTour.util.ChatRoom.openChatRoom('dynamic/' + roomCode, function(event) {
                me.onCommonOpen(event);
            }, function(event) {
                me.onCommonClose(event);
            }, function(event) {
                me.onCommonMessage(event);
            });
        }
    },

    onCommonOpen: function(event) {
        // 通用的打开WebSocket操作
        console.log('WebSocket onOpen() ->');
        console.log(event);
    },

    onCommonClose: function(event) {
        // 通用的关闭WebSocket操作
        console.log('WebSocket onClose() ->');
        console.log(event);
    },

    onCommonMessage: function(event) {
        // 通用的接收到WebSocket实时消息
        console.log('WebSocket onMessage() ->');
        console.log(event);
    },
    
    onMessageGroup:function(){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.common.MessageGroupView'));
    }
});
