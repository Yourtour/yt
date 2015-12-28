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
    			xtype:'panel',
    			layout:'hbox',
    			cls:'nav_arrow underline',
				padding:'0 10 0 10',
    			items:[
					{
						xtype:'xlabel',
						html : '头像',
					},

					{
						xtype:'spacer',
						flex:1
					},

					{
						itemId : 'image',
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
						itemId : 'nickname',
						flex:1,
						xtype : 'xfield',
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
						html : '年龄',
					},

					{
						xtype:'spacer',
						flex:1
					},

					{
						itemId : 'age',
						xtype : 'xlabel',
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
						html : '星座',
					},

					{
						xtype:'spacer',
						flex:1
					},

					{
						itemId : 'constellation',
						xtype : 'xlabel',
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
						flex:1,
						xtype : 'xpickerfield',
						padding:'0 10 0 0'
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
						xtype:'spacer',
						flex:1
					},

					{
						itemId : 'state',
						xtype : 'xlabel',
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

