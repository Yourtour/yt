Ext.define('YourTour.view.PersonalMain', {
    extend: 'Ext.Panel',
    xtype: 'personalmain',
    requires:['Ext.Img', 'Ext.Panel', 'Ext.TitleBar'],
    config: {
    	fullscreen: true,
    	
    	layout:'vbox',
        items: [
            {
            	xtype: 'titlebar',
                docked: 'top',
                title: '我的'
            }
        ]
    }
});

