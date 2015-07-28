Ext.define('YourTour.view.home.HomeMain', {
    extend: 'Ext.Panel',
    xtype: 'homemain',
    requires:['Ext.Panel', 'YourTour.view.widget.XTitleBar'],
    config: {
    	fullscreen: true,
    	id:'homemain',
    	layout:'vbox',
        items: [
            {
            	xtype: 'xtitlebar',
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

