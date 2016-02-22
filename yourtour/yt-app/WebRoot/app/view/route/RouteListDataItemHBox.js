Ext.define('YourTour.view.route.RouteListDataItemHBox', {
	extend: 'YourTour.view.widget.XDataItem',
    xtype: 'RouteListDataItemHBox',
    config: {
		layout:'vbox',
		cls:'x-xspacer',
		items: [
			{
				xtype: 'panel',
				layout: 'hbox',
				cls: 'row nav-arrow underline',
				items:[
					{
						xtype: "xfield",
						itemId:'name',
						underline:false
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				padding:'10',
				items:[
					{
						xtype:'ximage',
						itemId:'image',
						imageCls:'img-small',
						margin:'0 5 0 0'
					},

					{
						xtype:'panel',
						layout:'vbox',
						flex:1,
						items:[
							{
								xtype:'xmultifield',
								itemId:'reason',
								padding:0,
								underline:false,
								ellipsis:{
									size:50,
									expandable:false
								}
							}
						]
					}
				]
			}
		]
    }
});

