Ext.define('YourTour.view.expert.ExpertApplyView', {
	extend: 'YourTour.view.widget.XPage',
    xtype: 'ExpertApplyView',
    requires:['YourTour.view.widget.XHeaderBar','YourTour.view.widget.XLabel','YourTour.view.expert.ExpertServiceDataItem'],
    config: {
	    id:'ExpertApplyView',
		scrollable:false,
	    layout:'vbox',
        items: [
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'达人申请',
				items:[
					{
						xtype: "toolbutton",
						ui: "normal",
						text:'提交',
						itemId:'btnSubmit',
						align:'right'
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				cls:'row underline tab',
				defaults:{
					flex:1
				},
				items:[
					{
						xtype:'xlabel',
						html:'资料',
						cls:'tabitem',
						itemId:'btnResource'
					},
					{
						xtype:'xlabel',
						html:'服务',
						cls:'tabitem',
						itemId:'btnService'
					}
				]
			},

			{
				xtype: 'carousel',
				itemId:'expertCarousel',
				indicator:false,
				flex:1,
				items:[
					{
						xtype:'panel',
						itemId:'overviewPanel',
						layout:'vbox',
						items:[
							{
								xtype:'panel',
								layout:'hbox',
								padding:'0 5 0 5',
								cls:'row underline',
								items:[
									{
										xtype:'xlabel',
										html: '真实姓名',
										margin:'0 10 0 0'
									},
									{
										xtype:'xtextfield',
										flex:1,
										itemId: 'realName'
									}
								]
							},

							{
								xtype:'panel',
								layout:'hbox',
								padding:'0 5 0 5',
								cls:'row underline',
								items:[
									{
										xtype:'xlabel',
										html: '证件类型',
										margin:'0 10 0 0'
									},
									{
										xtype:'xtextfield',
										flex:1,
										itemId: 'certType'
									}
								]
							},

							{
								xtype:'panel',
								layout:'hbox',
								padding:'0 5 0 5',
								cls:'row underline',
								items:[
									{
										xtype:'xlabel',
										html: '证件号码',
										margin:'0 10 0 0'
									},
									{
										xtype:'xtextfield',
										flex:1,
										itemId: 'certNo'
									}
								]
							},

							{
								xtype:'panel',
								layout:'hbox',
								padding:'0 5 0 5',
								cls:'row underline',
								items:[
									{
										xtype:'xlabel',
										html: '达人标签',
										margin:'0 10 0 0'
									},
									{
										xtype:'xtextfield',
										flex:1,
										itemId: 'tags'
									}
								]
							}
						]
					},

					{
						xtype: 'panel',
						itemId: 'servicePanel',
						layout: 'vbox',
						items:[
							{
								itemId:'ServiceList',
								xtype:'dataview',
								useComponents: true,
								flex:1,
								scrollable:{
									direction: 'vertical',
									indicators: false
								},
								defaultType: 'ExpertServiceDataItem'
							},

							{
								xtype: 'toolbar',
								docked: 'bottom',
								items: [
									{
										xtype: 'spacer',
										flex:1
									},{
										xtype: 'button',
										text: '添加',
										ui: 'normal',
										iconCls:'add',
										itemId: 'btnAdd'
									},
									{
										xtype: 'spacer',
										flex:1
									}
								]
							}
						]
					}
				]
			}
        ]
    }
});

