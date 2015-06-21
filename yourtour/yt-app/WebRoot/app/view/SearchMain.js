Ext.define('YourTour.view.SearchMain', {
    extend: 'Ext.Panel',
    xtype: 'searchmain',
    requires:['Ext.Panel', 'Ext.TitleBar'],
    config: {
    	fullscreen: true,
    	
    	layout:'vbox',
        items: [
            {
            	xtype: 'titlebar',
                docked: 'top',
                title: '搜索'
            }
        ]
    }
});

