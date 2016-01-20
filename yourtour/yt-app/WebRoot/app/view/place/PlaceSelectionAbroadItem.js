Ext.define('YourTour.view.place.PlaceSelectionAbroadItem', {
    extend: 'YourTour.view.common.CarouselItem',
    requires: ['YourTour.view.place.PlaceSelectionDataItem','YourTour.view.widget.XDataView', 'YourTour.view.place.PlaceSelectionTypeDataItem'],

    xtype:'PlaceSelectionAbroadItem',
    config: {
        layout: 'hbox',
        label: '国际·港澳台',
        items: [
            {
                xtype:'xdataview',
                itemId:'placeType',
                flex:1,
                useComponents: true,
                defaultType: 'PlaceSelectionTypeDataItem',
                cls:'x-xmain-color',
                scrollable: {
                    direction: 'vertical',
                    indicators: false,
                    directionLock: true,
                    momentumEasing:  {
                        /*momentum: {
                         acceleration: 10,
                         friction: 0.9
                         },*/
                        bounce: {
                            acceleration: 0.0001,
                            springTension: 0.9999
                        }
                        /*minVelocity: 5*/
                    },
                    outOfBoundRestrictFactor: 0
                }
            },

            {
                xtype:'xdataview',
                itemId:'placeList',
                flex:4,
                defaultType: 'PlaceSelectionDataItem'
            }
        ]
    },

    initializeItem:function(){
        var placeList = this.down('#placeList');

        placeList.setStore(this.getData());
    }
});

