Ext.define('YourTour.view.home.AlongListView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.home.AlongListItemView', 'Ext.DataView','YourTour.view.widget.XHeaderBar'],
    xtype: 'AlongListView',
    config: {
    	fullscreen: true,
    	id:'AlongListView',
    	layout:'fit',
        items: [
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'达人'
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

