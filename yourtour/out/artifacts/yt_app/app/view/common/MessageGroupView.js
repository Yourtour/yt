Ext.define('YourTour.view.common.MessageGroupView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar'],
    config: {
	    id:'MessageGroupView',
	    layout:'vbox',
        items: [
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'聊天信息'
			}
        ]
    }
});

