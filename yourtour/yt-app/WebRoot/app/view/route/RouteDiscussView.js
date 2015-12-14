Ext.define('YourTour.view.route.RouteDiscussView', {
	extend: 'YourTour.view.common.MessageView',
    requires:['YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.ToolButton','YourTour.view.widget.XTextArea'],
    config: {
	    id:'RouteDiscussView',
	    layout:'vbox',
        items: [
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'行程讨论',
				items:[
			       {
			    	   xtype:'toolbutton',
			    	   itemId:'save',
			    	   text:'保存',
			    	   align:'right'
			       }
				]			
			}
        ]
    }
});

