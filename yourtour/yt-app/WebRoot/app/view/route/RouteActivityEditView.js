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
					icon:'resources/icons/24/icon_header_ok.png',
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
						binding:'resource.name',
						label:'景点名称'
					},

					{
						xtype: 'xspacer'
					},

					{
						xtype: 'panel',
						layout: 'hbox',
						cls: 'row underline',
						padding: '0 10 0 10',
						items:[
							{
								xtype:'xlabel',
								html:'行程名称',
								padding:'0 10 0 0'
							},
							{
								xtype: 'xtextfield',
								itemId: 'title',
								flex:1,
								inputCls: 'font-medium font-grey',
								placeHolder: '输入活动名称'
							}
						]
					},

					{
						xtype: 'panel',
						layout: 'hbox',
						cls: 'row underline',
						padding: '0 0 0 10',
						items: [
							{
								xtype:'xlabel',
								html:'行程时间',
								padding:'0 10 0 0'
							},
							{
								xtype: 'timepickerfield',
								itemId: 'startTime',
								value: new Date(),
								width: 70,
								margin: '0 5 0 0'
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
								width: 70,
								value: new Date()
							}
						]
					},

					{
						xtype: 'panel',
						layout: 'hbox',
						cls: 'underline',
						padding: '0 0 0 10',
						items: [
							{
								xtype:'xlabel',
								html:'行程描述',
								padding:'0 10 0 0'
							},
							{
								xtype: 'xtextarea',
								itemId: 'memo',
								height: 195,
								clearIcon: true,
								flex: 1,
								cls: 'font-medium font-grey multilineinfo',
								margin: '0 5 0 0'
							}
						]
					},

					{
						xtype: 'xspacer'
					},

					{
						xtype: 'xpanel',
						itemId:'btnItemAdd',
						layout: 'vbox',
						cls:'icon-add',
						items: [
							{
								xtype: 'xfield',
								itemId: 'activityItem',
								label:'行程安排'
							}
						]
					},

					{
						xtype: 'xdataview',
						itemId: 'itemList',
						itemHeight:95,
						defaultType: 'RouteActivityItemDataItem',
						scrollable:'none',
						binding:'item'
					}
				]
			}
        ]
    },
    
    updateResource:function(resource){
    	this.resource = resource;
    }
});

