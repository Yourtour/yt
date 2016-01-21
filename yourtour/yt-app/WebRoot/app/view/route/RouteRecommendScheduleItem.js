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
				flex:1,
				scrollable: {
					direction: 'vertical',
					indicators: false,
					directionLock: true,
					momentumEasing:  {
						/*momentum: {
						 acceleration: 10,
						 friction: 0.9
						 },*/
						bounce: {
							acceleration: 0.0001,
							springTension: 0.9999
						}
						/*minVelocity: 5*/
					},
					outOfBoundRestrictFactor: 0
				},
				useComponents: true,
				defaultType: 'RouteScheduleListDataItem'
			},

			{
				xtype: 'xtoolbar',
				docked: 'bottom',
				items: [
					{
						xtype: 'spacer',
						flex:1
					}, {
						xtype: 'xbutton',
						itemId: 'btnClone',
						text: '行程复制',
						baseCls:'button',
						padding: '0 20 0 20',
						icon:'resources/icons/icon_copy.png'
					}
				]
			}
		]
    },

	initializeItem:function() {
		var me = this;

		var store = me.getData();
		var scheduleList = me.down('#RouteScheduleList');
		scheduleList.setStore(store);
	}
});

