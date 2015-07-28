Ext.define('YourTour.view.community.Main', {
    extend: 'Ext.Panel',
    xtype: 'communityview',
    requires:['Ext.Img', 'Ext.Panel', 'YourTour.view.widget.XToolbar'],
    config: {
    	fullscreen: true,
    	
    	layout:'vbox',
        items: [
            {
            	xtype: 'xtoolbar',
                docked: 'top',
                title: '社区'
            }
        ]
    }
});

