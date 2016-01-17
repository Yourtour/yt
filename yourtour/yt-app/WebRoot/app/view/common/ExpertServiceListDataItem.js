Ext.define('YourTour.view.common.ExpertServiceListDataItem', {
	extend: 'YourTour.view.widget.XDataItem',
    xtype: 'ExpertServiceListDataItem',
    config: {
      	layout:'hbox',
        items: [
			{
				itemId : 'image',
				xtype : 'ximage',
				margin:'0 5 0 0',
				styleHtmlCls:'img-small',
				binding:'imageUrl'
			},

			{
				xtype:'panel',
				layout:'vbox',
				flex:1,
				items:[
					{
						xtype:'xfield',
						itemId:'title',
						underline:false,
						fieldCls:'font-bold font-normal',
						padding:'0'
					} ,

					{
						xtype:'xmultifield',
						itemId:'memo',
						underline:false,
						padding:'0',
						ellipsis:{
							size:40,
							expandable:false
						}
					}
				]
			}
        ]
    }
});

