Ext.define('YourTour.view.DemoView', {
    extend: 'Ext.Container',
	requires:["YourTour.view.widget.XExtendCarousel"],
    config: {
    	id:'DemoView',
		layout:'card',
        items: [
			{
				xtype:"xextendcarousel",
				height:200,
				items:[
					{
						xtype:'image',
						itemId:'1',
						src:'resources/images/guangdong.jpg'
					},

					{
						xtype:'image',
						itemId:'2',
						src:'resources/images/beijing.jpg'
					},

					{
						xtype:'image',
						itemId:'3',
						src:'resources/images/shanghai.jpg'
					},

					{
						xtype:'image',
						itemId:'4',
						src:'resources/images/guangdong.jpg'
					},

					{
						xtype:'image',
						itemId:'5',
						src:'resources/images/shanghai.jpg'
					},

					{
						xtype:'image',
						itemId:'6',
						src:'resources/images/guangdong.jpg'
					},

					{
						xtype:'image',
						itemId:'7',
						src:'resources/images/shanghai.jpg'
					},

					{
						xtype:'image',
						itemId:'8',
						src:'resources/images/guangdong.jpg'
					},

					{
						xtype:'image',
						itemId:'9',
						src:'resources/images/shanghai.jpg'
					},

					{
						xtype:'image',
						itemId:'10',
						src:'resources/images/guangdong.jpg'
					}
				]
			}
		]
    }
});

