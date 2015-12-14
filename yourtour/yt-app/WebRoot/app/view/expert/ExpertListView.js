Ext.define('YourTour.view.expert.ExpertListView', {
		extend: 'YourTour.view.widget.XPage',
    xtype: 'ExpertListView',
    requires:['YourTour.view.widget.XHeaderBar'],
    config: {
	    id:'ExpertListView',
	    layout:'card',
        items: [
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'旅行达人',
				items:[
			       
				]			
			},      
            
        ]
    }
});

