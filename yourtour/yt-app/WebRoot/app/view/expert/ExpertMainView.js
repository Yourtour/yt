Ext.define('YourTour.view.expert.ExpertMainView', {
		extend: 'YourTour.view.widget.XPage',
    xtype: 'ExpertMainView',
    requires:['YourTour.view.widget.XHeaderBar'],
    config: {
	    id:'ExpertMainView',
	    layout:'card',
        items: [
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'我是达人',
				items:[
			       
				]			
			},      
            
        ]
    }
});

