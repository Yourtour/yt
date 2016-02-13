Ext.define('YourTour.view.route.RouteScheduleListProvisionItem', {
    extend: 'Ext.Container',
    xtype: 'RouteScheduleListProvisionItem',
    requires: ['Ext.Label'],
    config: {
        layout: 'vbox',
        padding: '10 0 10 0',
        cls: 'prepareItem',

        items: [
            {
                xtype: 'xspacer'
            },

            {
                xtype: 'label',
                itemId: 'title',
                cls: 'icon-todo title',
                padding: '0 0 0 30'
            },

            {
                xtype: 'label',
                itemId: 'memo',
                cls: 'content',
                padding: '0 0 0 30'
            },

            {
                xtype: 'label',
                cls: 'underline',
                margin: '0 0 0 30',
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

