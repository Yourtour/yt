Ext.define('YourTour.view.service.RouteServiceBookView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Label','Ext.Panel'],
    config: {
    	id:'RouteServiceBookView',
		layout:'vbox',
    	items:[
			{    
				xtype: 'xheaderbar',
				title:'服务预订',
				items:[
					{
						xtype: "xbutton",
						itemId:'btnSave',
						icon:'resources/icons/24/icon_header_ok.png',
						align:'right'
					}
				]
			},

			{
				xtype: 'xpagebody',
				layout: 'vbox',
				items: [
					{
						xtype: 'xfield',
						itemId: 'serviceName',
						label:'服务名称'
					},

					{
						xtype: 'xfield',
						itemId: 'serviceFee',
						label:'服务费用'
					},

					{
						xtype:'xspacer'
					},
					{
						xtype:'panel',
						layout:'hbox',
						cls:'underline font-medium font-grey',
						items:[
							{
								xtype:'xlabel',
								html:'开始日期'
							},
							{
								xtype: 'xdatefield',
								itemId: 'fromDate',
								value: new Date(),
								margin:'0 10 0 0',
								underline:false
							},
							{
								xtype: 'timepickerfield',
								itemId: 'fromTime',
								value: new Date()
							}
						]
					},

					{
						xtype:'panel',
						layout:'hbox',
						cls:'underline font-medium font-grey',
						items:[
							{
								xtype:'xlabel',
								html:'结束日期'
							},
							{
								xtype: 'xdatefield',
								itemId: 'endDate',
								value: new Date(),
								margin:'0 10 0 0',
								underline:false
							},
							{
								xtype: 'timepickerfield',
								itemId: 'endTime',
								value: new Date(),
								margin:'0 10 0 0',
							},

							{
								xtype: 'xlabel',
								itemId: 'clearEnd',
								width:'45',
								cls:'x-clear-icon'
							}
						]
					},

					{
						xtype:'panel',
						layout:'hbox',
						cls:'underline font-medium font-grey',
						items:[
							{
								xtype:'xlabel',
								html:'消费地点'
							},
							{
								xtype: 'textfield',
								itemId: 'place',
								flex:1
							}
						]
					},

					{
						xtype:'panel',
						layout:'hbox',
						cls:'underline font-medium font-grey',
						items:[
							{
								xtype:'xlabel',
								html:'消费人数'
							},
							{
								xtype: 'textfield',
								itemId: 'adultNum',
								flex:1,
								clearIcon:false,
								label:'成人',
								labelWidth:'20'
							},
							{
								xtype: 'textfield',
								itemId: 'oldNum',
								flex:1,
								clearIcon:false,
								label:'老人',
								labelWidth:'20'
							},
							{
								xtype: 'textfield',
								itemId: 'childNum',
								flex:1,
								clearIcon:false,
								label:'儿童',
								labelWidth:'20'
							}
						]
					},

					{
						xtype:'panel',
						layout:'hbox',
						cls:'underline font-medium font-grey',
						items:[
							{
								xtype:'xlabel',
								html:'合计费用'
							},
							{
								xtype: 'textfield',
								itemId: 'fee',
								flex:1
							}
						]
					},

					{
						xtype:'panel',
						layout:'hbox',
						cls:'underline font-medium font-grey',
						items:[
							{
								xtype:'xlabel',
								html:'服务说明'
							},
							{
								xtype: 'textareafield',
								itemId: 'memo',
								flex:1
							}
						]
					}
				]
			}
    	]
    }
});

