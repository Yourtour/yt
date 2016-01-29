Ext.define('YourTour.view.charge.ChargeDivisionFormView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar','Ext.Img','YourTour.view.widget.XField','YourTour.view.widget.XMultiField','YourTour.view.widget.XSelectField'],
    config: {
	    id:'ChargeDivisionFormView',
	    layout:'vbox',
        items: [
			{    
				xtype: 'xheaderbar',
				title:'费用分摊',
				items:[
					{
						xtype: "xbutton",
						icon:'resources/icons/icon_header_ok.png',
						itemId:'btnSave',
						align:'right'
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
						itemId : 'amount',
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
				xtype : 'xfield',
				itemId : 'userId',
				hidden:true
			},

			{
				xtype : 'xfield',
				itemId : 'nickName',
				label : '伙伴'
			},

			{
				xtype : 'xtextfield',
				itemId : 'divisionAmount',
				label : '金额',
				labelWidth:60
			}
        ]
    }
});

