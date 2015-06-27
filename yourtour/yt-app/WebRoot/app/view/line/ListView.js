Ext.define('YourTour.view.line.ListView', {
    extend: 'Ext.dataview.DataView',
    requires:['YourTour.view.line.ListItem'],
    xtype: 'lineListView',
    config: {
    	id:'lineListView',
    	/**
         * Tell the dataview to use components for each item
         */
        useComponents: true,
        
        /**
         * Set the default item for this component list to be the {@link Example.view.KittensListItem}
         * class.
         */
        defaultType: 'lineListItem'
    },
    
    initialize : function() {
    	this.callParent(arguments);
    }
});

