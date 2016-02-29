Ext.define('YourTour.view.user.RegisterProfileView', {
	extend: 'Ext.Container',
    xtype: 'RegisterProfileView',
    requires:['Ext.Panel','Ext.field.Radio','Ext.field.File', 'YourTour.view.widget.XImageSelect', 'YourTour.view.widget.XSpacer', 'YourTour.view.widget.XTitleBar', 'Ext.field.Password','YourTour.view.widget.XTextField', 'YourTour.view.widget.XButton', 'YourTour.view.widget.ToolButton'],
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
				xtype:'panel',
				layout:'hbox',
				margin:'10 0 0 0',
				items:[{
					xtype:'panel',
					layout:'hbox',
					cls:'textfield',
					style:'width:100%',
					items:[
						{
							xtype:'label',
							html:'-',
							fit:1
						},
						{
							xtype:'label',
							html:'标签',
							fit:1
						},
						{
							xtype:'label',
							html:'-',
							fit:1
						}
					]
				}]
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

