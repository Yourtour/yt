Ext.define('YourTour.view.service.RouteServiceFormView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Label','Ext.Panel'],
    config: {
    	id:'RouteServiceFormView',
		layout:'vbox',
    	items:[
			{    
				xtype: 'xheaderbar'
			},

			{
				xtype: 'xpagebody',
				layout: 'vbox',
				items: [
					{
						xtype: 'carousel',
						itemId: 'images',
						height: 150
					},
					{
						xtype: 'panel',
						cls: 'spacer'
					},

					{
						xtype: 'xfield',
						itemId: 'fromDate',
						label: '开始日期'
					},

					{
						xtype: 'xfield',
						itemId: 'endDate',
						label: '结束日期'
					},

					{
						xtype: 'xfield',
						itemId: 'place',
						label: '消费地点'
					},

					{
						xtype: 'xmultifield',
						itemId: 'memberNum',
						label: '消费人数'
					},

					{
						xtype: 'xfield',
						itemId: 'fee',
						label: '消费费用'
					},

					{
						xtype: 'xmultifield',
						itemId: 'bookMemo',
						label: '预订说明'
					},

					{
						xtype: 'panel',
						cls: 'spacer'
					},

					{
						xtype: 'xmultifield',
						itemId: 'content',
						label: '服务内容'
					},

					{
						xtype: 'xmultifield',
						itemId: 'memo',
						label: '服务说明'
					},

					{
						xtype: 'xmultifield',
						itemId: 'feeIncluding',
						label: '费用包含'
					},

					{
						xtype: 'xmultifield',
						itemId: 'feeExcluding',
						label: '费用不含'
					},

					{
						xtype: 'xmultifield',
						itemId: 'withdraw',
						label: '退款政策'
					},

					{
						xtype: 'xtoolbar',
						docked: 'bottom',
						items: [
							{
								xtype:'spacer', flex:1
							},	{
								xtype: 'xbutton',
								text: '完成',
								itemId: 'btnCancel',
								icon:'resources/icons/24/icon_ok.png'
							},{
								xtype:'spacer', flex:1
							},{
								xtype: 'xbutton',
								text: '取消',
								itemId: 'btnCancel',
								icon:'resources/icons/24/icon_cancel.png'
							},{
								xtype:'spacer', flex:1
							},{
								xtype: 'xbutton',
								text: '点评',
								itemId: 'btnComment',
								icon:'resources/icons/24/icon_comment.png'
							}, {
								xtype:'spacer', flex:1
							},{
								xtype: 'xbutton',
								text: '通知达人',
								itemId: 'btnAlert',
								icon:'resources/icons/24/icon_message.png'
							}, {
								xtype:'spacer', flex:1
							}
						]
					}
				]
			}
    	]
    }
});

