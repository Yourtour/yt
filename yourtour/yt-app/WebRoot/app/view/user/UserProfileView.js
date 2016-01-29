Ext.define('YourTour.view.user.UserProfileView', {
    extend: 'YourTour.view.widget.XPage',
    requires:[ 'YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.XField','YourTour.view.widget.XMultiField', 'YourTour.view.widget.XGenderSelect', 'YourTour.view.widget.XLabel','YourTour.view.widget.XMultiLabel'],
    xtype:'UserProfileView',
    config: {
    	id:'UserProfileView',
    	items: [
            {
            	xtype: 'xheaderbar',
				title:'个人信息',
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
				xtype:'xpanel',
				layout:'hbox',
				itemId:'userLogoPanel',
				style:'background-color:white',
				cls:'nav-arrow',
				padding:10,
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
				cls:'spacer'
			},

			{
				itemId : 'slogan',
				xtype : 'xmultifield',
				label : '个性',
				showMore:true,
				fieldCls:'x-field-more',
				padding:'0 10 0 10'
			},

			{
				xtype : 'xfield',
				itemId : 'nickName',
				label : '昵称',
				fieldCls:'text-align-right'
			},

			{
				xtype:'panel',
				layout:'hbox',
				cls:'row underline font-medium',
				items:[
					{
						xtype:'xlabel',
						html : '生日'
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
				xtype : 'xfield',
				itemId : 'constellation',
				label : '星座'
			},

			{
				xtype:'xpanel',
				layout:'hbox',
				cls:'row underline font-medium',
				items:[
					{
						xtype:'xlabel',
						html : '性别'
					},

					{
						itemId : 'gender',
						xtype : 'xgenderselect',
						flex:1,
						style:'text-align:right'
					}
				]
			},

			{
				xtype : 'xfield',
				itemId : 'state',
				label : '地区'
			},

			{
				xtype:'panel',
				cls:'spacer'
			},

			{
				itemId : 'tags',
				xtype : 'xmultifield',
				label : '标签',
				fieldCls:'text-align-right',
				padding:'0 10 0 10'
			}
        ]
    }
});

