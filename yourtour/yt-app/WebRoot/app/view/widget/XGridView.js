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
    }
});

