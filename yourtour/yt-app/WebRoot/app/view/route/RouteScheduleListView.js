Ext.define('YourTour.view.route.RouteScheduleListView', {
    extend: 'YourTour.view.widget.XPage',
    requires: ['Ext.Panel', 'YourTour.view.route.RouteScheduleListDataItem', 'YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.XProcessing'],
    config: {
        id: 'RouteScheduleListView',
        layout: 'card',
        items: [
            {
                xtype: 'xheaderbar',
                items: [
                    {
                        xtype: "xbutton",
                        ui: "normal",
                        icon:'resources/icons/icon_header_edit.png',
                        itemId: 'edit',
                        align: 'right'
                    }
                ]
            },

            {
                xtype: 'xprocessing'
            },

            {
                xtype: 'xpagebody',
                layout: 'vbox',
                items: [
                    {
                        xtype: 'panel',
                        layout: 'vbox',
                        items: [
                            {
                                itemId: 'imageUrl',
                                xtype: 'image',
                                mode: 'tag'
                            },

                            {
                                itemId: 'name',
                                xtype: 'label',
                                style: 'background:grey;opacity:0.5; color:#fff; font-size:14px; font-weight:bold; width:100%; height:40px; line-height:40px; text-align:center',
                                docked: 'bottom',
                                bottom: 1
                            }
                        ]
                    },

                    {
                        itemId: 'RouteScheduleList',
                        xtype: 'dataview',
                        scrollable: null,
                        useComponents: true,
                        defaultType: 'RouteScheduleListDataItem'
                    }
                ]
            },

            {
                xtype: 'image',
                itemId: 'button',
                mode: 'tag',
                docked: 'bottom',
                src: 'resources/icons/icon_prepare.png',
                bottom: 30,
                left: 10,
                style: 'width:48px;height:48px;'
            }
        ]
    },

    initialize: function () {
        var me = this;

        me.callParent(arguments);

        var button = me.down('#button');
        button.schedule = true;
        button.on(
            {
                scope: button,
                tap: function () {
                    var scheduleList = me.down('#RouteScheduleList');
                    var store = scheduleList.getStore();

                    var type;
                    if (button.schedule) {
                        button.schedule = false;
                        button.setSrc('resources/icons/icon_schedule.png');

                        store.each(function (record) {
                            type = record.get('type');
                            if (type == 'Provision' || type == 'ProvisionItem') {
                                record.set('viewhidden', false);
                            } else {
                                record.set('viewhidden', true);
                            }
                        });
                    } else {
                        button.schedule = true;
                        button.setSrc('resources/icons/icon_prepare.png');

                        store.each(function (record) {
                            type = record.get('type');
                            if (type == 'Provision' || type == 'ProvisionItem') {
                                record.set('viewhidden', true);
                            } else {
                                record.set('viewhidden', false);
                            }
                        });
                    }
                }
            }
        )

    }
});

