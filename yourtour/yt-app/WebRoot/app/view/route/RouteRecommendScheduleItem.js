Ext.define('YourTour.view.route.RouteRecommendScheduleItem', {
    extend: 'YourTour.view.common.CarouselItem',
    requires:['Ext.DataView', 'YourTour.view.route.RouteScheduleListDataItem'],
    xtype:'RouteRecommendScheduleItem',
	config: {
		label:'行程安排',
		layout:'vbox',
		items: [
			{
				itemId:'RouteScheduleList',
				xtype:'dataview',
				scrollable:null,
				useComponents: true,
				defaultType: 'RouteScheduleListDataItem'
			},

			{
				xtype: 'toolbar',
				docked: 'bottom',
				items: [
					{
						xtype: 'spacer',
						flex:1
					}, {
						xtype: 'button',
						text: '开始规划',
						baseCls:'button',
						flex: 1,
						padding: '0 20 0 20',
						itemId: 'btnClone'
					}
				]
			}
		]
    }
});

