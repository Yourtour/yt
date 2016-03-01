Ext.define('YourTour.view.expert.ExpertViewRecommendItem', {
    extend: 'YourTour.view.common.CarouselItem',
    xtype:'ExpertViewRecommendItem',
    requires:['YourTour.view.expert.ExpertViewRecommendDataItem'],
    config: {
        layout: 'vbox',
        items: [
            {
                xtype: 'xdataview',
                itemId: 'recommendList',
                flex:1,
                defaultType: 'ExpertViewRecommendDataItem'
            }
        ]
    }
});

