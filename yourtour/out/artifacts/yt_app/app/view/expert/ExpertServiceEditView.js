Ext.define('YourTour.view.expert.ExpertServiceEditView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar','YourTour.view.widget.XTextField','YourTour.view.widget.XTextArea'],
    config: {
	    id:'ExpertServiceEditView',
	    layout:'vbox',
        items: [
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'服务',
				items:[
					{
						xtype: "toolbutton",
						ui: "normal",
						text: '保存',
						itemId: 'btnSave',
						align: 'right'
					}
				]
			},

			{
				xtype:'hiddenfield',
				itemId:'id',
				value:'0'
			},

			{
				xtype:'panel',
				layout:'hbox',
				cls:'row underline',
				items:[
					{
						xtype:'label',
						html: '服务名称',
						cls:'font-medium font-grey',
						margin:'0 10 0 5'
					},
					{
						xtype: 'xtextfield',
						itemId:'title',
						flex:1,
						cls:'font-medium font-grey',
						margin:'0 5 0 10'
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				cls:'row underline',
				items:[
					{
						xtype:'label',
						html: '服务费用',
						cls:'font-medium font-grey',
						margin:'0 10 0 5'
					},
					{
						xtype: 'xtextfield',
						itemId:'title',
						flex:1,
						cls:'font-medium font-grey',
						margin:'0 5 0 10'
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				cls:'row underline',
				items:[
					{
						xtype:'label',
						html: '费用包含',
						cls:'font-medium font-grey',
						margin:'0 10 0 5'
					},
					{
						xtype: 'xtextfield',
						itemId:'title',
						flex:1,
						cls:'font-medium font-grey',
						margin:'0 5 0 10'
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				cls:'row underline',
				items:[
					{
						xtype:'label',
						html: '费用不包含',
						cls:'font-medium font-grey',
						margin:'0 10 0 5'
					},
					{
						xtype: 'xtextfield',
						itemId:'title',
						flex:1,
						cls:'font-medium font-grey',
						margin:'0 5 0 10'
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				cls:'row underline',
				items:[
					{
						xtype:'label',
						html: '退款政策',
						cls:'font-medium font-grey',
						margin:'0 10 0 5'
					},
					{
						xtype: 'xtextfield',
						itemId:'title',
						flex:1,
						cls:'font-medium font-grey',
						margin:'0 5 0 10'
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				items:[
					{
						xtype:'label',
						html: '服务描述',
						cls:'row font-medium font-grey',
						margin:'0 10 0 5'
					},
					{
						xtype: 'xtextarea',
						itemId:'memo',
						flex:1,
						maxRows:20,
						cls:'font-medium font-grey multilineinfo',
						margin:'5 5 0 10'
					}
				]
			},

			{
				xtype: 'toolbar',
				itemId:'toolbar',
				docked: 'bottom',
				hidden:true,
				items: [
					{
						xtype: 'spacer',
						flex:1
					},{
						xtype: 'button',
						text: '删除',
						ui: 'normal',
						iconCls:'delete',
						itemId: 'btnDelete'
					},
					{
						xtype: 'spacer',
						flex:1
					}
				]
			}
        ]
    }
});

