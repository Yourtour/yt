Ext.define('YourTour.view.route.RouteScheduleDataView', {
    extend: 'YourTour.view.widget.XDataView',
    xtype: 'RouteScheduleDataView',
    config: {
    	readonly:null
    },
    
    setReadonly:function(readonly){
    	this.readonly = readonly;
    }
});

