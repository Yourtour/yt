Ext.define('YourTour.view.widget.XMultiLabel', {
    extend: 'Ext.Label',
    xtype: 'xmultilabel',
    config:{
    	tappable : false,
    	cls:'font-medium font-grey multilineinfo',
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

    setTappable:function(tappable){
    	this.tappable = tappable;
    },

	setHtml:function(html){
		this.callParent(arguments);

		if(html == '' || html == null){
			this.addCls('row');
		}
	}
});

