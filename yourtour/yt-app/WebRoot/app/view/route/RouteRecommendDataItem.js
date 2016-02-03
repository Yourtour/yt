Ext.define('YourTour.view.route.RouteRecommendDataItem', {
	extend: 'YourTour.view.widget.XDataItem',
    xtype: 'RouteRecommendDataItem',
    requires:['Ext.Panel', 'Ext.Img', 'YourTour.view.widget.XField','YourTour.view.widget.XLabel'],
    config: {
		height:150,
		layout:'vbox',
		padding:'0 0 10 0',
    	items:[
			{
				xtype: 'ximage',
				itemId: 'image',
				width:'100%',
				height:'100%',
				mode:'background'
			},

			{
				xtype: 'xfield',
				itemId: 'lineName',
				docked:'top',
				underline:false,
				cls:'x-xopacity',
				fieldCls:'font-white',
				top:10,
				left:10,
				padding:0
			}
		]
    }
});

