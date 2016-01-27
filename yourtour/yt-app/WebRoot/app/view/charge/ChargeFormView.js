Ext.define('YourTour.view.charge.ChargeFormView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar','Ext.Img'],
    config: {
	    id:'ChargeFormView',
	    layout:'vbox',
		defaults:{
			labelWidth:75
		},
        items: [
			{    
				xtype: 'xheaderbar',
				title:'记账',
				items:[
					{
						xtype: "xbutton",
						ui: "normal",
						icon:'resources/icons/icon_header_ok.png',
						itemId:'btnSave',
						align:'right'
					}
				]
			},

			{
				xtype : 'xtextfield',
				itemId : 'amount',
				label : '金额'
			},

			{
				xtype : 'selectfield',
				itemId : 'item',
				label : '项目',
				usePicker:1,
				options: [
					{text:'门票', value:'Ticket'},
					{text:'餐饮', value:'Food'},
					{text:'住宿', value:'Hotel'},
					{text:'交通', value:'Traffic'},
					{text:'购物', value:'Shopping'},
					{text:'其他', value:'Other'}
				]
			},

			{
				xtype : 'xtextfield',
				itemId : 'type',
				label : '类型'
			},

			{
				xtype : 'xtextarea',
				itemId : 'memo',
				label : '备注'
			},

			{
				xtype : 'xfield',
				itemId : 'attachment',
				label : '附件'
			}
        ]
    }
});

