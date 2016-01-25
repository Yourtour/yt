Ext.define('YourTour.view.widget.XCalendar', {
    extend: 'Ext.Container',
    alternateClassName: 'YourTour.XCalendar',
    requires: ['YourTour.view.widget.XCalendarPanel', 'Ext.Carousel'],
    xtype: 'xcalendar',
    config: {
        baseCls: 'x-xcalendar',
        layout: 'vbox',
        allDays: [],
        items: [
            {
                xtype: 'label',
                itemId: 'title',
                cls: 'row font-large header '
            },
            {
                xtype: 'panel',
                layout: 'hbox',
                defaults: {
                    flex: 1
                },
                cls: 'underline week',
                items: [
                    {
                        xtype: 'label',
                        html: '日'
                    },

                    {
                        xtype: 'label',
                        html: '一'
                    },

                    {
                        xtype: 'label',
                        html: '二'
                    },

                    {
                        xtype: 'label',
                        html: '三'
                    },

                    {
                        xtype: 'label',
                        html: '四'
                    },
                    {
                        xtype: 'label',
                        html: '五'
                    },
                    {
                        xtype: 'label',
                        html: '六'
                    }

                ]
            },

            {
                xtype: 'carousel',
                itemId: 'calendar',
                flex: 1,
                indicator: false
            }
        ]
    },

    initialize: function () {
        var me = this;
        me.callParent(arguments);
        me.selectedItems = [];

        me.on(
            {
                scope: me,
                itemselect: me.onItemTap
            }
        );

        var calendar = me.down('#calendar');
        calendar.on(
            {
                scope: me,
                activeitemchange: me.onActiveItemChange
            }
        );
    },

    onActiveItemChange: function (carousel, value, oldValue, eOpts) {
        var me = this;

        if(value) {
            if (me.initialized) {
                var year = value.getYear(), month = value.getMonth();

                var activeIndex = carousel.getActiveIndex();
                if (activeIndex == 0) {
                    me.insertMonth(year, month);
                } else if (activeIndex == carousel.getItems().length - 1) {
                    me.appendMonth(year, month);
                }
            }

            var title = me.down('#title');
            if (title) {
                title.setHtml(value.getYear() + '年' + value.getMonth() + '月');
            }
        }
    },

    onItemTap: function (panel, item, date, active) {
        var me = this, selectedItems = me.selectedItems;

        var result =  me.fireEvent('itemtap', me, panel, item, date, active);
        if(result) {
            if (active) {
                selectedItems.push(item);
            } else {
                var index = selectedItems.indexOf(item);
                if (index > -1) {
                    selectedItems.splice(index, 1);
                }
            }
        }

        return result;
    },

    setDate: function (year, month) {
        var me = this, calendar = me.down('#calendar');

        me.insertMonth(year, month);
        calendar.add(Ext.create('YourTour.view.widget.XCalendarPanel', {year: year, month: month, calendar: me}));
        me.appendMonth(year, month);

        calendar.setActiveItem(1);

        me.initialized = true;
    },

    insertMonth: function (year, month) {
        var me = this, calendar = me.down('#calendar');
        if(calendar) {
            var dMonth = month != 1 ? month - 1 : 12;
            var dyear = dMonth == 12 ? year - 1 : year;
            calendar.insert(0, Ext.create('YourTour.view.widget.XCalendarPanel', {
                year: dyear,
                month: dMonth,
                calendar: me
            }));
        }
    },

    appendMonth: function (year, month) {
        var me = this, calendar = me.down('#calendar');

        if(calendar) {
            var dMonth = month != 12 ? month + 1 : 11;
            var dyear = dMonth == 1 ? year + 1 : year;
            calendar.add(Ext.create('YourTour.view.widget.XCalendarPanel', {year: dyear, month: dMonth, calendar: me}));
        }
    },

    getActiveItems: function () {
        return this.selectedItems;
    }
});