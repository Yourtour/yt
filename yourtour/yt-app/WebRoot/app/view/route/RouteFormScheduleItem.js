Ext.define('YourTour.view.route.RouteFormScheduleItem', {
    extend: 'Ext.Container',
    requires:['YourTour.view.route.RouteScheduleListDataItem'],
    xtype:'RouteFormScheduleItem',
	config: {
		layout:'vbox',
		items: [
			{
				xtype:'xdataview',
				itemId:'RouteScheduleList',
				flex:1,
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
						padding: '0 20 0 20',
						icon:'resources/icons/icon_copy.png'
					}
				]
			}
		]
    },

	updateData:function(store) {
		var me = this;
		var scheduleList = me.down('#RouteScheduleList');
		scheduleList.setStore(store);
	}
});

