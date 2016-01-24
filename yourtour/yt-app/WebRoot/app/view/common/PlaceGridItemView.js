Ext.define('YourTour.view.common.PlaceGridItemView', {
	extend: 'YourTour.view.widget.XGridItem',
    requires:['Ext.Panel', 'YourTour.view.widget.XField','YourTour.view.widget.XPanel'],
    xtype: 'PlaceGridItemView',
    config: {
        items: [
			{
				itemId:'name',
				xtype:'xfield'
			}    
        ]
    },
    
    applyModel:function(model){
    	this.callParent(arguments);
    	
    	var me = this;
       	if(model){
       		var nameEl = me.down('#name');
       		nameEl.setHtml(model.get('name'));
	 	}
    }
});

