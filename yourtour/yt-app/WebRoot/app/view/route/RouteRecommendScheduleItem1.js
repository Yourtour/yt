Ext.define('YourTour.view.route.RouteRecommendScheduleItem', {
    extend: 'YourTour.view.common.CarouselItem',
    requires:['Ext.Panel','Ext.Img','Ext.DataView','YourTour.view.widget.XCarousel', 'YourTour.view.route.RouteScheduleListDataItem','YourTour.view.widget.XPanel', 'YourTour.view.widget.XButton','YourTour.view.widget.XField','YourTour.view.widget.XLabel','YourTour.view.widget.XHeaderBar', 'YourTour.view.line.LineResourceItem'],
    xtype:'RouteRecommendScheduleItem',
	config: {
		label:'简介',
      	layout:'vbox',
        items: [
        	{    
				xtype: 'xheaderbar',
				title:'行程介绍'
			},

			{
				xtype: 'carousel',
				itemId:'recommendCarousel',
				indicator:false,
				flex:1,
				direction:'verticle',
				items:[
					{
						xtype:'panel',
						layout:'vbox',
						itemId:'overview',
						flex:1,
						scrollable:{
							direction: 'vertical',
							indicators: false
						},
						items:[
							{
								xtype:'panel',
								layout:'vbox',
								height:150,
								items:[
									{
										itemId : 'image',
										xtype : 'image',
										mode : 'tag'
									},

									{
										xtype: 'panel',
										layout: 'hbox',
										cls: 'row underline',
										padding: '0 10 0 10',
										docked: 'bottom',
										bottom: 0,
										style: 'background-color:grey;opacity:0.2; width:100%; text-align:center',
										items: [
											{
												itemId: 'price',
												xtype: 'label',
												style:'color:#FFFFFF;font-size:14px; font-weight:bold;',
												html:'¥ 450元/人/天'
											},

											{
												xtype:'spacer',
												flex:1
											},

											{
												xtype: 'image',
												src: 'resources/images/raty_32.png',
												mode: 'tag'
											}
										]
									}
								]
							},

							{
								xtype:'xlabel',
								itemId:'lineName',
								padding:'0 10 0 10',
								cls:'row underline font-bold font-medium'
							},
							{
								xtype:'toolbar',
								padding:'0 10 0 10',
								cls:'toolbar-row underline font-grey font-medium',
								defaults:{
									flex:1
								},
								items:[
									{
										xtype: 'button',
										text: '100',
										icon: 'resources/icons/icon_eye.png',
										itemId: 'readNum'
									}, {
										xtype: 'button',
										text: '100',
										icon: 'resources/icons/icon_ok.png',
										itemId: 'usedNum'
									}, {
										xtype: 'button',
										text: '100',
										iconCls: 'refresh',
										itemId: 'commentNum'
									}, {
										xtype: 'button',
										text: '100',
										icon: 'resources/icons/icon_favorite.png',
										itemId: 'favoriteNum'
									}
								]
							},

							{
								xtype:'hspacer'
							},

							{
								xtype:'panel',
								tappable:true,
								layout:'hbox',
								padding:'0 10 0 10',
								cls:'underline font-grey font-medium nav-arrow',
								items:[
									{
										itemId : 'expertImage',
										xtype : 'image',
										mode : 'tag',
									}
								]
							},

							{
								xtype:'hspacer'
							},

							{
								xtype:'xlabel',
								itemId:'featureTitle',
								html:'线路特点',
								tappable:true,
								padding:'0 10 0 10',
								cls:'row underline font-bold font-medium nav-arrow'
							},

							{
								xtype:'xmultifield',
								itemId:'feature',
								padding:'0 10 0 10',
							},

							{
								xtype:'hspacer'
							},

							{
								xtype:'xlabel',
								itemId:'reasonTitle',
								html:'推荐理由',
								tappable:true,
								padding:'0 10 0 10',
								cls:'row underline font-bold font-medium nav-arrow'
							},

							{
								xtype:'xmultifield',
								itemId:'reason',
								padding:'0 10 0 10'
							}
						]
					},{
						xtype:'dataview',
						itemId:'scheduleList',
						flex:1,
						useComponents:true,
						defaultType:'RouteScheduleListDataItem',
						scrollable: {
							direction: 'vertical',
							indicators: false
						}
					}
				]
			},

			{
				xtype: 'toolbar',
				docked: 'bottom',
				items: [
					{
						xtype: 'button',
						text: '联系达人',
						padding:'0 20 0 20',
						itemId: 'btnConsult'
					}, {
						xtype: 'button',
						text: '开始规划',
						baseCls:'button',
						flex: 1,
						padding: '0 20 0 20',
						itemId: 'btnClone'
					}
				]
			}
        ]
    }
});

