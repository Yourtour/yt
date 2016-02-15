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
				xtype: 'carousel',
				itemId:'images',
				height:150
			},

			{
				xtype:'panel',
				cls:'spacer'
			},

			{
				xtype: 'xmultifield',
				itemId:'memo',
				label:'服务说明'
			},

			{
				xtype: 'xmultifield',
				itemId:'feeIncluding',
				label:'费用包含'
			},

			{
				xtype: 'xmultifield',
				itemId:'feeExcluding',
				label:'费用不含'
			},

			{
				xtype: 'xmultifield',
				itemId:'withdraw',
				label:'退款政策'
			},

			{
                xtype: 'xtoolbar',
                docked: 'bottom',
				defaults:{
					flex:1
				},
                items: [
	                {
	                    xtype: 'button',
	                    text: '点评',
	                    ui: 'normal',
						hidden:true,
	                    itemId: 'btnComment'
	                },{
						xtype: 'button',
						text: '预订',
						ui: 'normal',
						hidden:true,
						itemId: 'btnBook'
					}
					,{
						xtype: 'button',
						text: '提醒',
						ui: 'normal',
						hidden:true,
						itemId: 'btnAlert'
					},{
						xtype: 'button',
						text: '取消',
						ui: 'normal',
						hidden:true,
						itemId: 'btnCancel'
					}
	            ]
            }
    	]
    }
});

