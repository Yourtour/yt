Ext.define('YourTour.view.route.RouteScheduleFormView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','YourTour.view.widget.XLabel','YourTour.view.widget.XImage','YourTour.view.widget.XProcessing', 'YourTour.view.widget.XDataView','YourTour.view.widget.XVerticalLine', 'YourTour.view.route.RouteActivityItemDataItem','YourTour.view.expert.ExpertServiceListDataItem','YourTour.view.widget.XHeaderBar'],
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
						indicator:'nav-arrow',
						binding: 'resource.name'
					},

					{
						xtype: 'xfield',
						itemId: 'address',
						label:'景点地址',
						indicator:'nav-arrow',
						binding: 'resource.address'
					},

					{
						xtype: 'xspacer'
					},

					{
						xtype: 'xfield',
						itemId: 'time',
						label:'行程时间',
						dataChange: function (field, record) {
							field.setText(record.get('startTime') + '-' + record.get('endTime'));
						}
					},

					{
						xtype: 'xmultifield',
						itemId:'memo',
						label:'行程描述',
						ellipsis:{
							size:100,
							expandable:true
						}
					},

					{
						xtype: 'xspacer'
					},


					{
						xtype: 'xfield',
						itemId: 'activityItem',
						label:'行程安排'
					},

					{
						xtype: 'xdataview',
						itemId: 'items',
						defaultType: 'RouteActivityItemDataItem',
						itemHeight:95,
						scrollable:'none'
					},

					{
						xtype: 'xtoolbar',
						docked: 'bottom',
						itemId:'toolbar',
						items: [
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
							}
						]
					}
				]
			}
        ]
    }
});

