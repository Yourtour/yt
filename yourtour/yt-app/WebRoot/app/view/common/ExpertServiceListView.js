Ext.define('YourTour.view.common.ExpertServiceListView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Label','Ext.Panel','YourTour.view.common.ExpertServiceListDataItem'],
    config: {
    	id:'ExpertServiceListView',
    	items:[
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'达人服务',
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

