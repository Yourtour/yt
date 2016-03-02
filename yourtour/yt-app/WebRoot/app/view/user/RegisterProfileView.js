Ext.define('YourTour.view.user.RegisterProfileView', {
	extend: 'Ext.Container',
    xtype: 'RegisterProfileView',
    requires:['Ext.Panel','Ext.field.Radio','YourTour.view.user.UserTagDataItem', 'YourTour.view.widget.XImageSelect', 'YourTour.view.widget.XSpacer', 'YourTour.view.widget.XTitleBar', 'Ext.field.Password','YourTour.view.widget.XTextField', 'YourTour.view.widget.XButton', 'YourTour.view.widget.ToolButton'],
    config: {
		layout: 'vbox',
		defaults: {
			padding: '0 10 0 10',
			style:'background-color:white'
		},
		scrollable: 'none',
        items: [
			{
				xtype: 'xheaderbar',
				title: '用户注册',
				backAction:function(headerbar){
					headerbar.up('#LoginMainView').setActiveItem(1);
				}
			},

			{
				xtype: 'container',
				layout: 'hbox',
				cls: 'row underline icon-nickname',
				padding: '0 10 0 50',
				items: [
					{
						xtype: 'xtextfield',
						placeHolder: '请输入昵称',
						inputCls: 'font-grey',
						flex: 1,
						name: 'nickName',
						itemId: 'nickName'
					}
				]
			},

			{
				xtype: 'container',
				layout: 'hbox',
				cls: 'row underline icon-nickname',
				padding: '0 10 0 50',
				items: [
					{
						xtype:'xgenderselect',
						itemId:'gender',
						name:'gener',
						displayField:"text",
						valueField:"value",
						flex: 1
					}
				]
			},

			{
				xtype: 'container',
				layout: 'hbox',
				cls: 'underline icon-portrait',
				padding: '2 10 0 50',
				height:80,
				items: [
					{
						xtype:'ximageselect',
						itemId:'userLogo',
						cls:'x-xuserlogo',
						flex:1,
						image:{
							fileName:'user.jpg',
							maximumImageCount:1
						},
						maxHeight:75
					}
				]
			},
			{
				xtype:'xspacer'
			},

			{
				xtype:'xlabel',
				html:'旅行标签',
				cls:'underline font-normal'
			},
			{
				xtype: 'xgridview',
				itemId: 'tagList',
				useComponents: true,
				defaultType: 'UserTagDataItem',
				cols:3
			},

            {
            	xtype:'xspacer'
            },
            
            {
            	xtype:'xbutton',
            	id:'btnRegisterDone',
				cls: 'x-button-primary',
            	text:'注册'
            }
        ]
    }
});

