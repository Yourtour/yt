Ext.define('YourTour.view.route.RouteImpressionView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.XTextArea'],
    config: {
	    id:'RouteImpressionView',
	    layout:'vbox',
        items: [
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'旅行印象',
				items:[
			       {
			    	   xtype:'xbutton',
			    	   itemId:'save',
			    	   text:'保存',
			    	   align:'right'
			       }
				]			
			},      
            {
               	xtype: 'xtextarea',
               	itemId:'impression',
               	flex:1,
               	height:300,
			    clearIcon: true
            }
        ]
    }
});

