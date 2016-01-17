Ext.define('YourTour.view.expert.ExpertRecommendIntroView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','Ext.Img','Ext.DataView','YourTour.view.widget.XMultiField','YourTour.view.route.RouteScheduleListDataItem', 'YourTour.view.widget.XPanel', 'YourTour.view.widget.XField','YourTour.view.widget.XLabel','YourTour.view.widget.XHeaderBar'	],
    config: {
    	id:'ExpertRecommendIntroView',
      	layout:'vbox',
		scrollable:{
		 direction: 'vertical',
		 indicators: false
		 },

        items: [
        	{    
				xtype: 'xheaderbar',
				title:'达人介绍'
			},

			{
				xtype: 'panel',
				layout: 'hbox',
				cls: 'underline',
				padding: '10 10 10 10',
				items: [
					{
						xtype: 'image',
						mode: 'tag',
						itemId: 'image'

					},

					{
						xtype: 'panel',
						layout: 'vbox',
						flex:1,
						margin:'0 0 0 10',
						items: [
							{
								xtype: 'panel',
								layout: 'hbox',
								items: [
									{
										xtype: 'xfield',
										itemId: 'nickName',
										flex:1
									},

									{
										xtype: 'image',
										mode: 'tag',
										itemId: 'rank',
										src: 'resources/images/raty_32.png',
									},
								]
							},

							{
								xtype: 'xfield',
								itemId:'age',
								margin:'5 0 0 0'
							},

							{
								xtype: 'xfield',
								itemId:'identity',
								margin:'5 0 0 0'
							},
						]
					},

					{
						xtype: 'panel',
						layout: 'vbox',
						margin:'10 0 0 0',
					}
				]
			},

			{
				xtype: 'panel',
				layout: 'hbox',
				padding: '0 10 0 10',
				defaults: {
					flex: 1
				},
				cls: 'row underline',
				items: [
					{
						xtype: 'xlabel',
						itemId: 'idAuthenticate',
						padding:'0 0 0 40',
						html: '身份认证'
					},

					{
						xtype: 'xlabel',
						itemId: 'mobileAuthenticate',
						padding:'0 0 0 40',
						html: '手机认证'
					},

					{
						xtype: 'xlabel',
						itemId: 'snsAuthenticate',
						padding:'0 0 0 40',
						html: '社交认证'
					},
				]
			},

			{
				xtype: 'xmultifield',
				padding: '0 10 0 10',
				itemId: 'memo'
			},

			{
				xtype: 'panel',
				cls: 'spacer'
			},

			{
				xtype: 'panel',
				layout: 'hbox',
				padding: '0 10 0 10',
				cls: 'row underline nav-narrow',
				items: [
					{
						xtype: 'xlabel',
						html: '推荐线路'
					}
				]
			},

			{
				xtype: 'panel',
				itemId:'routes',
				layout: 'vbox',
				padding: '0 10 0 10'
			}
        ]
    }
});

