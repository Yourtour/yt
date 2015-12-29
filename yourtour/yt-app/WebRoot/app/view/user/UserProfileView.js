Ext.define('YourTour.view.user.UserProfileView', {
    extend: 'YourTour.view.widget.XPage',
    requires:[ 'YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.XField', 'YourTour.view.widget.XPickerField', 'YourTour.view.widget.XLabel','YourTour.view.widget.XGenderPicker', 'YourTour.view.widget.XButton'],
    xtype:'UserProfileView',
    config: {
    	id:'UserProfileView',
    	layout:'vbox',
    	cls:'page',
    	items: [
            {
            	xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'编辑个人信息',
            },

			{
				xtype:'xpanel',
				layout:'hbox',
				itemId:'profile',
				style:'background-color:white',
				cls:'nav_arrow',
				padding:10,
				tappable:true,
				items:[
					{
						itemId : 'userLogo',
						xtype : 'image',
						model:'tag'
					}
									]
			},

			{
				xtype:'panel',
				layout:'hbox',
				margin:'10 0 0 0',
				padding:'0 10 0 10',
				cls:'nav_arrow row underline font-medium font-grey',
				items:[
					{
						xtype:'xlabel',
						html : '昵称',
					},

					{
						itemId : 'nickName',
						xtype : 'xfield',
						tappable:true,
						flex:1,
						style:'text-align:right'
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 10 0 10',
				cls:'nav_arrow row underline font-medium font-grey',
				items:[
					{
						xtype:'xlabel',
						html : '生日',
					},

					{
						itemId : 'birthday',
						xtype : 'datepickerfield',
						flex:1,
						value:new Date(),
						dateFormat:'Y年m月d日',
						clearIcon: true,
						inputCls:'text_right font-medium font-grey'
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 10 0 10',
				cls:'bg_white row underline font-medium font-grey',
				items:[
					{
						xtype:'xlabel',
						html : '星座',
					},

					{
						itemId : 'constellation',
						xtype : 'xfield',
						flex:1,
						style:'text-align:right'
					}
				]
			},

			{
				xtype:'xpanel',
				layout:'hbox',
				padding:'0 10 0 10',
				cls:'nav_arrow row underline font-medium font-grey',
				items:[
					{
						xtype:'xlabel',
						html : '性别',
					},

					{
						itemId : 'gender',
						xtype : 'xpickerfield',
						flex:1,
						style:'text-align:right'
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 10 0 10',
				cls:'nav_arrow row underline font-medium font-grey',
				items:[
					{
						xtype:'xlabel',
						html : '地区',
					},

					{
						itemId : 'state',
						xtype : 'xfield',
						flex:1,
						tappable:true,
						style:'text-align:right'
					}
				]
			},

			{
				xtype:'xgenderpicker',
				itemId:'genderPicker'
			},

			{
				xtype: 'xbutton',
				docked: 'bottom',
				itemId:'btnQuit',
				text:'退出'
			}
        ]
    }
});

