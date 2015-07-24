Ext.define('YourTour.view.route.schedule.ScheduleReferenceListView', {
    extend: 'Ext.Container',
    requires:['YourTour.view.route.schedule.ScheduleListItem','YourTour.view.route.schedule.ScheduleDataView','Ext.Panel','Ext.Toolbar'],
    xtype: 'schedulereference',
    config: {
    	itemId:'schedulereference',
    	id:'schedulereference',
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
    			itemId:'scheduleList',
    			xtype:'ScheduleDataView',
    			readonly:true,
    			flex:1,
    			
		    	/**
		         * Tell the dataview to use components for each item
		         */
		        useComponents: true,
		        
		        /**
		         * Set the default item for this component list to be the {@link Example.view.KittensListItem}
		         * class.
		         */
		        defaultType: 'scheduleListItem'
    		}
        ]
    }
});

