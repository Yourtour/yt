Ext.define('YourTour.view.line.RecommendView', {
    extend: 'Ext.Panel',
    requires:['Ext.Toolbar', 'Ext.Panel','Ext.Carousel','YourTour.view.widget.TitleBar'],
    xtype: 'linerecommendview',
    config: {
    	id:'linerecommendview',
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
				},			
				{
                	xtype: "toolbutton", 
                    ui: "normal", 
                	text:'详情',
                	itemId:'intro',
                	align:'right'
                }
                ]
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

