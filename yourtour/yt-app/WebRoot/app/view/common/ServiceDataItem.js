Ext.define('YourTour.view.common.ServiceDataItem', {
    extend: 'Ext.dataview.component.DataItem',
    xtype: 'ServiceDataItem',
    requires:['Ext.Label','Ext.field.Select','Ext.Panel'],
    config: {
    	cls:'row underline nav_arrow',
    	items:[
			{
				xtype:'label',
				cls:'font-medium font-grey',
				itemId:'title',
			}   
    	]
    },
    
   	/**
   	 * 
   	 * @param {} record
   	 */
    updateRecord: function(record) {
       var me = this;
       if(record){
    	   var titleEl = me.down('#title');
    	   titleEl.setHtml(record.get('title'));
       }
    }   
});

