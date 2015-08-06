Ext.define('YourTour.view.common.PlaceView', {
    extend: 'YourTour.view.widget.XPage',
    xtype: 'placeview',
    requires:['YourTour.view.widget.XToolbar','Ext.Carousel'],
    config: {
    	itemId:'placeview',
    	id:'placeview',
    	fullscreen: true,
    	layout:'vbox',
    	
        items: [
            {    
				xtype: 'xtoolbar',
				title: '目的地',
				items:[]			
			},
			
			{
				xtype:'toolbar',
				id:'placeType',	
				defaults:{
					flex:1
				},
				items: [
		            {
		                text:'国内'
		            },
		            {
		                text:'亚洲'
		            },
		            {
		                text:'欧洲'
		            },
		            {
		                text:'美洲'
		            }
		        ]
			},
			{
            	xtype: 'carousel',
            	itemId:'routeCarousel',
            	indicator:false,
            	flex:1,
            	items:[{
            		html:'国内'
            	},
            	{
            		html:'亚洲'
            	},{
            		html:'欧洲'
            	},{
            		html:'美洲'
            	}]
            }
        ]
    }
});

