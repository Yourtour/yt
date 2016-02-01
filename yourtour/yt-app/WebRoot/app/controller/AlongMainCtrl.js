Ext.define('YourTour.controller.AlongMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{

       },
       
       control:{

       }
    },

    showAlongList:function(placeId, store){
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.along.AlongListView'));
    },

    showAlongInfo:function(alongId, along){
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.along.AlongInfoView'));
    }
});
