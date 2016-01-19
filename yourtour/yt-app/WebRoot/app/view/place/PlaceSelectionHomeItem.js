Ext.define('YourTour.view.place.PlaceSelectionHomeItem', {
    extend: 'YourTour.view.common.CarouselItem',
    requires: ['YourTour.view.place.PlaceSelectionDataItem'],
    xtype:'PlaceSelectionHomeItem',
    config: {
        layout: 'vbox',
        label: '国内',
        items: [
            {
                xtype:'dataview',
                itemId:'placeList',
                flex:1,
                useComponents: true,
                defaultType: 'PlaceSelectionDataItem'
            }
        ]
    },

    initializeItem:function(){
        var placeList = this.down('#placeList');

        var store = this.getData();
        store.each(function(place){
            if(place.get('leaf') == 'true'){
                place.set('hidden', true);
            }else{
                place.set('hidden', false);
            }
        });

        placeList.setStore(this.getData());
    }
});

