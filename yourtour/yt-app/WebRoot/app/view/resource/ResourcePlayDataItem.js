Ext.define('YourTour.view.resource.ResourcePlayDataItem', {
	extend: 'YourTour.view.widget.XDataItem',
    xtype: 'ResourcePlayDataItem',
    requires:['Ext.Img','YourTour.view.widget.XLabel','YourTour.view.widget.XField'],
    config: {
    	layout:'hbox',
		cls:'underline',
		padding:'10',
        items: [
			{
				xtype:'ximage',
				itemId:'image',
				binding:'imageUrl',
				imageCls:'img-small'
			},

        	{
        		xtype:'panel',
        		layout:'vbox',
				flex:1,
				margin:'0 0 0 10',
        		items:[
					{
						xtype :'panel',
						layout:'hbox',
						items:[
							{
								xtype: "xfield",
								itemId:'name',
								underline:false,
								padding:0,
								flex:1,
								cls:'font-normal font-medium'
							},

							{
								xtype: "xscore",
								itemId:'commentScore',
								margin:'0 0 0 10',
								cls:'font-medium'
							}
						]
					}
        		]
        	}

        ]
    }
});

