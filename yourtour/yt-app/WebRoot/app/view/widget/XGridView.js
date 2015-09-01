Ext.define('YourTour.view.widget.XGridView', {
    extend: 'Ext.Panel',
    xtype:'xgridview',
    
    config:{
    	cols: 3,
    	store:null
    },
    
    setCols:function(cols){
    	this.cols = cols;
    },
    
    updateStore: function(newStore, oldStore){ 
    	this.callParent(arguments);
    	
    	if(this.scrollable == null){
	    	var items = this.getViewItems();
	    	var height = 0;
	    	for(var index = 0; index < items.length; index++){
	    		height += Ext.get(items[index].getId()).getHeight();
	    	}
	    	
	    	this.setHeight(height);
    	}
    }
});

