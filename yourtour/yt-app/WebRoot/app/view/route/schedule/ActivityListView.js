Ext.define('YourTour.view.route.schedule.ActivityListView', {
    extend: 'Ext.dataview.DataView',
    requires:['YourTour.view.route.schedule.ActivityListItem'],
    xtype: 'activityListView',
    config: {
    	id:'activityListView',
    	/**
         * Tell the dataview to use components for each item
         */
        useComponents: true,
        
        /**
         * Set the default item for this component list to be the {@link Example.view.KittensListItem}
         * class.
         */
        defaultType: 'activityListItem'
    },
    
    initialize : function() {
    	this.callParent(arguments);
    }
});

