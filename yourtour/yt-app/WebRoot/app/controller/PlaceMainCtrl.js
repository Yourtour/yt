Ext.define('YourTour.controller.PlaceMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{
    	   placeMainView:'#PlaceMainView'
       },
       
       control:{
    	   
       },
       
    },
    
    init:function(){
    },
    
    showPage:function(placeId){
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.place.PlaceMainView'));

    	var me = this, mainview = me.getPlaceMainView();
        var image = mainview.down('#image');
        var place = Ext.create('YourTour.model.PlaceModel');
        image.setData(place);
    }
});
