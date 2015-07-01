Ext.define('YourTour.view.route.schedule.ScheduleListItem', {
    extend: 'Ext.dataview.component.DataItem',
    xtype: 'scheduleListItem',
    requires:['Ext.Label','Ext.field.Select'],
    config: {
    	layout : 'hbox',
    	
    	items:[{
    		xtype: 'label',
    		itemId:'dateIndex',
    	    html: 'My label!'
    	},{
    		xtype:'selectfield',
    		itemId:'placeField',
    		name:'placeField'
    	}]
    },
    
    updateRecord: function(record) {
       var me = this;
       
       console.log(record);
       if(record){
    	   var label = me.down('#dateIndex');
	 	   label.setHtml(record.get('date'));
	 	   
	 	   var placeField = me.down('#placeField');
	 	   placeField.setOptions(record.options);
       }
    }   
});

