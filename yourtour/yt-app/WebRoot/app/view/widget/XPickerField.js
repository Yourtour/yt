Ext.define('YourTour.view.widget.XPickerField', {
    extend: 'Ext.field.Select',
    xtype: 'xpickerfield',
    config:{
        ui: 'select',

        /**
         * @cfg {Object/Ext.picker.Date} picker
         * An object that is used when creating the internal {@link Ext.picker.Date} component or a direct instance of {@link Ext.picker.Date}.
         * @accessor
         */
        picker: true,

        /**
         * @cfg {Boolean}
         * @hide
         * @accessor
         */
        clearIcon: false,

        /**
         * @cfg {Object/Date} value
         * Default value for the field and the internal {@link Ext.picker.Date} component. Accepts an object of 'year',
         * 'month' and 'day' values, all of which should be numbers, or a {@link Date}.
         *
         * Example: {year: 1989, day: 1, month: 5} = 1st May 1989 or new Date()
         * @accessor
         */

        /**
         * @cfg {Boolean} destroyPickerOnHide
         * Whether or not to destroy the picker widget on hide. This save memory if it's not used frequently,
         * but increase delay time on the next show due to re-instantiation.
         * @accessor
         */
        destroyPickerOnHide: false,

        /**
         * @cfg {Object}
         * @hide
         */
        component: {
            useMask: true
        }
    },

    initialize: function() {
        var me = this,
            component = me.getComponent();

        me.callParent();

        component.on({
            scope: me,
            masktap: 'onMaskTap'
        });


        component.doMaskTap = Ext.emptyFn;

        if (Ext.browser.is.AndroidStock2) {
            component.input.dom.disabled = true;
        }
    },

    syncEmptyCls: Ext.emptyFn,

    applyValue: function(value) {
        return value;
    },

    updateValue: function(newValue, oldValue) {
        var me     = this,
            picker = me._picker;

        if (picker && picker.isPicker) {
            picker.setValue(newValue);
        }

        // Ext.Date.format expects a Date
        if (newValue !== null) {
            me.getComponent().setValue(newValue);
        } else {
            me.getComponent().setValue('');
        }

        if (newValue !== oldValue) {
            me.fireEvent('change', me, newValue, oldValue);
        }
    },

    /**
     * Returns the {@link Date} value of this field.
     * If you wanted a formatted date use the {@link #getFormattedValue} method.
     * @return {Date} The date selected
     */
    getValue: function() {
        console.log(this._picker);

        if (this._picker) {
            return this._picker.getValue();
        }

        return this._value;
    },

    applyPicker: function(picker, pickerInstance) {
        if (pickerInstance && pickerInstance.isPicker) {
            picker = pickerInstance.setConfig(picker);
        }

        return picker;
    },

    getPicker: function() {
        console.log('getPicker');
        var picker = this._picker,
            value = this.getValue();

        if (picker && !picker.isPicker) {
            picker = this.factoryPicker(picker);
            if (value != null) {
                picker.setValue(value);
            }
        }

        picker.on({
            scope: this,
            change: 'onPickerChange',
            hide  : 'onPickerHide'
        });

        this._picker = picker;

        return picker;
    },

    factoryPicker:function(picker){
        //return Ext.factory(picker, Ext.picker.Date)
    },

    /**
     * @private
     * Listener to the tap event of the mask element. Shows the internal DatePicker component when the button has been tapped.
     */
    onMaskTap: function() {
        if (this.getDisabled()) {
            return false;
        }

        this.onFocus();

        return false;
    },

    /**
     * Called when the picker changes its value.
     * @param {Ext.picker.Date} picker The date picker.
     * @param {Object} value The new value from the date picker.
     * @private
     */
    onPickerChange: function(picker, value) {
        var me = this,
            oldValue = me.getValue();

        me.setValue(value);
        me.fireEvent('select', me, value);
        me.onChange(me, value, oldValue);
    },

    /**
     * Override this or change event will be fired twice. change event is fired in updateValue
     * for this field. TOUCH-2861
     */
    onChange: Ext.emptyFn,

    /**
     * Destroys the picker when it is hidden, if
     * {@link Ext.field.DatePicker#destroyPickerOnHide destroyPickerOnHide} is set to `true`.
     * @private
     */
    onPickerHide: function() {
        console.log('onPickerhide');
        var me     = this,
            picker = me.getPicker();

        if (me.getDestroyPickerOnHide() && picker) {
            picker.destroy();
            me._picker = me.getInitialConfig().picker || true;
        }
    },

    reset: function() {
        this.setValue(this.originalValue);
    },

    onFocus: function(e) {
        var component = this.getComponent();
        this.fireEvent('focus', this, e);

        if (Ext.os.is.Android4) {
            component.input.dom.focus();
        }
        component.input.dom.blur();

        if (this.getReadOnly()) {
            return false;
        }

        this.isFocused = true;

        this.getPicker().show();
    },

    // @private
    destroy: function() {
        var picker = this._picker;

        if (picker && picker.isPicker) {
            picker.destroy();
        }

        this.callParent(arguments);
    }
});

