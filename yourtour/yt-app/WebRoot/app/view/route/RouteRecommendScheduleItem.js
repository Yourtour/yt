Ext.define('YourTour.view.route.RouteRecommendScheduleItem', {
    extend: 'YourTour.view.common.CarouselItem',
    requires:['Ext.Panel','Ext.Img','Ext.DataView','YourTour.view.widget.XCarousel', 'YourTour.view.route.RouteScheduleListDataItem','YourTour.view.widget.XPanel', 'YourTour.view.widget.XButton','YourTour.view.widget.XField','YourTour.view.widget.XLabel','YourTour.view.widget.XHeaderBar', 'YourTour.view.line.LineResourceItem'],
    xtype:'RouteRecommendScheduleItem',
	config: {
		label:'简介',
		layout:'vbox',
		items: [
			{
				xtype: 'panel',
				html: '行程'
			}
		]
    }
});

