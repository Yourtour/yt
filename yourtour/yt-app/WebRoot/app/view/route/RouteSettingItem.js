Ext.define('YourTour.view.route.RouteSettingItem', {
    extend: 'Ext.dataview.component.DataItem',
    requires:['YourTour.view.widget.XLabel', 'YourTour.view.widget.XField'],
    xtype: 'RouteSettingItem',
    config: {
    	layout:'hbox',
        items: [
			{
				itemId:'name',
				xtype:'xfield',
			}      
        ]
    },

	updateRecord: function(record) {
	    var me = this;
	    
	    if(record){
	 	   var nameEl = me.down('#name');
	 	   nameEl.setHtml(record.get('name'));
	 	}
	 }   
});

