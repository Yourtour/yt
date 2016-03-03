Ext.define('YourTour.view.user.RegisterProfileView', {
	extend: 'Ext.Container',
    xtype: 'RegisterProfileView',
    requires:['Ext.Panel', 'YourTour.view.widget.XImageSelect', 'YourTour.view.widget.XSpacer','YourTour.view.widget.XTextField', 'YourTour.view.widget.XButton', 'YourTour.view.widget.XSelectField', 'YourTour.view.widget.XGenderField'],
    config: {
		layout: 'vbox',
		defaults: {
			padding: '0 10 0 10',
			style:'background-color:white'
		},
		scrollable: null,
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
						xtype:'xgenderfield',
						itemId:'gender',
						name:'gener',
						field:{
							align:'left',
						},
						underline:false,
						flex: 1,
						editable:{
							enable:true,
							icon:'none'
						},
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
				xtype: 'container',
				layout: 'hbox',
				cls: 'row underline',
				padding: '0 10 0 50',
				items: [
					{
						xtype: 'xselectfield',
						itemId: 'tags',
						dd:'dddd',
						field:{
							align:'left',
							placeHolder:'标签选择'
						},
						selectable: {
							style: 'grid',
							multiselect: true
						},
						editable:{
							enable:true,
							icon:'none'
						},
						underline:false,
						flex: 1
					}
				]
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

