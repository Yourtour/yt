Ext.define('YourTour.view.place.PlaceSelectionRecommendItem', {
    extend: 'YourTour.view.common.CarouselItem',
    requires: ['Ext.Panel'],
    xtype:'PlaceSelectionRecommendItem',
    config: {
        layout: 'vbox',
        label: '猜你想去',
        items: [

        ]
    },

    setRecord:function(record){
        this.callParent(arguments);
    }
});

