Ext.define('YourTour.view.setting.UserSettingView', {
    extend: 'YourTour.view.widget.XPage',
    requires:[ 'YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.XField', 'YourTour.view.widget.XPickerField', 'YourTour.view.widget.XLabel','YourTour.view.widget.XGenderPicker', 'YourTour.view.widget.XButton'],
    xtype:'UserSettingView',
    config: {
    	id:'UserSettingView',
    	layout:'vbox',
    	cls:'page',
    	items: [
            {
            	xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'设置',
            },

			{
				xtype:'xpanel',
				layout:'vbox',
				items:[
					{
						xtype:'xlabel',
						padding:'0 10 0 10',
						cls:'row underline font-medium',
						html:'个人设置',
					},

					{
						xtype:'xlabel',
						padding:'0 10 0 40',
						cls:'row underline font-medium font-grey nav-arrow',
						html:'推送设置',
					},

					{
						xtype:'xlabel',
						padding:'0 10 0 40',
						cls:'row underline font-medium font-grey nav-arrow',
						html:'位置分享',
					},

					{
						xtype:'xlabel',
						padding:'0 10 0 40',
						cls:'row underline font-medium font-grey nav-arrow',
						html:'清除缓存',
					},
				]
			},

			{
				xtype: 'xpanel',
				layout: 'vbox',
				margin:'10 0 0 0',
				flex:1,
				items:[
					{
						xtype:'xlabel',
						padding:'0 10 0 10',
						cls:'row underline font-medium',
						html:'应用相关',
					},

					{
						xtype:'xlabel',
						padding:'0 10 0 40',
						cls:'row underline font-medium font-grey nav-arrow',
						html:'版本检查',
					},

					{
						xtype:'xlabel',
						padding:'0 10 0 40',
						cls:'row underline font-medium font-grey nav-arrow',
						html:'意见反馈',
					},

					{
						xtype:'xlabel',
						padding:'0 10 0 40',
						cls:'row underline font-medium font-grey nav-arrow',
						html:'游徒评价',
					},

					{
						xtype:'xlabel',
						padding:'0 10 0 40',
						cls:'row underline font-medium font-grey nav-arrow',
						html:'游徒分享',
					},

					{
						xtype:'xlabel',
						padding:'0 10 0 40',
						cls:'row underline font-medium font-grey nav-arrow',
						html:'关于游徒',
					},
				]
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

