Ext.define('YourTour.view.route.schedule.ScheduleListView', {
    extend: 'Ext.Container',
    requires:['YourTour.view.route.schedule.ScheduleListItem','Ext.Panel','YourTour.view.widget.TitleBar','YourTour.view.route.schedule.ScheduleDataView'],
    xtype: 'scheduleListView',
    config: {
    	itemId:'scheduleListView',
    	id:'scheduleListView',
    	fullscreen:true,
    	layout:'vbox',
    	baseCls:'page',
    	
    	items:[
    		{    
				xtype: '_titlebar',
				docked: 'top',
				title: '行程规划',
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
    			xtype:'ScheduleDataView',
    			readonly:false,
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

