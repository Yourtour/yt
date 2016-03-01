Ext.define('YourTour.view.expert.ExpertServiceFormView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Label','Ext.Panel'],
    config: {
    	id:'ExpertServiceFormView',
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
								xtype:'spacer',
								flex:1
							},
							{
								xtype: 'xbutton',
								text: '联系达人',
								icon:'resources/icons/24/icon_message.png',
								itemId: 'btnBook'
							},
							{
								xtype:'spacer',
								flex:1
							},
							{
								xtype: 'xbutton',
								text: '收藏',
								icon:'resources/icons/24/icon_favorite.png',
								itemId: 'btnFavorite'
							},

							{
								xtype: 'xbutton',
								text: '取消',
								hidden:true,
								icon:'resources/icons/24/icon_favorited.png',
								itemId: 'btnCancel'
							},

							{
								xtype:'spacer',
								flex:1
							},
							{
								xtype: 'xbutton',
								text: '预订',
								hidden: true,
								icon:'resources/icons/24/icon_book.png',
								itemId: 'btnBook'
							},
							{
								xtype:'spacer',
								flex:1
							}
						]
					}
				]
			}
    	]
    }
});

