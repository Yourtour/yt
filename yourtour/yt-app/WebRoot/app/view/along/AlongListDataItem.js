Ext.define('YourTour.view.along.AlongListDataItem', {
	extend: 'YourTour.view.widget.XDataItem',
    xtype: 'AlongListDataItem',
    requires:['Ext.Label','Ext.Panel'],
    config: {
		layout:'hbox',
		cls:'underline',
		padding:'5 10 5 10',
    	items:[
			{
				xtype : 'ximage',
				itemId:'routeImage',
				imageCls:'img-100-75',
				binding:'route.imageUrl',
				margin:'0 10 0 0'
			},

			{
				xtype:'panel',
				layout:'vbox',
				items:[
					{
						xtype:'xfield',
						itemId:'title',
						padding:'0',
						underline:false
					},

					{
						xtype:'xfield',
						itemId:'route',
						padding:'0',
						binding:'route.name',
						underline:false
					}
				]
			}
    	]
    }
});

