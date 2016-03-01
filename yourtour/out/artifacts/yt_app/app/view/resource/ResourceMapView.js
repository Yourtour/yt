Ext.define('YourTour.view.resource.ResourceMapView', {
	extend: 'YourTour.view.widget.XPage',
    xtype: 'ResourceMapView',
    requires:['YourTour.view.widget.XHeaderBar'],
    config: {
	    id:'ResourceMapView',
	    layout:'vbox',
        items: [
			{    
				xtype: 'xheaderbar',
				title:'位置'
			},
			
			{
                xtype:'panel',
                id:'map',
                styleHtmlContent: true,  
                scrollable: false,  
                flex:1
            }
        ]
    }
});

