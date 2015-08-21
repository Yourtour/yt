Ext.define('YourTour.view.widget.XPanel', {
    extend: 'Ext.Panel',
    xtype: 'xpanel',
    
    config:{
    	tappable:false
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
    }
});

