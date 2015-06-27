Ext.define('YourTour.view.line.ListItem', {
    extend: 'Ext.dataview.component.DataItem',
    xtype: 'lineListItem',
    requires:['YourTour.view.line.ListItemView'],
    config: {
    	dataMap: {
    		getItemView:{
                setImageUrl: 'imageUrl',
                setName:'name'
    		}
        },
        
        itemView:{
        	xtype : 'lineListItemView'
        }
    },
    
    /**
     * Called when you set the {@link #image} configuration.
     *
     * Uses Ext.factory to return a proper instance using the configuration passed, the
     * default component, and the existing instance (if it exists).
     *
     * This should *never* be called manually. It will be called when you call {@link #setImage}.
     */
    applyItemView: function(config) {
        return Ext.factory(config, 'YourTour.view.line.ListItemView', this.getItemView());
    },

    /**
     * Called when you set the {@link #image} configuration, and is passed both the new value
     * (from applyImage) and the old value.
     *
     * This should *never* be called manually. It will be called when you call {@link #setImage}.
     * @private
     */
    updateItemView: function(newListItemView, oldListItemView) {
        if (newListItemView) {
            this.add(newListItemView);
        }

        if (oldListItemView) {
            this.remove(oldListItemView);
        }
    }
});

