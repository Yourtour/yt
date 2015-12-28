Ext.define('YourTour.view.widget.XField', {
    extend: 'Ext.Label',
    xtype: 'xfield',
    config:{
    	cls:'field',
    	tappable:false,
		padding:'0 10 0 0',
		style:'text-align:right'
    },
    
    initialize : function(){
    	this.callParent(arguments);
    	
    	if(this.tappable){
	    	var me = this;
	    	me.element.on({
				scope : me,
				tap : function(e, t) {
					me.fireEvent('tap', me, e, t);
				}
			});
    	}
    },
    
    setTappable:function(tappable){
    	this.tappable = tappable;
    }
});

