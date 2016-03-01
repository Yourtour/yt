Ext.define('YourTour.view.route.RouteScheduleListScheduleJoin', {
    extend: 'Ext.Container',
    xtype: 'RouteScheduleListScheduleJoin',
    requires: ['Ext.Label'],
    config: {
        layout: 'hbox',
        cls: 'x-xschedule-join row font-small font-grey nav-arrow',
        items: [
            {
                xtype: 'label',
                itemId: 'title',
                cls:'x-xtitle'
            },

            {
                xtype: 'label',
                itemId: 'memo',
                margin:'0 0 0 10'
            }
        ]
    },

    /**
     *
     * @param {} record
     */
    updateRecord: function (record) {
        var me = this;
        if (record) {
            var sTitle = record.get('title');
            var title = me.down('#title');
            title.setHtml(sTitle);

            var memo = me.down('#memo');
            memo.setHtml(record.get('memo'));
        }
    }
});

