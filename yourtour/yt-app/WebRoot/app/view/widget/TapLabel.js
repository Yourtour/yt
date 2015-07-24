Ext.define('YourTour.view.widget.TapPanel', {
    extend: 'Ext.Panel',
    xtype: 'tappanel',
    
    initialize : function(){
    	this.callParent(arguments);
    	
    	var me = this;
    	me.element.on({
			scope : me,
			tap : function(e, t) {
				me.fireEvent('tap', me, e, t);
			}
		});
    }
});

