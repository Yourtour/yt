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
				xtype : 'xselectfield',
				itemId : 'type',
				padding:0,
				margin:'0 10 0 10',
				underline:false,
				options: [
					{text:'个人', value:'1'},
					{text:'分摊', value:'2'}
				]
			},

			{
				xtype:'xfield',
				itemId:'payment',
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

