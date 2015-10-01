Ext.define('YourTour.view.home.TalentListView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.home.TalentListItemView','Ext.DataView','YourTour.view.widget.XHeaderBar'],
    xtype: 'TalentListView',
    config: {
    	fullscreen: true,
    	id:'TalentListView',
    	layout:'fit',
        items: [
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'达人'
			}, 
			
            {
            	xtype:'dataview',
            	itemId:'talentList',
		        useComponents: true,
		        defaultType: 'TalentListItemView',
		        scrollable: {
	        	    direction: 'vertical',
	        	    indicators: false	
	        	}  	
    		}
        ]
    }
});

