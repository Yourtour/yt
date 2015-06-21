Ext.define('YourTour.view.HomeMain', {
    extend: 'Ext.Panel',
    xtype: 'homemain',
    requires:['Ext.Panel', 'Ext.TitleBar'],
    config: {
    	fullscreen: true,
    	
    	layout:'vbox',
        items: [
            {
            	xtype: 'titlebar',
                docked: 'top',
                title: '中国,上海',
            	items:[{
                	xtype: "button", 
                    ui: "normal", 
                	text:'切换',
                	align:'right'
                }]	
            }
        ]
    }
});

