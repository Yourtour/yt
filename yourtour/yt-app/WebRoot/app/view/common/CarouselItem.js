Ext.define('YourTour.view.common.CarouselItem', {
	extend: 'Ext.Panel',
    requires:['Ext.Panel'],
    config: {
      	label:null,
		active:false,
		cacheData:null,

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

