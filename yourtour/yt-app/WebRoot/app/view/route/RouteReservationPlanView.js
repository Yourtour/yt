/**
 * 行程预约定制信息提供页面
 */
Ext.define('YourTour.view.route.RouteReservationPlanView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.grid.XGridView', 'YourTour.view.expert.ExpertGridDataItem'],
    config: {
    	id:'RouteReservationPlanView',
    	layout:'card',
    	items:[
    		{
				xtype: 'xheaderbar',
				title:'预约定制',
				items:[{
                	xtype: "xbutton",
                    ui: "normal",
					icon:'resources/icons/24/icon_header_ok.png',
                	itemId:'btnSave',
                	align:'right'
                }]
			},

			{
				xtype: 'xpagebody',
				layout: 'vbox',
				items: [
					{
						xtype: 'xlabel',
						html:'行程信息',
						cls:'underline font-medium icon-tag'
					},

					{
						xtype: 'xspacer'
					},

					{
						xtype: 'xlabel',
						html:'预约信息',
						cls:'underline font-medium icon-tag'
					},

					{
						xtype:'xmultifield',
						itemId:'generalRequirement',
						editable:true,
						label:'总体要求'
					},

					{
						xtype:'xmultifield',
						itemId:'sceneRequirement',
						editable:true,
						label:'景点要求'
					},

					{
						xtype:'xmultifield',
						itemId:'hotelRequirement',
						editable:true,
						label:'住宿要求'
					},

					{
						xtype:'xmultifield',
						itemId:'foodRequirement',
						editable:true,
						label:'餐饮要求'
					},

					{
						xtype:'xmultifield',
						itemId:'trafficRequirement',
						editable:true,
						label:'交通要求'
					},

					{
						xtype:'xmultifield',
						itemId:'trafficRequirement',
						editable:true,
						label:'其他要求'
					},

					{
						xtype: 'xspacer'
					},

					{
						xtype: 'xlabel',
						html:'服务达人',
						cls:'underline font-medium icon-tag'
					},

					{
						xtype: 'xgridview',
						itemId: 'expertList',
						useComponents: true,
						defaultType: 'ExpertGridDataItem',
						scollable:false,
						cols:2
					}
				]
			}
        ]
    }
});

