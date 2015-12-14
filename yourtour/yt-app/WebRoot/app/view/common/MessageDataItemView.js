Ext.define('YourTour.view.common.MessageDataItemView', {
	extend: 'Ext.dataview.component.DataItem',
    requires:['Ext.Panel', 'YourTour.view.widget.XField','YourTour.view.widget.XPanel'],
    xtype: 'MessageDataItemView',
    config: {
      	layout:'hbox',
      	padding:'10 5 10 5',
      	cls:'underline',
        items: [
		   	{
				itemId:'type',
				xtype:'xfield'
			},
        ]
    },
    
    updateRecord: function(record){
    	var me = this;
       	if(record){
	 	   var typeEl = me.down('#type');
	 	   typeEl.setHtml(record.get('name'));
	 	}
    }
});

