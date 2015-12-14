Ext.define('YourTour.view.expert.ExpertIntroView', {
		extend: 'YourTour.view.widget.XPage',
    xtype: 'ExpertIntroView',
    requires:['YourTour.view.widget.XHeaderBar'],
    config: {
	    id:'ExpertIntroView',
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

