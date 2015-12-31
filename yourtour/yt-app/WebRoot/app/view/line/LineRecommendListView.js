Ext.define('YourTour.view.line.LineRecommendListView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','Ext.Img', 'Ext.DataView','YourTour.view.widget.XHeaderBar', 'Ext.Spacer','YourTour.view.widget.XBack','YourTour.view.widget.ToolButton','YourTour.view.line.LineRecommendDataItem'],
    config: {
    	id:'LineRecommendListView',
    	layout:'fit',
        items: [
        	{    
				xtype: 'xheaderbar',
				title:'达人推荐',
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
		    	defaultType:'LineRecommendDataItem',
		    	scrollable: {
		    	    direction: 'vertical',
		    	    indicators: false	
		    	}
		    }
        ]
    }
});

