Ext.define('YourTour.view.route.schedule.SchedulePlanListView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.route.schedule.SchedulePlanListItem','Ext.Panel','YourTour.view.widget.XToolbar','YourTour.view.route.schedule.ScheduleDataView'],
    xtype: 'SchedulePlanListView',
    config: {
    	itemId:'SchedulePlanListView',
    	id:'SchedulePlanListView',
    	layout:'vbox',
		scrollable: {
    	    direction: 'vertical',
    	    indicators: false	
    	},    	
    	items:[
    		{    
				xtype: 'xtoolbar',
				itemId:'toolbar',
				title:'行程调整',
				items:[
					{
	                	xtype: "toolbutton", 
	                    ui: "normal", 
	                	text:'保存',
	                	itemId:'save',
	                	align:'right'
	                }
				]			
			},
	    	
    		{
    			itemId:'schedulePlanList',
    			xtype:'dataview',
    			scrollable:null,
    			
		    	/**
		         * Tell the dataview to use components for each item
		         */
		        useComponents: true,
		        
		        /**
		         * Set the default item for this component list to be the {@link Example.view.KittensListItem}
		         * class.
		         */
		        defaultType: 'SchedulePlanListItem'
    		}
        ]
    }
});

