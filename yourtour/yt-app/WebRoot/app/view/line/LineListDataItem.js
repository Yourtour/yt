Ext.define('YourTour.view.line.LineListDataItem', {
	extend: 'YourTour.view.widget.XDataItem',
    xtype: 'ExpertDataItem',
    requires:['Ext.Label','Ext.Panel'],
    config: {
		layout:'vbox',
		cls:'underline',
    	items:[
			{
				xtype : 'ximage',
				itemId:'image'
			},

			{
				xtype:'xfield',
				itemId:'name',
				cls:'row underline font-medium font-grey'
			}
    	]
    }
});

