Ext.define('YourTour.view.common.PlaceView', {
    extend: 'YourTour.view.widget.XPage',
    xtype: 'placeview',
    requires:['YourTour.view.widget.XToolbar','Ext.Carousel','Ext.NestedList'],
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
		                text:'热门'
		            },
		            {
		                text:'境内'
		            },
		            {
		                text:'境外'
		            }
		        ]
			},
			{
            	xtype: 'carousel',
            	itemId:'routeCarousel',
            	indicator:false,
            	flex:1,
            	items:[
	            	/*{
	            		xtype:'nestedlist',
	            		itemId:'hot',
	            		store: hotStore
	            	},{
	            		xtype:'nestedlist',
	            		itemId:'domestic',
	            		store: domesticStore
	            	},
	            	{
	            		xtype:'nestedlist',
	            		itemId:'oversea',
	            		store: overseaStore
	            	}*/
            	]
            }
        ]
    }
});

