Ext.define('YourTour.view.common.CommentListView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Label','Ext.Panel','YourTour.view.common.CommentListDataItem'],
    config: {
    	id:'CommentListView',
    	items:[
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'点评',
			},

			{
				xtype:'panel',
				layout:'hbox',
				items:[
					{
						xtype:'xscore',
						itemId:'score',
						align:'right',
						padding:'0 10 0 10'
					},

					{
						xtype:'panel',
						layout:'vbox',
						items:[
							{
								xtype:'panel',
								layout:'hbox',
								defaults:{
									flex:1,
									hidden:true,
									padding:'0',
									underline:false
								},
								items:[
									{
										xtype:'xfield',
										itemId:'healthScore',
										label:'卫生'
									},

									{
										xtype:'xfield',
										itemId:'environmentScore',
										label:'卫生'
									},

									{
										xtype:'xfield',
										itemId:'serviceScore',
										label:'服务'
									},

									{
										xtype:'xfield',
										itemId:'facilityScore',
										label:'设施',
									}
								]
							},
							{
								xtype:'xfield',
								itemId:'recommendNum',
								padding:'0',
								underline:false
							}
						]
					}
				]
			},

			{
				itemId:'commentList',
				xtype:'xdataview',
				scrollable:null,
				useComponents: true,
				defaultType: 'CommentListDataItem'
			}
    	]
    }
});

