Ext.define('YourTour.view.common.CarouselItem', {
	extend: 'Ext.Panel',
    requires:['Ext.Panel'],
    config: {
      	label:null,
		active:false,
		record:null,
    },
    
    updateLabel:function(label){
		this.label = label;
	},

	getLabel:function(){
		return this.label;
	},

	updateActive:function(active){
		this.active = active;
	},

	isActive:function(){
		return this.active;
	},

	updateRecord:function(record){
		this.setRecord(record);
	},

	setRecord:function(record){
		this.record = record;
	},

	getRecord:function(){
		return this.record;
	}
});

