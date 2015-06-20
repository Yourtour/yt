Ext.define('YourTour.view.RouteMain', {
    extend: 'Ext.Panel',
    xtype: 'routemain',
    requires:['Ext.Carousel', 'Ext.TitleBar'],
    
    config: {
    	fullscreen: true,
    	itemId:'routemain',
    	layout:'vbox',
        items: [
            {
            	xtype: 'titlebar',
                docked: 'top',
                title: '行程'
            },
            {
            	xtype: 'carousel',
            	itemId:'routeCarousel',
            	style:'height:100%;weight:100%',
            }
        ]
    }
});

