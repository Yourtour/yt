Ext.define('YourTour.view.home.TalentListView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel', 'YourTour.view.home.TalentListItemView', 'YourTour.view.widget.XPanel','YourTour.view.widget.XField', 'Ext.DataView'],
    xtype: 'TalentListView',
    config: {
    	fullscreen: true,
    	id:'TalentListView',
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

