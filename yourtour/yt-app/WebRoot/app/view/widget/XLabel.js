Ext.define('YourTour.view.widget.XLabel', {
    extend: 'Ext.Label',
    xtype: 'xlabel',
    config:{
    	tappable : false,
    	cls:'label'
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

