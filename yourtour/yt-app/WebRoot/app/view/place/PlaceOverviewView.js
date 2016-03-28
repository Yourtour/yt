Ext.define('YourTour.view.place.PlaceOverviewView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Carousel', 'Ext.Panel','Ext.Img','Ext.DataView', 'YourTour.view.widget.XPanel','YourTour.view.widget.XField','YourTour.view.widget.XLabel', 'YourTour.view.widget.XHeaderBar','YourTour.view.line.LineResourceItem'],
    xtype: 'PlaceOverviewView',
    config: {
      	layout:'fit',
        items: [
        	{
        		xtype:'label',
        		html:'overview'
        	}
        ]
    }
});

