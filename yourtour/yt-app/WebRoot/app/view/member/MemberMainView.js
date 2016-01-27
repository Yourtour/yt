Ext.define('YourTour.view.member.MemberMainView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar', 'YourTour.view.member.MemberItemView', 'Ext.Img'],
    config: {
	    id:'MemberMainView',
	    layout:'vbox',
        items: [
			{    
				xtype: 'xheaderbar',
				title:'伙伴'
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
				xtype:'xdataview',
				itemId:'memberList',
				flex:1,
		        useComponents: true,
		        defaultType: 'MemberItemView'
	   		},

			{
				xtype: 'xtoolbar',
				docked: 'bottom',
				itemId: 'btnGroup1',
				defaults:{
					flex:1
				},
				items: [
					{
						xtype: 'xbutton',
						itemId:'btnAdd',
						text:'加人',
						iconAlign:'top',
						icon: 'resources/icons/icon_button_member_add.png'
					},
					{
						xtype: 'xbutton',
						itemId:'btnMessage',
						text:'聊天',
						iconAlign:'top',
						icon: 'resources/icons/icon_button_chat.png'
					},

					{
						xtype: 'xbutton',
						itemId:'btnPosition',
						text:'位置',
						iconAlign:'top',
						icon: 'resources/icons/icon_button_position.png'
					}
				]
			}
        ]
    }
});

