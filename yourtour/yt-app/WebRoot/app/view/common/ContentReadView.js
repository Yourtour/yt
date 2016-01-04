Ext.define('YourTour.view.common.ContentReadView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Label','Ext.Panel'],
    config: {
    	id:'ContentReadView',
    	layout:'card',
    	items:[
			{
				xtype: 'xheaderbar',
				itemId:'headerbar'
			},

			{
				xtype: 'panel',
				itemId:'content',
				padding:'5 10 5 10',
				cls:'multilineinfo'
			}
    	]
    }
});

