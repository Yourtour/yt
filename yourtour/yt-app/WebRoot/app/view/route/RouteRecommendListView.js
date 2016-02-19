Ext.define('YourTour.view.route.RouteRecommendListView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','Ext.DataView','YourTour.view.widget.XHeaderBar', 'Ext.Spacer','YourTour.view.route.RouteRecommendDataItem'],
    config: {
    	id:'RouteRecommendListView',
		layout:'card',
        items: [
        	{    
				xtype: 'xheaderbar'
			},
			{
				xtype:'xprocessing'
			},

			{
				xtype: 'xpagebody',
				items: [
					{
						itemId: 'routeRecommendList',
						xtype: 'xdataview',
						flex:1,
						defaultType: 'RouteRecommendDataItem'
					}
				]
			}
        ]
    }
});

