Ext.define('YourTour.view.expert.ExpertView', {
    extend: 'YourTour.view.widget.XPage',
    requires: ['YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.XLabel'],
    config: {
        id: 'ExpertView',
        layout: 'card',
        items: [
            {
                xtype: 'xheaderbar',
                title:'达人'
            },

            {
                xtype: 'xprocessing'
            },

            {
                xtype: 'xpagebody',
                layout: 'card',
                items: [
                    {
                        xtype: 'carousel',
                        itemId: 'expertCarousel',
                        flex:1,
                        animation: {
                            duration: 250,
                            easing: {
                                type: 'fade'
                            }
                        }
                    }
                ]
            }
        ]
    }
});

