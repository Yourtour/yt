Ext.define('YourTour.view.route.RouteScheduleListSchedule', {
    extend: 'Ext.Container',
    xtype: 'RouteScheduleListSchedule',
    requires: ['Ext.Label'],
    config: {
        layout: 'vbox',
        cls: 'x-xschedule',
        items: [
            {
                xtype: 'label',
                itemId: 'title',
                cls: 'row x-xtitle text-align-center font-medium'
            },

            {
                xtype: 'xfield',
                itemId: 'lineName'
            },

            {
                xtype:'panel',
                layout:'vbox',
                items:[
                    {
                        xtype: 'xmultifield',
                        itemId: 'memo'
                    },
                    {
                        xtype:'xspacer'
                    }
                ]
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
            var sTitle = record.get('title'), places = record.get('places');
            var title = me.down('#title');
            title.setHtml(sTitle + '  ' + places);

            var memo = me.down('#memo');
            memo.setText(record.get('memo'));
        }
    }
});

