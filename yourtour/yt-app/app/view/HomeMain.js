Ext.define('YourTour.view.HomeMain', {
    extend: 'Ext.Panel',
    xtype: 'homemain',
    requires:['Ext.Img', 'Ext.Panel', 'Ext.TitleBar'],
    config: {
    	fullscreen: true,
    	
    	layout:'vbox',
        items: [
            {
            	xtype: 'titlebar',
                docked: 'top',
                title: '目的地'
            }
        ]
    }
});

