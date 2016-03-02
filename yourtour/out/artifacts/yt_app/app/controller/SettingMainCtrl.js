Ext.define('YourTour.controller.SettingMainCtrl', {
    extend: 'Ext.app.Controller',
    config: {
       refs:{
           userSettingView:'#UserSettingView'
       },
       
       control:{
           '#UserSettingView #btnQuit':{
                tap:'onUserQuitTap'
           }
       }
    },
    
    init:function(){
    },
    
    showPage:function(){
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.setting.UserSettingView'));
    },


    onUserQuitTap:function(){
        this.getApplication().quit();
    }
});
