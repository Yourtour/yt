Ext.define('YourTour.view.common.CarouselItem', {
	extend: 'Ext.Panel',
    requires:['Ext.Panel'],
    config: {
      	label:null,
		active:false,
		cacheData:null,
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

	updateData:function(data){
		this.cacheData = data;

		this.initializeItem();
	},

	initializeItem:function(){

	},

	getData:function(){
		return this.cacheData;
	}
});

