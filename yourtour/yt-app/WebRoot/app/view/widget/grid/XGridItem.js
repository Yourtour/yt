Ext.define('YourTour.view.widget.grid.XGridItem', {
    extend: 'Ext.Panel',
    config:{
    	layout:'fit',
      	
    	model:null,
      	
    	gridview:null
    },
    
    initialize : function(){
    	var me = this;
    	
    	me.element.on({
			scope : me,
			tap : function(e, t) {
				me.gridview.fireEvent('itemtap', me.model);
			}
		});
    },
    
    applyGridview:function(gridview){
    	this.gridview = gridview;
    },
    
    applyModel:function(model){
    	this.model = model;
    }
});

