Ext.define('YourTour.view.common.PlaceGridItemView', {
	extend: 'Ext.Panel',
    requires:['Ext.Panel', 'YourTour.view.widget.XField','YourTour.view.widget.XPanel'],
    xtype: 'PlaceGridItemView',
    config: {
      	layout:'fit',
      	model:null,
        items: [
			{
				itemId:'name',
				xtype:'xfield',
					
			}    
        ]
    },
    
    applyModel:function(model){
    	var me = this;
       	if(model){
       		var nameEl = me.down('#name');
       		nameEl.setHtml(model.get('name'));
	 	}
    }
});

