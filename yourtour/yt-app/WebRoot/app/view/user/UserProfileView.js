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
						icon:'resources/icons/24/icon_header_ok.png',
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
				cls:'nav-arrow underline',
				padding:10,
				items:[
					{
						xtype: 'xuserlogo',
						itemId: 'userLogo',
						editable:true,
						cls:'x-xmedium'
					},
				]
			},

			{
				xtype : 'xmultifield',
				itemId : 'slogan',
				label : '个性',
				editable:true,
				fieldCls:'x-field-more',
				ifNull:'请简单描述您个人'
			},

			{
				xtype:'panel',
				cls:'spacer'
			},

			{
				xtype : 'xfield',
				itemId : 'nickName',
				label : '昵称',
				editable:true
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
				itemId : 'residence',
				label : '居住地',
				editable:true
			},

			{
				xtype : 'xfield',
				itemId : 'nativePlace',
				label : '籍贯',
				editable:true
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
				editable:true
			}
        ]
    }
});

