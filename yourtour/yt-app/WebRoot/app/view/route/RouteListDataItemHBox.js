Ext.define('YourTour.view.route.RouteListDataItemHBox', {
	extend: 'YourTour.view.widget.XDataItem',
    xtype: 'RouteListDataItemHBox',
    requires:['Ext.Label','Ext.Panel'],
    config: {
		layout:'hbox',
		cls:'underline',
		padding:'5 10 5 10',
    	items:[
			{
				xtype : 'ximage',
				itemId:'image',
				imageCls:'img-small',
				margin:'0 10 0 0'
			},

			{
				xtype:'xfield',
				itemId:'name',
				underline:false
			}
    	]
    }
});

