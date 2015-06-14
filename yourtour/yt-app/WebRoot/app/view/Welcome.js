Ext.define('YourTour.view.Welcome', {
    extend: 'Ext.Carousel',
    xtype: 'welcome',
    requires:['Ext.Img', 'Ext.Panel', 'Ext.Button'],
    config: {
    	fullscreen: true,
    	
        items: [
            {
            	xtype: 'image',
            	width: '100%',
            	src: 'resources/images/welcome_1.jpg'
            },
            {
            	xtype: 'image',
            	width: '100%',
            	src: 'resources/images/welcome_2.jpg'
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
});

