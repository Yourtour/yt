Ext.define('YourTour.view.route.RouteScheduleReferenceView', {
    extend: 'Ext.Container',
    requires:['Ext.Panel','Ext.Toolbar'],
    xtype: 'RouteScheduleReferenceView',
    config: {
    	id:'RouteScheduleReferenceView',
    	fullscreen:true,
    	layout:'vbox',
    	baseCls:'page',
    	
    	items:[
    		{    
				xtype: 'toolbar',
				docked: 'top',
				items:[
					{
						xtype: "image", 
	                	itemId:'close',
	                	mode:'tag',
	                	margin:'0 0 0 5',
	                	src:'resources/icons/icon_back.png',
	                	align:'left'
					},
					{
						xtype: "label", 
						html:'行程参考'
					},
					{
						xtype: "spacer" 
					},
					{
	                	xtype: "toolbutton", 
	                    ui: "normal", 
	                	text:'复制',
	                	itemId:'copy',
	                	align:'right'
	                },
					{
	                	xtype: "toolbutton", 
	                    ui: "normal", 
	                	text:'咨询',
	                	itemId:'consult',
	                	align:'right'
	                },
	                {
	                	xtype: "toolbutton", 
	                    ui: "normal", 
	                	text:'收藏',
	                	itemId:'favorite',
	                	align:'right'
	                },
	                {
	                	xtype: "toolbutton", 
	                    ui: "normal", 
	                	text:'分享',
	                	itemId:'share',
	                	align:'right'
	                }
                ]
			},
			
    		{
    	   		itemId : 'imageUrl',
	    		xtype : 'image',
	    		mode : 'tag'
	    	},
	    	
	    	{
    			itemId:'routeActivitiesList',
    			xtype:'ActivityDataView',
    			readonly:false,
    			scrollable:null,
    			
		    	/**
		         * Tell the dataview to use components for each item
		         */
		        useComponents: true,
		        
		        /**
		         * Set the default item for this component list to be the {@link Example.view.KittensListItem}
		         * class.
		         */
		        defaultType: 'ActivityDataListItem'
    		}
        ]
    }
});

