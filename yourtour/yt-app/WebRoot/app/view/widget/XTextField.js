Ext.define('YourTour.view.widget.XTextField', {
    extend: 'Ext.field.Text',
    xtype: 'xtextfield',
    config:{
        binding:null,
        height:44
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

