Ext.define('YourTour.view.route.RouteFormView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar','YourTour.view.widget.XNavigation', 'YourTour.view.route.RouteFormScheduleItem', 'YourTour.view.route.RouteFormOverviewItem'],
    config: {
    	id:'RouteFormView',
		layout:'card',
        items: [
        	{    
				xtype: 'xheaderbar',
				title:'行程介绍',
				items:[
					{
						xtype: 'xbuttongroup',
						align: 'right',
						itemId: 'buttonGroup',
						defaults:{
							flex:1
						},
						items: [
							{
								xtype: 'xbutton',
								itemId: 'btnOverview',
								text: '概述'
							},

							{
								xtype: 'xbutton',
								itemId: 'btnSchedule',
								text: '安排'
							},

							{
								xtype: 'xbutton',
								itemId: 'btnExpert',
								text: '达人'
							}
						]
					}
				]
			},

			{
				xtype:'xprocessing'
			},

			{
				xtype: 'xpagebody',
				layout: 'card',
				items: [
					{
						xtype: 'carousel',
						itemId: 'routeFormCarousel',
						indicator: false,
						flex: 1,
						items: [
							{
								xtype: 'RouteFormOverviewItem',
								itemId: 'overviewItem'
							},

							{
								xtype: 'RouteFormScheduleItem',
								itemId: 'scheduleItem'
							}
						]
					}
				]
			}
        ]
    }
});

