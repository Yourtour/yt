Ext.define('YourTour.view.route.schedule.ScheduleListView', {
    extend: 'Ext.Container',
    requires:['YourTour.view.route.schedule.ScheduleListItem','Ext.Panel','YourTour.view.widget.XToolbar','YourTour.view.route.schedule.ScheduleDataView'],
    xtype: 'ScheduleListView',
    config: {
    	itemId:'ScheduleListView',
    	id:'ScheduleListView',
    	fullscreen:true,
    	layout:'vbox',
    	baseCls:'page',
    	
    	items:[
    		{    
				xtype: 'xtoolbar',
				itemId:'toolbar',
				title:'行程规划',
				items:[
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
	                	text:'分享',
	                	itemId:'consult',
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
    			itemId:'scheduleList',
    			xtype:'ScheduleDataView',
    			cls:'space-top',
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

