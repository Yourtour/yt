Ext.define('YourTour.view.route.Main', {
    extend: 'Ext.Panel',
    xtype: 'routemain',
    requires:['Ext.Carousel', 'YourTour.view.widget.TitleBar', 'YourTour.view.widget.ToolButton', 'Ext.field.Hidden'],
    
    config: {
    	itemId:'routemain',
    	fullscreen: true,
    	layout:'vbox',
    	
        items: [
			{
			    xtype: 'hiddenfield',
			    name: 'user_id',
			    value: 123
			},    
            {
            	xtype: '_titlebar',
                docked: 'top',
                title: '行程',
                items:[{
                	xtype: "toolbutton", 
                    ui: "normal", 
                	text:'管理',
                	align:'left'
                },
                {
                	xtype: "toolbutton", 
                    ui: "normal", 
                	text:'新建',
                	itemId:'btnRouteNew',
                	align:'right'
                }]
            },
            {
            	xtype: 'carousel',
            	itemId:'routeCarousel',
            	style:'height:100%;weight:100%',
            }
        ]
    }
});

