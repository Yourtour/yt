Ext.define('YourTour.view.resource.ResourceActivityItem', {
    extend: 'Ext.Container',
    xtype: 'ResourceActivityItem',
    requires:['YourTour.view.widget.XField','YourTour.view.widget.XMultiField'],
    config: {
    	layout:'vbox',
        items: [
			{
				xtype:'xmultifield',
				itemId:'memo',
				fieldCls:'font-white !important',
				bottom:0,
				underline:false
			}
        ]
    }
});

