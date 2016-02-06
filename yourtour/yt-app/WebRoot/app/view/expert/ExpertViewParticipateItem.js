Ext.define('YourTour.view.expert.ExpertViewParticipateItem', {
    extend: 'YourTour.view.common.CarouselItem',
    xtype:'ExpertViewParticipateItem',
    requires:['YourTour.view.expert.ExpertViewParticipateDataItem'],
    config: {
        layout: 'vbox',
        items: [
            {
                xtype: 'xdataview',
                itemId: 'participateList',
                flex:1,
                defaultType: 'ExpertViewParticipateDataItem'
            }
        ]
    }
});

