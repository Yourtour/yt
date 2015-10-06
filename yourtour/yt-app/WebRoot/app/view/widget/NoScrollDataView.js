Ext.define('YourTour.view.widget.NoScrollDataView', {
    extend: 'Ext.DataView',
    xtype: 'noscrollabledataview',
    
    doRefresh:function(me) {
    	this.callParent(me);
    	
    	console.log(this.getViewItems().length);
    }
});

