Ext.define('YourTour.view.route.schedule.ScheduleDataView', {
    extend: 'Ext.dataview.DataView',
    xtype: 'ScheduleDataView',
    config: {
    	readonly:null
    },
    
    setReadonly:function(readonly){
    	this.readonly = readonly;
    }
});

