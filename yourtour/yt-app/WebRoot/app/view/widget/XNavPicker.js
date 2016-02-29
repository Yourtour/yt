Ext.define('YourTour.view.widget.XNavPicker', {
    extend: 'Ext.picker.Picker',
    config: {
    },

    /**
     * @private
     * Called when the done button has been tapped.
     */
    onDoneButtonTap: function() {
        var oldValue = this._value,
            newValue = this.getValue(true);

        this.fireEvent('change', this, newValue);

        this.hide();
        Ext.util.InputBlocker.unblockInputs();
    }
});

