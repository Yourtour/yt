Ext.define('YourTour.view.along.AlongListDataItem', {
	extend: 'YourTour.view.widget.XDataItem',
    xtype: 'AlongListDataItem',
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

