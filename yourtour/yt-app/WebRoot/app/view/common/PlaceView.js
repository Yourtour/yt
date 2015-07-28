Ext.define('YourTour.view.common.PlaceView', {
    extend: 'Ext.Panel',
    xtype: 'placeview',
    requires:['YourTour.view.widget.XToolbar','Ext.Toolbar','Ext.Carousel'],
    config: {
    	itemId:'placeview',
    	id:'placeview',
    	fullscreen: true,
    	layout:'vbox',
    	
        items: [
            {    
				xtype: 'xtoolbar',
				docked: 'top',
				title: '目的地',
				items:[{
					xtype: "image", 
                	itemId:'close',
                	mode:'tag',
                	margin:'0 0 0 5',
                	src:'resources/icons/icon_back.png',
                	align:'left'
				},
				{
					xtype: "toolbutton", 
                    ui: "normal", 
                	text:'搜索',
                	itemId:'search',
                	id:'search',
                	align:'right'
				}]			
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

