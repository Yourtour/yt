Ext.define('YourTour.view.place.PlaceMainDataItem', {
	extend: 'Ext.dataview.component.DataItem',
	xtype:'PlaceMainDataItem',
    config: {
		width:'100',
        items: [
			{
				xtype:'xfield',
				itemId:'name',
				underline:false
			}
        ]
    },

	updateRecord:function(record){
		var me = this;
		if(record){
			var name = me.down('#name');
			name.setText(record.get('name'));
		}
	}
});

