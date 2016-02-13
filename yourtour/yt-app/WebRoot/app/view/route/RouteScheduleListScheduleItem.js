Ext.define('YourTour.view.route.RouteScheduleListScheduleItem', {
    extend: 'Ext.Container',
    xtype: 'RouteScheduleListScheduleItem',
    config: {
        layout: 'vbox',
        cls: 'x-xschedule-item ',
        items: [
            {
                xtype: 'panel',
                layout: 'hbox',
                cls: 'row nav-arrow',
                padding: '0 10 0 10',
                items: [
                    {
                        xtype: 'label',
                        itemId: 'title'
                    },

                    {
                        xtype: 'xfield',
                        itemId: 'time',
                        flex: 1
                    }
                ]
            },

            {
                xtype: 'image',
                itemId: 'resImage',
                mode: 'background',
                height: 150,
                width: '100%'
            },

            {
                xtype: 'xmultifield',
                itemId: 'memo'
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
            var title = me.down('#title');
            title.setHtml(record.get('title'));

            var resType = record.get('resourceType');
            if(resType == 'SCENE') {
                title.addCls('x-xschedule-scene');
            }else if(resType == 'FOOD') {
                title.addCls('x-xschedule-food');
            }else if(resType == 'HOTEL') {
                title.addCls('x-xschedule-hotel');
            }else if(resType == 'TRAFFIC') {
                title.addCls('x-xschedule-traffic');
            }

            var time = me.down('#time');
            time.setText(record.get('startTime'));

            var resImage = me.down('#resImage');
            resImage.setSrc(YourTour.util.Context.getImageResource(record.get('imageUrl')));

            var memo = me.down('#memo');
            memo.setText(Ext.String.ellipsis(record.get('memo'), 70, false));
        }
    }
});

