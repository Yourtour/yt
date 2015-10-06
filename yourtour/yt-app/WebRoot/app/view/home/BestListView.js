Ext.define('YourTour.view.home.BestListView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.home.BestListItemView', 'Ext.DataView','YourTour.view.widget.XHeaderBar'],
    xtype: 'BestListView',
    config: {
    	id:'BestListView',
    	layout:'fit',
        items: [
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'推荐游'
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

