Ext.define('YourTour.view.widget.XPage', {
    extend: 'Ext.Panel',
    xtype: 'xpage',
    config:{
    	scrollable: 'none',
		/*scrollable:{
    	    direction: 'vertical',
    	    indicators: false	
    	},*/
    	
    	fullscreen: true,

		attrs:null
    },

	initialize : function(){
		this.callParent(arguments);
		
		this.getScrollable().getScroller().on('scroll',function(scroller,x,y){
			if(y <= 0){
			}
		});
	},

	setAttrs:function(attrs){
		this.attrs = attrs;
	},

	getAttrs:function(){
		return this.attrs;
	}
});

