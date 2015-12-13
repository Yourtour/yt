Ext.define('YourTour.view.expert.ExpertApplyView', {
		extend: 'YourTour.view.widget.XPage',
    xtype: 'ExpertApplyView',
    requires:['YourTour.view.widget.XHeaderBar'],
    config: {
	    id:'ExpertApplyView',
	    layout:'card',
        items: [
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'达人申请',
				items:[
			       
				]			
			},      
            
        ]
    }
});

