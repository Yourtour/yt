Ext.define('YourTour.view.route.RouteScheduleListProvision', {
    extend: 'Ext.Label',
    xtype: 'RouteScheduleListProvision',
    config: {
        cls: 'row underline'
    },

    /**
     *
     * @param {} record
     */
    updateRecord: function (record) {
        var me = this;
        if (record) {
            this.setHtml(record.get('title'));
        }
    }
});

