Ext.define('YourTour.view.widget.XLabel', {
    extend: 'Ext.Label',
    xtype: 'xlabel',
    config:{
    	cls:'x-xlabel',
		tappable : false,

		style:'background-color:white'
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

    updateTappable:function(tappable){
    	this.tappable = tappable;
    }
});

