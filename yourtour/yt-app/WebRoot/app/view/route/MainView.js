Ext.define('YourTour.view.route.MainView', {
    extend: 'Ext.Panel',
    xtype: 'routemainview',
    requires:['Ext.Carousel', 'YourTour.view.widget.TitleBar', 'YourTour.view.widget.ToolButton', 'Ext.field.Hidden'],
    
    config: {
    	itemId:'routemainview',
    	id:'routemainview',
    	fullscreen: true,
    	layout:'vbox',
    	baseCls:'page',
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
                	itemId:'new',
                	align:'right'
                }]
            },
            {
            	xtype: 'carousel',
            	itemId:'routeCarousel',
            	indicator:false,
            	direction:'verticle',
            	style:'height:100%;weight:100%'
            }
        ]
    }
});

