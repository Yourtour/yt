Ext.define('YourTour.view.common.CommentListDataItem', {
	extend: 'YourTour.view.widget.XDataItem',
    requires:['Ext.Panel', 'YourTour.view.widget.XField','YourTour.view.widget.XVerticalLine','YourTour.view.widget.XPanel','YourTour.view.widget.XScore'],
    xtype: 'CommentListDataItem',
    config: {
		layout:'vbox',
		padding:'10',
		cls:'underline',
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
							},

							{
								xtype:'xfield',
								itemId:'createdDate',
								padding:'0',
								underline:false,
								dataChange:function(field, record){
									field.setText('<span class="tab-space-right">发表于</span>' + record.get('createdDate'));
								}
							}
						]
					},

					{
						xtype:'xscore',
						itemId:'score',
						align:'left',
						shape:'round'
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
						xtype:'spacer',
						flex:1
					},
					{
						xtype:'xbutton',
						itemId:'report',
						text:'举报',
						icon:'resources/icons/16/icon_report.png'
					},

					{
						xtype:'xbutton',
						itemId:'thumbup',
						icon:'resources/icons/16/icon_thumbup.png'
					}
    			]
    		}
        ]
    }
});

