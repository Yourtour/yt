Ext.define('YourTour.view.place.PlaceExpertView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Carousel', 'Ext.Panel','Ext.Img','Ext.DataView', 'YourTour.view.widget.XPanel', 'YourTour.view.widget.XPlainButton','YourTour.view.widget.XField','YourTour.view.widget.XLabel', 'YourTour.view.widget.XHeaderBar','YourTour.view.line.LineResourceItem'],
    xtype: 'PlaceExpertView',
    config: {
      	layout:'fit',
        items: [
			{
				xtype:'label',
				html:'expert'
			}
        ]
    }
});

