Ext.define('YourTour.view.common.FieldEditView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Label','Ext.Panel'],
    config: {
    	id:'FieldEditView',
    	layout:'vbox',
    	items:[
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'编辑',
				items:[
					{
						xtype:'toolbutton',
						itemId:'btnSave',
						text:'确定',
						align:'right'
					}
				]
			},
			
			{
				xtype: 'textfield',
				itemId:'field',
				padding:'0 10 0 10',
				inputCls:'font-medium font-grey'
			},

			{
				xtype: 'label',
				cls:'horizentalLine'
			}
    	]
    }
});

