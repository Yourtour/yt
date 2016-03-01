Ext.define('YourTour.view.charge.ChargeListView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar','YourTour.view.charge.ChargeListDataItem'],
    config: {
	    id:'ChargeListView',
	    layout:'vbox',
        items: [
			{    
				xtype: 'xheaderbar',
				title:'费用清单'
			},

			{
				itemId: 'chargeList',
				xtype: 'xdataview',
				flex:1,
				defaultType: 'ChargeListDataItem'
			}
        ]
    }
});

