Ext.define('YourTour.controller.MessageMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
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
    
    showMainPage:function(sessionId){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.common.MessageMainView'));
    	
    	var me = this;
    	var messageMainView = me.getMessageMainView();
    	var headerBar = messageMainView.down('#headerbar');
    	headerBar.setTitle('行程讨论');
    },
    
    onMessageGroup:function(){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.common.MessageGroupView'));
    }
});
