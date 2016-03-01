Ext.define('YourTour.view.common.FieldEditView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Label','Ext.Panel'],
    config: {
    	id:'FieldEditView',
    	layout:'card',
    	items:[
			{
				xtype: 'xheaderbar',
				itemId:'headerbar',
				items:[
					{
						xtype: "xbutton",
						itemId:'btnOk',
						icon:'resources/icons/24/icon_header_ok.png',
						align:'right'
					}
				]
			},

			{
				xtype: 'textareafield',
				itemId:'content',
				height:200
			}
    	]
    }
});

