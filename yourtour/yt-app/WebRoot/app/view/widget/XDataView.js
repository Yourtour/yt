Ext.define('YourTour.view.widget.XDataView', {
    extend: 'Ext.DataView',
    xtype:'xdataview',
	config:{
		binding:'',
		useComponents: true,
		scrollable: {
			direction: 'vertical',
			indicators: false,
			directionLock: true,
			momentumEasing:  {
				/*momentum: {
				 acceleration: 10,
				 friction: 0.9
				 },*/
				bounce: {
					acceleration: 0.0001,
					springTension: 0.9999
				}
				/*minVelocity: 5*/
			},
			outOfBoundRestrictFactor: 0
		}
	},

	updateBinding:function(binding){
		this.binding = binding;
	},

	isUpdatable:function(){
		return this.binding != '';
	},

	updateRecord:function(record){
		if(this.binding == ''){
			console.warn("Binding config is not provided.");
		}else {
			var store = record[this.binding];
			this.setStore(store);
		}
	}
});

