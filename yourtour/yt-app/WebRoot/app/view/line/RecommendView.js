Ext.define('YourTour.view.line.RecommendView', {
    extend: 'Ext.Panel',
    requires:['Ext.Toolbar', 'Ext.Panel','Ext.Carousel','YourTour.view.widget.TitleBar','YourTour.view.line.RecommedListItem'],
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
                	text:'新建',
                	itemId:'new',
                	align:'right'
                }
                ]
			},
            
            {
            	xtype: 'dataview',
            	itemId:'lines',
			   	flex:1,
			   	scrollable: {
		    	    direction: 'vertical',
		    	    directionLock: true
		    	},
			   	useComponents:true,
		       	defaultType:'lineListItem'
            }
        ]
    }
});

