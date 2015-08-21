Ext.define('YourTour.view.widget.XField', {
    extend: 'Ext.Label',
    xtype: 'xfield',
    config:{
    	cls:'field',
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
    },
    
    setTappable:function(tappable){
    	console.log('tappable=' + tappable);
    	
    	this.tappable = tappable;
    }
});

