Ext.define('YourTour.view.common.CommentListDataItem', {
	extend: 'YourTour.view.widget.XDataItem',
    requires:['Ext.Panel', 'YourTour.view.widget.XField','YourTour.view.widget.XVerticalLine','YourTour.view.widget.XPanel','YourTour.view.widget.XScore'],
    xtype: 'CommentListDataItem',
    config: {
		layout:'vbox',
        items: [
			{
				xtype:'panel',
				layout:'hbox',
				items:[
					{
						xtype:'ximage',
						itemId:'userLogo',
						imageCls:'img-user-logo-48',
						binding:'user.imageUrl'
					},

					{
						xtype:'panel',
						layout:'vbox',
						flex:1,
						margin:'0 10 0 10',
						items:[
							{
								xtype:'xfield',
								itemId:'nickName',
								padding:'0',
								binding:'user.nickName',
								underline:false
							}
						]
					},

					{
						xtype:'xscore',
						itemId:'score',
						align:'left',
						padding:'0 10 0 10'
					}
				]
			},

		   	{
				xtype : 'xmultifield',
				itemId : 'memo',
				padding: '10 0 10 0',
				underline:false
    		},

    		{
    			xtype:'panel',
    			layout:'hbox',
    			items:[
					{
						xtype:'xfield',
						itemId:'createdDate',
						padding:'0',
						underline:false,
						flex:1
					},

					{
						xtype:'xbutton',
						itemId:'report',
						iconCls:'icon-report',
						width:80,
						text:'举报'
					},

					{
						xtype:'xbutton',
						itemId:'thumbup',
						width:80,
						iconCls:'icon-thumbup'
					}
    			]
    		}
        ]
    }
});

