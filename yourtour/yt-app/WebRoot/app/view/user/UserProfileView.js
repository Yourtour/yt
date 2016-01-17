Ext.define('YourTour.view.user.UserProfileView', {
    extend: 'YourTour.view.widget.XPage',
    requires:[ 'YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.XField','YourTour.view.widget.XMultiField', 'YourTour.view.widget.XPickerField', 'YourTour.view.widget.XImagePicker','YourTour.view.widget.XLabel','YourTour.view.widget.XMultiLabel','YourTour.view.widget.XGenderPicker'],
    xtype:'UserProfileView',
    config: {
    	id:'UserProfileView',
    	layout:'vbox',
    	cls:'page',
    	items: [
            {
            	xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'个人信息',
            },

			{
				xtype:'xpanel',
				layout:'hbox',
				itemId:'userLogoPanel',
				style:'background-color:white',
				cls:'nav-arrow',
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
				cls:'spacer'
			},

			{
				itemId : 'slogan',
				xtype : 'xmultifield',
				label : '个性',
				showMore:true,
				fieldCls:'x-field-more',
				padding:'0 10 0 10',
				tappable:true,
			},

			{
				itemId : 'nickName',
				xtype : 'xfield',
				label : '昵称',
				fieldCls:'x-field-right',
				padding:'0 10 0 10',
				tappable:true
			},

			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 10 0 10',
				cls:'nav-arrow row underline font-medium font-grey',
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
				itemId : 'constellation',
				xtype : 'xfield',
				padding:'0 10 0 10',
				label : '星座'
			},

			{
				xtype:'xpanel',
				layout:'hbox',
				padding:'0 10 0 10',
				cls:'nav-arrow row underline font-medium font-grey',
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
				itemId : 'state',
				xtype : 'xfield',
				padding:'0 10 0 10',
				label : '地区',
				tappable:true
			},

			{
				xtype:'panel',
				cls:'spacer'
			},

			{
				itemId : 'tags',
				xtype : 'xmultifield',
				label : '标签',
				fieldCls:'x-field-right',
				padding:'0 10 0 10',
				tappable:true,
			},

			{
				xtype:'xgenderpicker',
				itemId:'genderPicker'
			},

			{
				xtype:'ximagepicker',
				itemId:'imagePicker'
			}
        ]
    }
});

