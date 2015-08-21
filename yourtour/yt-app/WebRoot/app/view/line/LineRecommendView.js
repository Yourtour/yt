Ext.define('YourTour.view.line.LineRecommendView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','Ext.Img', 'Ext.DataView','YourTour.view.widget.XToolbar', 'Ext.Spacer','YourTour.view.widget.XBack','YourTour.view.widget.ToolButton','YourTour.view.line.LineRecommendItem'],
    xtype: 'LineRecommendView',
    config: {
    	id:'LineRecommendView',
    	itemId:'LineRecommendView',
    	
    	layout:'vbox',
    	
        items: [
        	{    
				xtype: 'xtoolbar',
				title:'线路推荐',
				itemId:'toolbar',
				items:[
					{
						xtype:'spacer'
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

