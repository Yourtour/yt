Ext.define('YourTour.view.WelcomeView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Img', 'Ext.Label','Ext.Carousel'],
    config: {
    	layout:'card',
    	fullscreen: true,
    	id:'WelcomeView',
        items: [
            {
            	xtype: 'carousel',
            	itemId:'routeCarousel',
            	direction:'horizontal',
            	items:[
            		{
            			xtype:"panel",
						style:"width: 100%; background-image: url(resources/images/welcome_1.jpg); background-size: cover;"
            		},
            		{
						xtype:"panel",
						style:"width: 100%; background-image: url(resources/images/welcome_2.jpg); background-size: cover;"
            		},
            		{
		            	xtype:"panel",
		            	style:"width: 100%; background-image: url(resources/images/welcome_3.jpg); background-size: cover;",
		            	layout:'hbox',
		            	items:[
		            		{
			            		xtype: 'button',
			            		itemId:'enter',
			            		ui:'normal',
			            		height:50,
			            		docked:'bottom',
			                	left:120,
			                	right:120,
			                	bottom:50,
			            		style:'background:white;opacity:0.2; color:#000; font-size:16px; font-weight:bold',
			                	text: '开始旅行'
			            	}
		            	]
            		}
            	]
            }
        ]
    }
});

