Ext.define('YourTour.view.widget.XDataView', {
    extend: 'Ext.DataView',
    xtype:'xdataview',
	config:{
		binding:'',
	},

	updateBinding:function(binding){
		this.binding = binding;
	},

	updateRecord:function(record){
		if(this.binding == ''){
			throw new Error("Binding config must be provided for xdataview");
		}

		var store = record[this.binding];
		this.setStore(store);
	}
});

