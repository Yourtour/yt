Ext.define('YourTour.view.widget.XButton', {
    extend: 'Ext.Button',
    xtype: 'xbutton',
    config: {
        binding:null,
    	cls:'x-xbutton'
    },

    updateRecord:function(record){
        var text = this.getText();
        if(text == null || text == ''){
            var binding = this.binding;
            var name = binding == null ? this.getItemId():binding;
            this.setText(record.get(name));
        }
    }
});

