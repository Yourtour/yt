Ext.define('YourTour.view.route.schedule.ActivityListItem', {
    extend: 'Ext.dataview.component.DataItem',
    xtype: 'activityListItem',
    requires:['YourTour.view.route.schedule.SceneActivityListItem'],
    config: {
    	layout : 'vbox',
    },
    
    updateRecord: function(record) {
       var me = this;
       
       if(record){
    	   if(record.get('type') == '1'){
    		   var item = Ext.create('YourTour.view.route.schedule.SceneActivityListItem');
    		   me.add(item);
    	   }
	 	}
    }   
});

