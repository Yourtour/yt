Ext.define('YourTour.view.route.schedule.ScheduleReferenceListView', {
    extend: 'Ext.Container',
    requires:['YourTour.view.route.schedule.ScheduleListItem','Ext.Panel','YourTour.view.widget.TitleBar'],
    xtype: 'schedulereference',
    config: {
    	itemId:'schedulereference',
    	id:'schedulereference',
    	fullscreen:true,
    	layout:'vbox',
    	baseCls:'page',
    	
    	items:[
    		{    
				xtype: '_titlebar',
				docked: 'top',
				title: '行程参考',
				items:[
					{
						xtype: "image", 
	                	itemId:'close',
	                	mode:'tag',
	                	margin:'0 0 0 5',
	                	src:'resources/icons/icon_back.png',
	                	align:'left'
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
    			xtype:'dataview',
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

