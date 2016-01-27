Ext.define('YourTour.view.charge.ChargeMainView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar', 'YourTour.view.member.MemberItemView', 'Ext.Img'],
    config: {
	    id:'ChargeMainView',
	    layout:'vbox',
        items: [
			{    
				xtype: 'xheaderbar',
				title:'费用'
			},

			{
				xtype: 'image',
				height:150,
				width:'100%',
				src:'resources/images/image_member.jpg',
				mode: 'tag'
			},

			{
				xtype:'xspacer'
			},

			{
				xtype : 'xfield',
				itemId : 'ticket',
				label : '门票',
				indicator: 'nav-arrow',
				fieldCls:'x-field-right'
			},

			{
				xtype : 'xfield',
				itemId : 'food',
				label : '餐饮',
				indicator: 'nav-arrow',
				fieldCls:'x-field-right'
			},

			{
				xtype : 'xfield',
				itemId : 'hotel',
				label : '住宿',
				indicator: 'nav-arrow',
				fieldCls:'x-field-right'
			},

			{
				xtype : 'xfield',
				itemId : 'traffic',
				label : '交通',
				indicator: 'nav-arrow',
				fieldCls:'x-field-right'
			},

			{
				xtype : 'xfield',
				itemId : 'shopping',
				label : '购物',
				indicator: 'nav-arrow',
				fieldCls:'x-field-right'
			},


			{
				xtype : 'xfield',
				itemId : 'other',
				label : '其他',
				indicator: 'nav-arrow',
				fieldCls:'x-field-right'
			},

			{
				xtype:'xspacer'
			},

			{
				xtype : 'xfield',
				itemId : 'total',
				fieldCls:'x-field-right',
				label : '合计'
			},

			{
				xtype: 'xtoolbar',
				docked: 'bottom',
				items: [
					{
						xtype:'spacer',
						flex:1
					},
					{
						xtype: 'xbutton',
						itemId:'btnCharge',
						text:'记账',
						iconAlign:'top',
						icon: 'resources/icons/icon_button_member_add.png'
					},
					{
						xtype:'spacer',
						flex:1
					}
				]
			}
        ]
    }
});

