Ext.define('YourTour.view.home.AlongListView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel', 'YourTour.view.home.AlongListItemView', 'YourTour.view.widget.XPanel','YourTour.view.widget.XField', 'Ext.DataView'],
    xtype: 'AlongListView',
    config: {
    	fullscreen: true,
    	id:'AlongListView',
    	layout:'fit',
        items: [
			{    
				xtype: 'xtoolbar',
				itemId:'toolbar',
				title:'上海',
				items:[
				]			
			},
            {
            	xtype:'dataview',
            	itemId:'alongList',
		        useComponents: true,
		        defaultType: 'AlongListItemView',
	        	scrollable: {
	        	    direction: 'vertical',
	        	    indicators: false	
	        	}  	
    		}
        ]
    }
});

