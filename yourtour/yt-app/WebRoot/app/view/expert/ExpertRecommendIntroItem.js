Ext.define('YourTour.view.expert.ExpertRecommendIntroItem', {
    extend: 'YourTour.view.common.CarouselItem',
    requires: ['Ext.Panel'],
    xtype:'ExpertRecommendIntroItem',
    config: {
        layout: 'vbox',
        label: '达人',
        scrollable: {
            direction: 'vertical',
            indicators: false
        },
        items: [
            {
                xtype: 'panel',
                html: '达人'
            }
        ]
    }
});

