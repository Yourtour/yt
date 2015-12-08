Ext.define('YourTour.view.widget.XPage', {
    extend: 'Ext.Panel',
    xtype: 'xpage',
    config:{
    	scrollable: {
    	    direction: 'vertical',
    	    indicators: false	
    	},
    	
    	//style:'background-color:#EDEDED',
    	
    	fullscreen: true
    },

	initialize : function(){
		this.callParent(arguments);
		
		var me = this;
		this.getScrollable().getScroller().on('scroll',function(scroller,x,y){
			if(y <= 0){
			}
		});
	},
});

