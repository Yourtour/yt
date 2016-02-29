Ext.define('YourTour.view.widget.XNavSelect', {
    extend: 'Ext.field.Select',
    xtype: 'xnavselect',
    requires:['YourTour.view.widget.XNavPicker'],
    config: {
    },

    getPhonePicker:function(){
        var config = this.getDefaultPhonePickerConfig();

        if (!this.picker) {
            this.picker = Ext.create('YourTour.view.widget.XNavPicker', Ext.apply({
                slots: [
                    {
                        align: this.getPickerSlotAlign(),
                        name: this.getName(),
                        valueField: this.getValueField(),
                        displayField: this.getDisplayField(),
                        value: this.getValue(),
                        store: this.getStore()
                    }
                ],
                listeners: {
                    change: this.onPickerChange,
                    scope: this
                }
            }, config));
        }

        return this.picker;
    },

    // @private
    onPickerChange: function(picker, value) {
        var me = this,
            newValue = value[me.getName()],
            store = me.getStore(),
            index = store.find(me.getValueField(), newValue, null, null, null, true),
            record = store.getAt(index);

        me.setValue(record);

        me.fireEvent('change', me, me.getValue());
    },

    onChange: function(component, newValue, oldValue) {
        //override parent function define
    },
});

