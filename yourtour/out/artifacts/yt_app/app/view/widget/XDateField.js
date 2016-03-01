Ext.define('YourTour.view.widget.XDateField', {
    extend: 'YourTour.view.widget.XField',
    xtype: 'xdatefield',
    config: {
        editable: true,
        single: true
    },

    onEditTap: function () {
        var me = this, application = YourTour.util.Context.getApplication(), controller = application.getController('CommonMainCtrl'), date = me.getText();

        var options = {
            single: me.single,
            title: me.getLabel(),
            date: date == null || date == '' ? new Date() : new Date(date)
        };
        controller.showTimeSelectionView(options, function (startDate, endDate, duration) {
            if (endDate) {
                me.modifyText(startDate + ' ~ ' + endDate);
            } else {
                me.modifyText(startDate);
            }

            Ext.ComponentManager.get('MainView').pop();
        });
    },

    updateSingle: function (single) {
        this.single = single;
    },

    getValue: function () {
        var value = this.callParent(arguments);

        if (value && value != '') {
            return new Date(value).getTime();
        } else {
            return 0;
        }
    },

    setText: function (text) {
        if (text && text != '') {
            if (Ext.isNumber(text)) {
                this.callParent([Ext.Date.format(new Date(text), 'Y/m/d')]);
            } else {
                this.callParent([text]);
            }
        }
    },

    updateValue: function (value) {
        this.setValue(value);
    },

    setValue: function (value) {
        this.setText(Ext.Date.format(value, 'Y-m-d'));
    }
});

