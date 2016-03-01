Ext.define('YourTour.view.user.UserView', {
    extend: 'Ext.Container',
    requires:['YourTour.view.widget.XField','YourTour.view.widget.XMultiField', 'YourTour.view.widget.XLabel','YourTour.view.widget.XMultiLabel'],
    xtype:'UserView',
    config: {
    	layout:'vbox',
    	items: [
			{
				xtype:'xpanel',
				layout:'hbox',
				itemId:'userLogoPanel',
				style:'background-color:white',
				padding:'10',
				items:[
					{
						itemId : 'userLogo',
						xtype : 'ximage',
						imageCls:'img-user-logo-48'
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
				padding:'10'
			},

			{
				itemId : 'nickName',
				xtype : 'xfield',
				label : '昵称',
				padding:'10',
				fieldCls:'x-field-right'
			},

			{
				itemId : 'birthday',
				xtype : 'xfield',
				label : '生日',
				padding:'10',
				fieldCls:'x-field-right !important'
			},

			{
				itemId : 'constellation',
				xtype : 'xfield',
				label : '星座',
				padding:'10',
				fieldCls:'x-field-right'
			},

			{
				itemId : 'state',
				xtype : 'xfield',
				label : '地区',
				padding:'10',
				fieldCls:'x-field-right'
			}
        ]
    }
});

