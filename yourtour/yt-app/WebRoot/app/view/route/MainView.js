Ext.define('YourTour.view.route.MainView', {
    extend: 'YourTour.view.widget.XPage',
    xtype: 'routemainview',
    requires:['Ext.Carousel', 'YourTour.view.widget.XTitleBar', 'YourTour.view.widget.ToolButton', 'Ext.field.Hidden'],
    config: {
    	id:'routemainview',
    	layout:'vbox',
        items: [
			{
			    xtype: 'hiddenfield',
			    name: 'user_id',
			    value: 123
			},    
            {
            	xtype: 'xtitlebar',
                title: '行程',
                items:[
                	{
	                	xtype: "toolbutton", 
	                    ui: "normal", 
	                	text:'管理',
	                	itemId:'manage',
	                	align:'left'
	                },
	                
	                {
	                	xtype: "toolbutton", 
	                    ui: "normal", 
	                	text:'新建',
	                	itemId:'new',
	                	align:'right'
	                }
                ]
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

