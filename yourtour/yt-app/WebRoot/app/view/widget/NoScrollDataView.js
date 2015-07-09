Ext.define('YourTour.view.widget.NoScrollDataView', {
    extend: 'Ext.DataView',
    xtype: 'noscrollabledataview',
    config: {

    },
    
    doAdd:function(item){
    	this.callParent(item);
    	
		console.log(item);    	
    }
});

