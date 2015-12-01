Ext.define('YourTour.view.route.RouteMainView', {
		extend: 'YourTour.view.widget.XPage',
    xtype: 'RouteMainView',
    requires:['Ext.Carousel', 'YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.ToolButton', 'Ext.field.Hidden', 'Ext.Img'],
    config: {
	    	itemId:'RouteMainView',
	    	id:'RouteMainView',
	    	layout:'card',
        items: [
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'旅行',
				backButton:false,
				items:[
			       {
			    	   xtype:'toolbutton',
			    	   itemId:'new',
			    	   text:'新建',
			    	   align:'right'
			       },
			       {
			    	   xtype:'toolbutton',
			    	   itemId:'delete',
			    	   text:'删除',
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

