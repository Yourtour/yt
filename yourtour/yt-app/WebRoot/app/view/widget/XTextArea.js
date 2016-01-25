Ext.define('YourTour.view.widget.XTextArea', {
    extend: 'Ext.field.TextArea',
    xtype: 'xtextarea',
    config:{
        binding:null
    },

    updateBinding:function(binding){
        this.binding = binding;
    },

    updateRecord:function(record){
        var binding = this.getBinding();
        var name = (binding == null ? this.getItemId() : binding);

        this.setValue(record.get(name));
    }
});

