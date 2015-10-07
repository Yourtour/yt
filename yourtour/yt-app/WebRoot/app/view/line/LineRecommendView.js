Ext.define('YourTour.view.line.LineRecommendView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','Ext.Img', 'Ext.DataView','YourTour.view.widget.XHeaderBar', 'Ext.Spacer','YourTour.view.widget.XBack','YourTour.view.widget.ToolButton','YourTour.view.line.LineRecommendItem'],
    xtype: 'LineRecommendView',
    config: {
    	id:'LineRecommendView',
    	layout:'fit',
        items: [
        	{    
				xtype: 'xheaderbar',
				title:'线路推荐',
				items:[
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

