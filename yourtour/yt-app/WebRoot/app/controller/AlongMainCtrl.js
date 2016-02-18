Ext.define('YourTour.controller.AlongMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
        refs: {
            alongFormView:'#AlongFormView'
        },

        control: {

        }
    },

    showAlongList: function (placeId, store) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.along.AlongListView'));
    },

    showAlongInfo: function (along) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.along.AlongFormView'));

        var me = this, formview = me.getAlongFormView();
        if(along){
            console.log('2');
            formview.bindData(along);
            formview.setActiveItem(2);
        }else{
            formview.setActiveItem(1);
            console.log('1');
        }
    }
});
