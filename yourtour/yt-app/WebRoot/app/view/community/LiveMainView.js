Ext.define('YourTour.view.community.LiveMainView', {
	extend: 'YourTour.view.widget.XPage',
    xtype: 'LiveMainView',
    requires:['Ext.Img', 'Ext.Panel', 'YourTour.view.widget.XToolbar'],
    config: {
    	id:'LiveMainView',
    	itemid:'LiveMainView',
    	layout:'card',
        items: [
            {
            	xtype: 'xheaderbar',
				itemId:'headerbar',
				backButton:false,
                title: '发现'
            }
        ]
    }
});

