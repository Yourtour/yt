Ext.define('YourTour.view.route.schedule.ScheduleDataView', {
    extend: 'YourTour.view.widget.XDataView',
    xtype: 'ScheduleDataView',
    config: {
    	readonly:null
    },
    
    setReadonly:function(readonly){
    	this.readonly = readonly;
    }
});

