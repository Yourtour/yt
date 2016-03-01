Ext.define('YourTour.view.widget.XPickerField', {
    extend: 'YourTour.view.widget.XField',
    requires:['YourTour.view.widget.XPicker'],
    xtype: 'xpickerfield',
    config: {
        picker:null,
        options:null
    },

    onEditTap: function () {
        var me = this;

        if(me.picker == null) {
            me.picker = Ext.create('YourTour.view.widget.XPicker', {
                slots: [
                    {
                        name: 'slot',
                        data: me.options
                    }
                ]
            });

            me.picker.on('change', function(picker, newValue){
                me.modifyText(newValue.slot);
                me.setValue(newValue.slot);
            });

            Ext.Viewport.add(me.picker);
        }

        me.picker.show();
    },

    updateOptions:function(options){
        this.setOptions(options);
    },

    setOptions:function(options){
        this.options = options;
    },

    getPickerValue:function(value){
        var result;
        Ext.Array.forEach(this.options, function(option){
            if(option.value == value){
                result = option;
            }
        })

        return result;
    },

    setText:function(text){
        var data = this.getPickerValue(text);
        if(data) {
            this.setValue(text);
            this.callParent([data.text]);
        }
    },

    destroy: function() {
        this.callParent(arguments);
        Ext.destroy(this.picker);
    }
});

