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
						xtype: 'xmultifield',
						itemId: 'useDate',
						label: '使用日期'
					},

					{
						xtype: 'xmultifield',
						itemId: 'content',
						label: '服务人数'
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
							},
							{
								xtype: 'xbutton',
								text: '点评',
								itemId: 'btnComment',
								icon:'resources/icons/24/icon_comment.png'
							}, {
								xtype:'spacer', flex:1
							},{
								xtype: 'xbutton',
								text: '提醒',
								itemId: 'btnAlert',
								icon:'resources/icons/24/icon_alert.png'
							}, {
								xtype:'spacer', flex:1
							},{
								xtype: 'xbutton',
								text: '取消',
								itemId: 'btnCancel',
								icon:'resources/icons/24/icon_cancel.png'
							},{
								xtype:'spacer', flex:1
							}
						]
					}
				]
			}
    	]
    }
});

