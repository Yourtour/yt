Ext.define('YourTour.view.Launch', {
    extend: 'Ext.Container',
    xtype: 'launch',
    requires:['Ext.Img', 'Ext.Label','Ext.Carousel'],
    config: {
    	layout:'card',
    	fullscreen: true,
    	itemId:'launch',
    	
        items: [
            {
            	xtype: 'image',
            	src: 'resources/images/launch.jpg',
            	style:'width:100%;background-size:cover'
            },
            
            {
            	xtype: 'carousel',
            	itemId:'routeCarousel',
            	indicator:false,
            	direction:'horizontal',
            	items:[
            		{
            			xtype: 'image',
		            	src: './resources/images/welcome_1.jpg',
		            	style:'width:100%;background-size:cover'
            		},
            		{
            			xtype: 'image',
		            	src: './resources/images/welcome_2.jpg',
		            	style:'width:100%;background-size:cover'
            		},
            		{
		            	xtype:"panel",
		            	layout:'vbox',
		            	style:"background:url('resources/images/welcome_3.jpg') no-repeat center;background-size:auto 100%",
		            	items:[
			            	{
			            		xtype: 'button',
			            		itemId:'enter',
			            		ui:'normal',
			                	text: '进入游途>>',
			                	docked:'bottom',
			                	style:' margin-bottom:50px'
			            	}
            			]
            		}
            	]
            }
        ]
    }
});

