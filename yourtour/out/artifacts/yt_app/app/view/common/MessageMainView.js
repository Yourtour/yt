Ext.define('YourTour.view.common.MessageMainView', {
	extend: 'YourTour.view.widget.XPage',
	requires:['Ext.Panel','YourTour.view.common.MessageDataItemView', 'YourTour.view.widget.XTextArea'],
	xtype:'MessageMainView',
    config: {
    	id:'MessageMainView',
    	layout:'card',
    	items:[
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'目的地',
				items:[
			       {
			    	   xtype:'xbutton',
			    	   itemId:'group',
			    	   text:'设置',
			    	   align:'right'
			       }
				]
			},

			{
				xtype:'container',
				layout:'vbox',
				flex:1,
				items:[
					{
						xtype:'xdataview',
						itemId:'MessageList',
						defaultType: 'MessageDataItemView',
						flex:1,
						style:'background-color:#EDEDED'
					},

					{
						xtype: 'xpanel',
						itemId:'messagetool',
						layout:'hbox',
						cls:'row',
						padding:'0 10',
						docked:'bottom',
						items:[
							{
								xtype : 'image',
								mode : 'tag',
								src:'resources/icons/48/icon_voice.png',
								itemId:'voice'
							},

							{
								xtype:'textfield',
								itemId: 'messageText',
								flex:1
							},

							{
								xtype:'textareafield',
								itemId: 'messageTextArea',
								flex:1,
								hidden:true,
								maxRows:4,
								clearIcon:false,
								cls:'underline'
							},

							{
								xtype : 'xlabel',
								html:'Send',
								itemId:'btnSend'
							}
						]
					}
				]
			}
    	]
    },

	initialize:function(){
		this.callParent(arguments);

		console.log(this.inputCls);
		console.log(this.baseCls);
	}
});