Ext.define('YourTour.view.resource.ResourceActivityItem', {
    extend: 'Ext.Container',
    xtype: 'ResourceActivityItem',
    requires:['YourTour.view.widget.XField','YourTour.view.widget.XMultiField'],
    config: {
    	layout:'vbox',
		record:null,
        items: [
			{
				xtype:'xmultifield',
				itemId:'memo',
				fieldCls:'font-white !important',
				bottom:0,
				underline:false
			}
        ]
    },

	updateRecord: function(record) {
		var me = this;
		if(record){
			var memo = me.down('#memo');
			memo.setText(record.get('memo'));

			me.setStyle('background-image: url(' + record.get('imageUrl') +  ');background-repeat: no-repeat;background-position: center center;');
		}
	}
});

