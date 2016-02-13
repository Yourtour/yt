Ext.define('YourTour.view.route.RouteScheduleFormView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','YourTour.view.widget.XLabel','YourTour.view.widget.XImage','YourTour.view.widget.XProcessing', 'YourTour.view.widget.XDataView','YourTour.view.widget.XVerticalLine', 'YourTour.view.route.RouteActivityItemDataItem','YourTour.view.common.ExpertServiceListDataItem','YourTour.view.widget.XHeaderBar'],
    config: {
    	id:'RouteScheduleFormView',
    	layout:'card',
    	items:[
    		{
				xtype: 'xheaderbar',
				itemId:'headerbar',
				items:[
				]
			},

			{
				xtype:'xprocessing'
			},

			{
				xtype: 'xpagebody',
				layout: 'vbox',
				items: [
					{
						itemId: 'image',
						xtype: 'ximage',
						imageCls: 'img-medium',
						binding: 'resource.imageUrl'
					},

					{
						xtype: 'xfield',
						itemId: 'resName',
						label:'景点名称',
						cls:'nav-arrow',
						binding: 'resource.name'
					},

					{
						xtype: 'xfield',
						itemId: 'address',
						label:'景点地址',
						cls:'nav-arrow',
						binding: 'resource.address'
					},

					{
						xtype: 'xfield',
						itemId: 'phone',
						label:'景点电话',
						cls:'nav-arrow',
						binding: 'resource.phone'
					},

					{
						xtype: 'xfield',
						itemId: 'time',
						label:'开放时间',
						dataChange: function (field, record) {
							field.setText(record.get('startTime') + '-' + record.get('startTime'));
						}
					},

					{
						xtype: 'xmultifield',
						itemId: 'memo',
						label:'景点简介',
						ellipsis: {
							size: 100,
							expandable: true
						}
					},

					{
						xtype: 'panel',
						cls: 'spacer'
					},

					{
						xtype: 'label',
						html: '安排',
						cls: 'row underline font-medium font-grey',
						padding: '0 0 0 10'
					},
					{
						xtype: 'xdataview',
						itemId: 'items',
						scrollable: null,
						useComponents: true,
						defaultType: 'RouteActivityItemDataItem',
						binding: 'itemsStore'
					},

					{
						xtype: 'panel',
						cls: 'spacer'
					},

					{
						xtype: 'label',
						html: '服务',
						cls: 'row underline font-medium font-grey',
						padding: '0 0 0 10'
					},
					{
						xtype: 'xdataview',
						itemId: 'services',
						scrollable: null,
						useComponents: true,
						defaultType: 'ExpertServiceListDataItem',
						binding: 'servicesStore'
					},

					{
						xtype: 'xtoolbar',
						docked: 'bottom',
						itemId:'toolbar',
						items: [
							{
								xtype: 'spacer',
								flex: 1
							},

							{
								xtype: 'xbutton',
								text: '留言',
								icon: 'resources/icons/16/icon_message.png',
								itemId: 'btnMessage'
							},
							{
								xtype:'spacer',
								flex:1
							},
							{
								xtype: 'xbutton',
								text: '签到',
								icon: 'resources/icons/24/icon_checkin.png',
								itemId: 'btnCheckin'
							}, {
								xtype:'spacer',
								flex:1
							}, {
								xtype: 'xbutton',
								text: '随记',
								icon: 'resources/icons/24/icon_note.png',
								itemId: 'btnNotes'
							},
							{
								xtype:'spacer',
								flex:1
							},
						]
					}
				]
			}
        ]
    }
});

