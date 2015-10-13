Ext.define('YourTour.view.widget.XSelecion', {
    extend: 'Ext.Panel',
    config: {
    	model:null,
    	selection:null,
    	style:'border:1px solid silver;',
    	padding:5,
    	items:[
    	    {
    	    	xtype:'xfield',
    	    	itemId:'selectionItem'
    	    }
    	]	
    },
    
    initialize : function(){
    	var me = this;
    	
    	me.element.on({
			scope : me,
			tap : function(e, t) {
				me.selection.fireEvent('itemtap', me, me.model);
			}
		});
    },

    applySelection:function(selection){
    	this.selection = selection;
    },
    
	applyModel:function(model){
		var me = this;
		me.model = model;
		
		if(model){
			var itemEl = me.down('#selectionItem');
			itemEl.setHtml(model.get('name'));
		}
	}
});

