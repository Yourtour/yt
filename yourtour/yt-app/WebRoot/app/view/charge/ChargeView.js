Ext.define('YourTour.view.charge.ChargeView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar','Ext.Img','YourTour.view.widget.XField','YourTour.view.widget.XMultiField','YourTour.view.widget.XSelectField'],
    config: {
	    id:'ChargeView',
	    layout:'vbox',

        items: [
			{    
				xtype: 'xheaderbar',
				title:'费用明细',
				items:[
					{
						xtype: 'xbutton',
						itemId:'btnEdit',
						align:'right',
						icon: 'resources/icons/icon_header_edit.png'
					},

					{
						xtype: 'xbutton',
						itemId:'btnDelete',
						align:'right',
						icon: 'resources/icons/icon_header_delete.png'
					},

					{
						xtype: 'xbutton',
						itemId:'btnDivide',
						align:'right',
						icon: 'resources/icons/icon_header_divide.png'
					}
				]
			},

			{
				xtype : 'xfield',
				itemId : 'name',
				padding:10,
				label : '名称'
			},

			{
				xtype: 'panel',
				layout: 'hbox',
				defaults: {
					flex: 1
				},
				items: [
					{
						xtype: 'xselectfield',
						itemId: 'item',
						padding: 10,
						label: '项目',
						options: [
							{text: '门票', value: 'Ticket'},
							{text: '餐饮', value: 'Food'},
							{text: '住宿', value: 'Hotel'},
							{text: '交通', value: 'Traffic'},
							{text: '购物', value: 'Shopping'},
							{text: '其他', value: 'Other'}
						]
					},
					{
						xtype: 'xdatefield',
						itemId: 'chargeDate',
						padding: 10,
						label: '日期'
					},
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				defaults:{
					flex:1
				},
				items:[
					{
						xtype : 'xfield',
						itemId : 'payment',
						padding:10,
						label : '金额'
					},{
						xtype : 'xfield',
						itemId : 'divideAmount',
						padding:10,
						label : '分摊金额'
					},
				]
			},

			{
				xtype : 'xmultifield',
				padding:10,
				itemId : 'memo',
				label : '备注'
			},
			{
				xtype:'xspacer'
			},

			{
				xtype: 'xdataview',
				itemId: 'divideList',
				flex:1,
				useComponents: false,
				itemTpl:new Ext.XTemplate('<div class="underline font-medium font-grey" style="height:45px; line-height:45px; padding:0px 10px 0px 10px"><span  style="float:left">{nickName}</span><span style="float:right">{payment}</span></div>')
			}
        ]
    }
});

