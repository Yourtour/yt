Ext.define('YourTour.view.widget.XCalendarItem', {
    extend: 'Ext.Label',
    xtype: 'xcalendaritem',
    config: {
        cls: 'day',
        enabled: true,
        date: null,
        calendarPanel: null,
        active: false
    },

    initialize: function () {
        var me = this;

        me.callParent(arguments);

        me.element.on({
            scope: me,
            'tap': me.onItemTap
        })
    },

    onItemTap: function () {
        var me = this;

        if (me.enabled) {
            me.active = !me.active;

            var result = me.calendarPanel.fireEvent('itemtap', me, me.getDate(), me.active);
            if (result != false) {
                if (me.active) {
                    me.addCls('active')
                } else {
                    me.removeCls('active');
                }
            }
        }
    },

    updateCalendarPanel: function (calendarPanel) {
        this.calendarPanel = calendarPanel;
    },

    updateEnabled: function (enabled) {
        this.enabled = enabled
    },

    updateDate: function (date) {
        var me = this, value = date.value, enabled = date.enabled, active = date.active;
        me.date = date;

        me.setHtml(value.split('/')[2]);
        if (enabled) {
            this.addCls('enabled');
        } else {
            this.addCls('disabled');
        }

        this.enabled = enabled;
        if (active) {
            this.addCls('active');
            this.onItemTap();
        }
    },

    getDate: function () {
        return this.date.value;
    },

    isActive: function () {
        return this.active == true;
    }
});

