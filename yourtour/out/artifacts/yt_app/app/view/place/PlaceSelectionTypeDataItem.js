Ext.define('YourTour.view.place.PlaceSelectionTypeDataItem', {
	extend: 'Ext.dataview.component.DataItem',
    requires:['Ext.Panel', 'YourTour.view.widget.XField','YourTour.view.widget.XPanel'],
    xtype: 'PlaceSelectionTypeDataItem',
    config: {
      	layout:'hbox',
		cls:'underline row',
        items: [
			{
				xtype: 'container',
				itemId: 'item',
				layout: 'hbox',
				items: [
					{
						itemId: 'type',
						xtype: 'xlabel',
						flex:1,
						cls:'x-xmain-color font-medium font-white',
						padding:'0 0 0 10'

					}
				]
			}
        ]
    },
    
    updateRecord: function(record){
    	var me = this;
       	if(record){
	 	   var type = me.down('#type');
			type.setHtml(record.get('name'));
	 	}
    }
});

