Ext.define('YourTour.view.line.IntroductionView', {
    extend: 'Ext.Panel',
    requires:['Ext.Toolbar', 'Ext.Panel','Ext.Carousel','YourTour.view.widget.TitleBar'],
    xtype: 'lineintroductionview',
    config: {
    	id:'lineintroductionview',
    	fullscreen: true,
    	layout:'vbox',
    	baseCls:'page',
    	
        items: [
        	{    
				xtype: '_titlebar',
				docked: 'top',
				title: '线路推荐',
				items:[{
					xtype: "image", 
                	itemId:'close',
                	mode:'tag',
                	margin:'0 0 0 5',
                	src:'resources/icons/icon_back.png',
                	align:'left'
				}]			
			},
            
            {
            	xtype: 'carousel',
            	itemId:'lineCarousel',
            	showAnimation:'fade',
            	indicator:false,
            	style:'width:100%; height:100%'
            }
        ]
    }
});

