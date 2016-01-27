Ext.define('YourTour.view.charge.ChargeFormView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar','Ext.Img'],
    config: {
	    id:'ChargeFormView',
	    layout:'vbox',
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
				itemId : 'slogan',
				label : '金额'
			},

			{
				xtype : 'xtextfield',
				itemId : 'slogan',
				label : '项目'
			},

			{
				xtype : 'xtextfield',
				itemId : 'slogan',
				label : '类型'
			},

			{
				xtype : 'xtextarea',
				itemId : 'slogan',
				label : '备注'
			},

			{
				xtype : 'xfield',
				itemId : 'slogan',
				label : '附件'
			},
        ]
    }
});

