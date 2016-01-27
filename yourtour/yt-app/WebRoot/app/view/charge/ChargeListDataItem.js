Ext.define('YourTour.view.charge.ChargeListDataItem', {
	extend: 'YourTour.view.widget.XDataItem',
	xtype:'ChargeListDataItem',
    config: {
	    layout:'hbox',
        items: [
			{
				xtype:'xfield',
				itemId:'date',
				padding:0,
				underline:false,
				dataChange: function (field, record){
					field.setText(Ext.Date.format(new Date(record.get('chargeDate')),'Y/m/d'));
				}
			},

			{
				xtype:'xfield',
				itemId:'name',
				padding:0,
				margin:'0 0 0 10',
				flex:1,
				underline:false
			},

			{
				xtype:'xfield',
				itemId:'amount',
				padding:0,
				underline:false
			}
        ]
    },

	updateRecord:function(record){
		if(record) {
			if(record.get('hidden') == true){
				this.hide();
				return;
			}

			this.callParent(arguments);
		}
	}
});

