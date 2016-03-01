Ext.define('YourTour.view.expert.ExpertServiceListView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Label','Ext.Panel','YourTour.view.expert.ExpertServiceListDataItem'],
    config: {
    	id:'ExpertServiceListView',
    	items:[
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'达人服务'
			},

			{
				itemId:'ExpertServiceList',
				xtype:'dataview',
				scrollable:null,
				useComponents: true,
				defaultType: 'ExpertServiceListDataItem'
			}
    	]
    }
});

