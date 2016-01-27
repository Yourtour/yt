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
				itemId : 'slogan',
				label : '门票',
				tappable:true
			},

			{
				xtype : 'xfield',
				itemId : 'slogan',
				label : '餐饮',
				tappable:true
			},

			{
				xtype : 'xfield',
				itemId : 'slogan',
				label : '住宿',
				tappable:true
			},

			{
				xtype : 'xfield',
				itemId : 'slogan',
				label : '交通',
				tappable:true
			},

			{
				xtype : 'xfield',
				itemId : 'slogan',
				label : '购物',
				tappable:true
			},


			{
				xtype : 'xfield',
				itemId : 'slogan',
				label : '其他',
				tappable:true
			},

			{
				xtype:'xspacer'
			},

			{
				xtype : 'xfield',
				itemId : 'slogan',
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
						itemId:'btnAdd',
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

