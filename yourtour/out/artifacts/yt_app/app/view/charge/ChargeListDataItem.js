Ext.define('YourTour.view.charge.ChargeListDataItem', {
	extend: 'YourTour.view.widget.XDataItem',
	xtype:'ChargeListDataItem',
    config: {
	    layout:'hbox',
        items: [
			{
				xtype:'xfield',
				itemId:'date',
				underline:false,
				flex:2,
				dataChange: function (field, record){
					field.setText(Ext.Date.format(new Date(record.get('chargeDate')),'Y/m/d'));
				}
			},

			{
				xtype:'xfield',
				itemId:'name',
				flex:3,
				underline:false
			},

			{
				xtype : 'xselectfield',
				itemId : 'type',
				underline:false,
				flex:1,
				options: [
					{text:'个人', value:'1'},
					{text:'分摊', value:'2'}
				]
			},

			{
				xtype:'xfield',
				itemId:'payment',
				underline:false,
				flex:1
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

