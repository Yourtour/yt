Ext.define('YourTour.view.personal.Main', {
    extend: 'Ext.Panel',
    xtype: 'personalmain',
    requires:['Ext.Img', 'Ext.Panel', 'YourTour.view.widget.XToolbar'],
    config: {
    	fullscreen: true,
    	
    	layout:'vbox',
        items: [
            {
            	xtype: 'xtoolbar',
                docked: 'top',
                title: '我的'
            }
        ]
    }
});

