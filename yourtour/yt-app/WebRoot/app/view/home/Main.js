Ext.define('YourTour.view.home.Main', {
    extend: 'Ext.Panel',
    xtype: 'homemain',
    requires:['Ext.Panel', 'Ext.TitleBar', 'Ext.Toolbar'],
    config: {
    	fullscreen: true,
    	id:'homemain',
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

