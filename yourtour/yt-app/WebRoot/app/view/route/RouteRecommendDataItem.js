Ext.define('YourTour.view.route.RouteRecommendDataItem', {
	extend: 'YourTour.view.widget.XDataItem',
    xtype: 'RouteRecommendDataItem',
    requires:['Ext.Panel', 'Ext.Img', 'YourTour.view.widget.XField','YourTour.view.widget.XLabel'],
    config: {
    	items:[
    		{
    			xtype:'panel',
    			layout:'vbox',
    			height:150,
    			items:[
		    		{
		   				itemId : 'image',
						xtype : 'ximage',
						binding:'imageUrl',
						imageCls:'img-medium'
		    		},

					{
						xtype: 'panel',
						layout: 'vbox',
						padding: '0 10 0 10',
						docked: 'top',
						top: 1,
						left:1,
						items: [
							{
								xtype: 'xscore',
								star:true
							}
						]
					}
		    	]
    		},

			{
				xtype: 'xfield',
				itemId: 'lineName',
				underline:false,
				fieldCls:'font-bold',
				padding:'5 0 0 0'
			},

			{
				xtype:'panel',
				layout:'hbox',
				defaults:{
					padding:'5 0 0 0',
					underline:false,
					fieldCls:'font-small',
					labelCls:'font-grey'
				},
				items:[
					{
						xtype:'xfield',
						itemId:'duration',
						label:'日程天数:'
					},

					{
						xtype:'xfield',
						itemId:'price',
						label:'价格:',
						margin:'0 0 0 20'
					},
				]
			}
		]
    }
});

