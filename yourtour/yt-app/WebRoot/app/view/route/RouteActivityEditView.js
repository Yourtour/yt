Ext.define('YourTour.view.route.RouteActivityEditView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','YourTour.view.widget.XLabel','YourTour.view.route.RouteActivityItemDataItem','YourTour.view.route.RouteActivityItemDataItem','YourTour.view.widget.XTimeField','YourTour.view.widget.XField','YourTour.view.widget.XHeaderBar'],
    config: {
    	id:'RouteActivityEditView',
    	layout:'card',
    	items:[
    		{
				xtype: 'xheaderbar',
				title:'行程安排',
				items:[{
                	xtype: "xbutton",
                    ui: "normal",
					icon:'resources/icons/icon_header_ok.png',
                	itemId:'btnSave',
                	align:'right'
                }]
			},

			{
				xtype: 'xprocessing'
			},

			{
				xtype: 'xpagebody',
				layout: 'vbox',
				items: [
					{
						xtype: 'xfield',
						itemId: 'resName',
						tappable: true,
						binding:'resource.name',
						icon: 'icon-name'
					},

					{
						xtype: 'xspacer'
					},

					{
						xtype: 'panel',
						layout: 'hbox',
						cls: 'row underline icon-name',
						padding: '0 0 0 10',
						items: [
							{
								xtype: 'xtextfield',
								itemId: 'title',
								inputCls: 'font-medium font-grey',
								flex: 1,
								placeHolder: '输入活动名称',
								margin: '0 5 0 30'
							}
						]
					},

					{
						xtype: 'panel',
						layout: 'hbox',
						cls: 'row underline icon-time',
						padding: '0 0 0 10',
						items: [
							{
								xtype: 'timepickerfield',
								itemId: 'startTime',
								value: new Date(),
								width: 100,
								margin: '0 5 0 30'
							},

							{
								xtype: 'xlabel',
								html: '至',
								width: 40,
								style: 'font-weight:bold',
								margin: '0 5 0 5'
							},

							{
								xtype: 'timepickerfield',
								itemId: 'endTime',
								width: 100,
								value: new Date()
							}
						]
					},

					{
						xtype: 'panel',
						layout: 'hbox',
						cls: 'underline icon-memo',
						padding: '0 0 0 10',
						items: [
							{
								xtype: 'xtextarea',
								itemId: 'memo',
								height: 195,
								clearIcon: true,
								flex: 1,
								cls: 'font-medium font-grey multilineinfo',
								margin: '0 5 0 30'
							}
						]
					},

					{
						xtype: 'xspacer'
					},

					{
						xtype: 'panel',
						layout: 'vbox',
						items: [
							{
								xtype: 'xlabel',
								html: '安排',
								itemId: 'btnItemAdd',
								tappable: true,
								cls: 'row underline font-medium icon-add',
								padding: '0 10 0 10'
							},
							{
								xtype: 'dataview',
								itemId: 'itemList',
								scrollable: null,
								useComponents: true,
								defaultType: 'RouteActivityItemDataItem',
								binding:'itemsStore'
							}
						]
					},

					{
						xtype: 'xspacer'
					},

					{
						xtype: 'panel',
						layout: 'vbox',
						items: [
							{
								xtype: 'xlabel',
								itemId: 'btnServiceAdd',
								html: '服务',
								tappable: true,
								cls: 'row underline font-medium icon-add',
								padding: '0 10 0 10'
							},
							{
								xtype: 'xdataview',
								itemId: 'serviceList',
								scrollable: null,
								useComponents: true,
								defaultType: 'ExpertServiceListDataItem',
								binding:'servicesStore'
							}
						]
					}
				]
			}
        ]
    },
    
    updateResource:function(resource){
    	this.resource = resource;
    }
});

