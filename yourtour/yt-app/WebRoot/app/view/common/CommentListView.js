Ext.define('YourTour.view.common.CommentListView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Label','Ext.Panel','YourTour.view.common.CommentListDataItem','YourTour.view.widget.XPageBody','YourTour.view.widget.XProcessing'],
    config: {
    	id:'CommentListView',
		layout:'card',
    	items:[
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'点评',
			},

			{
				xtype:'xprocessing'
			},

			{
					xtype:'xpagebody',
					layout:'vbox',
					items:[
					{
						xtype:'panel',
						layout:'hbox',
						height:70,
						padding:'10 0 10 10',
						cls:'underline',
						items:[
							{
								xtype:'xscore',
								itemId:'commentScore',
								align:'right',
								width:50
							},

							{
								xtype:'panel',
								layout:'vbox',
								flex:1,
								margin:'0 0 0 10',
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
												label:'环境'
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
										underline:false,
										label:'推荐'
									}
								]
							}
						]
					},

					{
						xtype:'xspacer'
					},

					{
						xtype:'panel',
						itemId:'filterPanel',
						layout:'hbox',
						padding:'0 10 0 10',
						cls:'underline',
						defaults:{
							cls:'row',
							underline:false,
							tappable:{
								indicator:'none'
							},
							padding:'0',
							align:'center'
						},
						items:[
							{
								xtype:'xfield',
								itemId:'commentNum'
							},

							{
								xtype:'xfield',
								itemId:'goodNum',
								margin:'0 0 0 40',
							},

							{
								xtype:'xfield',
								itemId:'mediumNum',
								margin:'0 0 0 40',
							},

							{
								xtype:'xfield',
								itemId:'badNum',
								margin:'0 0 0 40',
							},

							{
								xtype:'xfield',
								itemId:'imageNum',
								margin:'0 0 0 40',
							},{
								xtype:'spacer',
								flex:1
							}
						]
					},
					{
						itemId:'commentList',
						xtype:'xdataview',
						updatable:false,
						scrollable:null,
						useComponents: true,
						defaultType: 'CommentListDataItem'
					}
				]
			}
    	]
    }
});

