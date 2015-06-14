Ext.define('YourTour.view.Launch', {
    extend: 'Ext.Container',
    xtype: 'launch',
    requires:['Ext.Img', 'Ext.Label'],
    config: {
    	layout:'vbox',
    	fullscreen: true,
    	
        items: [
            {
            	xtype: 'image',
            	flex : 4,
            	src: './resources/images/launch.jpg',
            	style:'width:100%;background-size:cover'
            },
            {
            	xtype: 'label',
            	width: '100%',
            	flex : 1,
            	html: '世界是一本书，而不旅行的人们只读了其中的一页。',
            	style:'text-align:center; margin'
            }
        ]
    }
});

