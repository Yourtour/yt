Ext.define('YourTour.view.line.LineRecommendView', {
    extend: 'Ext.Panel',
    requires:['Ext.Panel','Ext.Img', 'Ext.DataView','YourTour.view.widget.TitleBar','YourTour.view.line.LineRecommendItem'],
    xtype: 'LineRecommendView',
    config: {
    	id:'LineRecommendView',
    	itemId:'LineRecommendView',
    	
    	fullscreen: true,
    	layout:'vbox',
    	
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
            	xtype:'dataview',
		    	itemId:'lineList',
				flex:1,
				useComponents:true,
		    	defaultType:'LineRecommendItem'
		    }
        ]
    }
});

