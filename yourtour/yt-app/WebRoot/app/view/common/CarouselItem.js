Ext.define('YourTour.view.common.CarouselItem', {
	extend: 'Ext.Panel',
    requires:['Ext.Panel'],
    xtype: 'CommentListItemView',
    config: {
      	label:null,
		active:false
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
	}
});

