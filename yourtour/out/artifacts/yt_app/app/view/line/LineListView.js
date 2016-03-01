Ext.define('YourTour.view.line.LineListView', {
		extend: 'YourTour.view.widget.XPage',
    xtype: 'LineListView',
    requires:['YourTour.view.widget.XHeaderBar','YourTour.view.line.LineListDataItem'],
    config: {
	    id:'LineListView',
	    layout:'card',
        items: [
			{    
				xtype: 'xheaderbar',
			},

			{
				xtype: 'xdataview',
				itemId: 'lineList',
				defaultType: 'LineListDataItem'
			}
        ]
    }
});

