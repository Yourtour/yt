Ext.define('YourTour.view.member.MemberView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar','YourTour.view.user.UserView'],
    config: {
	    id:'MemberView',
	    layout:'vbox',
        items: [
			{    
				xtype: 'xheaderbar',
				title:'详细资料'
			},

			{
				xtype:'UserView',
				flex:1
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
						text:'加入',
						iconAlign:'top',
						icon: 'resources/icons/icon_button_member_add.png'
					},
					{
						xtype: 'xbutton',
						itemId:'btnDelete',
						text:'取消',
						iconAlign:'top',
						icon: 'resources/icons/icon_button_chat.png'
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

