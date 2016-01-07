Ext.define('YourTour.view.common.ExpertServiceFormView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Label','Ext.Panel'],
    config: {
    	id:'ExpertServiceFormView',
		layout:'vbox',
		scrollable:{
			direction: 'vertical',
		 	indicators: false
		},
    	items:[
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				items:[
				]
			},

			{
				xtype: 'carousel',
				itemId:'images',
				height:150
			},

			{
				xtype:'panel',
				layout:'hbox',
				cls:'underline',
				padding:'0 5 0 5',
				margin:'10 0 0 0',
				items:[
					{
						xtype:'label',
						html: '服务说明',
						cls:'row font-medium',
						margin:'0 10 0 0'
					},
					{
						xtype: 'label',
						itemId:'memo',
						flex:1,
						cls:'font-medium font-grey multilineinfo',
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				cls:'underline',
				padding:'0 5 0 5',
				items:[
					{
						xtype:'label',
						html: '费用包含',
						cls:'row font-medium',
						margin:'0 10 0 0'
					},
					{
						xtype: 'label',
						itemId:'feeIncluding',
						flex:1,
						cls:'font-medium font-grey multilineinfo'
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				cls:'underline',
				padding:'0 5 0 5',
				items:[
					{
						xtype:'label',
						html: '费用不含',
						cls:'row font-medium',
						margin:'0 10 0 0'
					},
					{
						xtype: 'label',
						itemId:'feeExcluding',
						flex:1,
						cls:'font-medium font-grey multilineinfo'
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				cls:'underline',
				padding:'0 5 0 5',
				items:[
					{
						xtype:'label',
						html: '退款政策',
						cls:'row font-medium',
						margin:'0 10 0 0'
					},
					{
						xtype: 'label',
						itemId:'withdraw',
						flex:1,
						cls:'font-medium font-grey multilineinfo'
					}
				]
			},

			{
                xtype: 'toolbar',
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
						baseCls:'button',
						itemId: 'btnBook'
					}
					,{
						xtype: 'button',
						text: '取消预订',
						ui: 'normal',
						hidden:true,
						baseCls:'button',
						itemId: 'btnCancel'
					}
	            ]
            }
    	]
    }
});

