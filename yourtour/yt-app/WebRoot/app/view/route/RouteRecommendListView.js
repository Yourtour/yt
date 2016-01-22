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
						xtype: 'panel',
						height: 150,
						items: [
							{
								itemId: 'image',
								xtype: 'image',
								mode: 'tag',
								src: 'resources/icons/expert.jpg',
								width: '100%',
								height: 150
							}
						]
					},

					{
						xtype: 'panel',
						cls: 'spacer'
					},

					{
						itemId: 'routeRecommendList',
						xtype: 'dataview',
						scrollable: null,
						useComponents: true,
						defaultType: 'RouteRecommendDataItem'
					}
				]
			}
        ]
    }
});

