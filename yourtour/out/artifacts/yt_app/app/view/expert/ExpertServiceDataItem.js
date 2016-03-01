Ext.define('YourTour.view.expert.ExpertServiceDataItem', {
	extend: 'YourTour.view.widget.XDataItem',
    xtype: 'ExpertServiceDataItem',
    config: {
		layout:'vbox',
    	items:[
			{
				xtype:'xspacer'
			},
			{
				xtype:'xfield',
				itemId:'title',
				indicator:'nav-arrow'
			},

			{
				xtype:'xmultifield',
				itemId:'memo',
				underline:false
			}
    	]
    }
});

