Ext.define('YourTour.view.Community', {
    extend: 'Ext.Panel',
    xtype: 'communityview',
    requires:['Ext.Img', 'Ext.Panel', 'Ext.TitleBar'],
    config: {
    	fullscreen: true,
    	
    	layout:'vbox',
        items: [
            {
            	xtype: 'titlebar',
                docked: 'top',
                title: '社区'
            }
        ]
    }
});

