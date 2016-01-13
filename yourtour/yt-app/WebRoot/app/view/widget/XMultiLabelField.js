Ext.define('YourTour.view.widget.XMultiLabelField', {
    extend: 'YourTour.view.widget.XLabelField',
    xtype: 'xmultilabelfield',
    config:{
    	cls:'underline font-medium font-grey'
    },

	initialize:function(){
		this.callParent(arguments);

		var value = this.down('#value');
		value.addCls('multilineinfo');
	},

	setValue:function(value){
		this.callParent(arguments);

		if(value =='' || value == null || value == undefined){
			this.addCls('row');
		}
	},

	setLabel:function(label){
		this.callParent(arguments);

		var labelEl = this.down('#label');
		labelEl.addCls('row');
	}
});