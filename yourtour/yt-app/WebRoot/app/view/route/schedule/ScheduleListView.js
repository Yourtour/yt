Ext.define('YourTour.view.route.schedule.ScheduleListView', {
    extend: 'Ext.dataview.DataView',
    requires:['YourTour.view.route.schedule.ScheduleListItem'],
    xtype: 'scheduleListView',
    config: {
    	itemId:'scheduleListView',
    	id:'scheduleListView',
    	/**
         * Tell the dataview to use components for each item
         */
        useComponents: true,
        
        /**
         * Set the default item for this component list to be the {@link Example.view.KittensListItem}
         * class.
         */
        defaultType: 'scheduleListItem'
    },
    
    initialize : function() {
    	this.callParent(arguments);
    }
});

