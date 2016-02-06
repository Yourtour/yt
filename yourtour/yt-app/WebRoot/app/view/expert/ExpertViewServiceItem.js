Ext.define('YourTour.view.expert.ExpertViewServiceItem', {
    extend: 'YourTour.view.common.CarouselItem',
    xtype:'ExpertViewServiceItem',
    requires:['YourTour.view.expert.ExpertServiceDataItem'],
    config: {
        layout: 'vbox',
        items: [
            {
                xtype: 'xdataview',
                itemId: 'serviceList',
                binding:'services',
                flex:1,
                defaultType: 'ExpertServiceDataItem'
            }
        ]
    }
});

