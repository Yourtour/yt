Ext.define('YourTour.view.expert.ExpertViewParticipateDataItem', {
	extend: 'YourTour.view.widget.XDataItem',
    xtype: 'ExpertViewParticipateDataItem',
    requires:['Ext.Panel', 'Ext.Img', 'YourTour.view.widget.XField','YourTour.view.widget.XLabel'],
    config: {
		layout:'vbox',
		height:200,
		items:[
			{
				xtype:'xspacer'
			},
			{
				xtype: 'xfield',
				itemId: 'lineName',
				indicator:'nav-arrow'
			},

			{
				xtype: 'ximage',
				itemId: 'image',
				height:150,
				width:'100%',
				mode:'background'
			}
		]
    }
});

