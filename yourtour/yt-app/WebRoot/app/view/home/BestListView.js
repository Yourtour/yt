Ext.define('YourTour.view.home.BestListView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel', 'YourTour.view.home.BestListItemView', 'YourTour.view.widget.XPanel','YourTour.view.widget.XField', 'Ext.DataView'],
    xtype: 'BestListView',
    config: {
    	fullscreen: true,
    	id:'BestListView',
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
            	itemId:'bestList',
		        useComponents: true,
		        defaultType: 'BestListItemView',
	        	scrollable: {
	        	    direction: 'vertical',
	        	    indicators: false	
	        	}  	
    		}
        ]
    }
});

