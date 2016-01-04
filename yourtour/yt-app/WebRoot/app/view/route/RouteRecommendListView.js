Ext.define('YourTour.view.route.RouteRecommendListView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','Ext.Img', 'Ext.DataView','YourTour.view.widget.XHeaderBar', 'Ext.Spacer','YourTour.view.widget.XBack','YourTour.view.widget.ToolButton','YourTour.view.route.RouteRecommendDataItem'],
    config: {
    	id:'RouteRecommendListView',
    	layout:'fit',
        items: [
        	{    
				xtype: 'xheaderbar',
				title:'达人推荐',
				items:[
					{
	                	xtype: "toolbutton", 
	                    ui: "normal", 
	                	text:'定制',
	                	itemId:'btnCustomize',
	                	align:'right'
	                }
                ]
			},
            
            {
            	xtype:'dataview',
		    	itemId:'recommendRouteList',
				flex:1,
				useComponents:true,
		    	defaultType:'RouteRecommendDataItem',
		    	scrollable: {
		    	    direction: 'vertical',
		    	    indicators: false	
		    	}
		    }
        ]
    }
});

