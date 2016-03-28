Ext.define('YourTour.view.expert.ExpertMainView', {
	extend: 'YourTour.view.widget.XPage',
    xtype: 'ExpertMainView',
    requires:['YourTour.view.widget.XHeaderBar','YourTour.view.expert.ExpertRouteDataItem'],
    config: {
	    id:'ExpertMainView',
	    layout:'vbox',
        items: [
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'服务行程',
				items:[
					{
						xtype: "xbutton",
						text: '申请',
						itemId: 'btnApply',
						align: 'right'
					}
				]
			},

			{
				xtype: "panel",
				itemId: 'promptPanel',
				flex:1,
				html:'点击右上角申请按钮进行申请。',
				hidden:true
			},

			{
				itemId:'routeList',
				xtype:'dataview',
				scrollable:null,
				useComponents: true,
				flex:1,
				defaultType: 'ExpertRouteDataItem',
				hidden:true
			}
        ]
    }
});

