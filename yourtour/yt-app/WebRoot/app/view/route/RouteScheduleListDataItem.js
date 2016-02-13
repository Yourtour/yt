Ext.define('YourTour.view.route.RouteScheduleListDataItem', {
    extend: 'Ext.dataview.component.DataItem',
    xtype: 'RouteScheduleListDataItem',
    requires: ['YourTour.view.route.RouteScheduleListScheduleJoin','YourTour.view.route.RouteScheduleListProvision', 'YourTour.view.route.RouteScheduleListSchedule', 'YourTour.view.route.RouteScheduleListScheduleItem', 'YourTour.view.route.RouteScheduleListProvisionItem'],
    config: {
        cls:'x-xschedule'
    },

    /**
     *
     * @param {} record
     */
    updateRecord: function (record) {
        var me = this;
        if (record) {
            var status = record.get('status');
            if(status == 'stale'){

            }else if(status == 'new'){
                record.set('status', 'stale');
                var type = record.get('type');
                if (type == 'Provision') {
                    me.add(Ext.create('YourTour.view.route.RouteScheduleListProvision', {record: record}))
                } else if (type == 'ProvisionItem') {
                    me.add(Ext.create('YourTour.view.route.RouteScheduleListProvisionItem', {record: record}))
                } else if (type == 'Schedule') {
                    me.add(Ext.create('YourTour.view.route.RouteScheduleListSchedule', {record: record}))
                } else if(type == 'ScheduleItem') {
                    me.add(Ext.create('YourTour.view.route.RouteScheduleListScheduleItem', {record: record}))
                } else if(type == 'ScheduleJoin') {
                    me.add(Ext.create('YourTour.view.route.RouteScheduleListScheduleJoin', {record: record}))
                }
            }

            if(record.get('viewhidden')){
                me.setHidden(true);
            }else{
                me.setHidden(false);
            }
        }
    }
});

