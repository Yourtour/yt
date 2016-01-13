Ext.define('YourTour.view.route.RouteScheduleListView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel', 'YourTour.view.route.RouteScheduleListDataItem', 'YourTour.view.widget.XHeaderBar'],
    xtype: 'RouteScheduleListView',
    config: {
    	id:'RouteScheduleListView',
    	layout:'vbox',
		scrollable: {
    	    direction: 'vertical',
    	    indicators: false	,
    	},    	
    	items:[
    		{    
				xtype: 'xheaderbar',
				title:'行程安排',
				items:[
					{
	                	xtype: "toolbutton", 
	                    ui: "normal", 
	                	text:'编辑',
	                	itemId:'edit',
	                	align:'right'
	                }
				]
			},
			
			{
	    		xtype:'panel',
	    		layout:'vbox',
	    		items:[
	    			{
		    	   		itemId : 'imageUrl',
			    		xtype : 'image',
			    		mode : 'tag'
			    	},
			    	
			    	{
		    	   		itemId : 'name',
			    		xtype : 'label',
			    		style:'background:grey;opacity:0.5; color:#fff; font-size:14px; font-weight:bold; width:100%; height:40px; line-height:40px; text-align:center',
			    		docked:'bottom',
			    		bottom:1
			    	}
		    	]
			},
	    	
    		{
    			itemId:'RouteScheduleList',
    			xtype:'dataview',
    			scrollable:null,
		        useComponents: true,
		        defaultType: 'RouteScheduleListDataItem'
    		}
        ]
    }
});

