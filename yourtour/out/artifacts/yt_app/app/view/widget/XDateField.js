Ext.define('YourTour.view.widget.XDateField', {
    extend: 'YourTour.view.widget.XField',
    xtype: 'xdatefield',
    config: {
        single: true,
        seperator: '~',
        datatype:'long',

        listeners: {
            getter: function (field, value) {
                console.log('datefield');
                if(value == null || value == ''){
                    field.setValue(null);
                }else{
                    if(field.datatype == 'long') {
                        field.setValue(new Date(value).getTime());
                    }else{
                        field.setValue(new Date(value));
                    }
                }

                return true;
            },

            setter: function (field, text) {
                if(! (text == '' || text == null)){
                    if (Ext.isNumber(text)){
                        field.fillText(Ext.Date.format(new Date(text),'Y/m/d'));
                    }
                }

                return true;
            }
        }
    },

    onEditTap: function () {
        var me = this,
            application = YourTour.util.Context.getApplication(),
            controller = application.getController('CommonMainCtrl'),
            date = me.getValue();

        var dates = [];
        if (date != 0) {
            var sDate = date.split('~');
            Ext.Array.forEach(sDate, function (d) {
                dates.push(new Date(d));
            });
        }

        if (dates.length == 0) {
            dates.push(new Date());
        }

        var options = {
            single: me.single,
            title: me.getFieldLabel(),
            dates: dates
        };
        controller.showTimeSelectionView(options, function (startDate, endDate, duration) {
            if (endDate) {
                me.modifyText(startDate + ' ~ ' + endDate + ',' + duration);
            } else {
                me.modifyText(startDate);
            }

            Ext.ComponentManager.get('MainView').pop();
        });
    } ,

    updateSingle: function (single) {
        this.single = single;
    },

    updateSeperator: function (seperator) {
        this.seperator = seperator;
    }
});

