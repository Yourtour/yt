Ext.define('YourTour.view.widget.XSelectField', {
    extend: 'YourTour.view.widget.XField',
    xtype: 'xselectfield',
    config: {
        options:null
    },

    setText:function(text){
        var me = this;
        Ext.Array.forEach(me.options, function(item){
           if(item.value == text){
               text = item.text;
           }
        });

        me.callParent([text]);
    },

    updateOptions:function(options){
        this.options = options;
    }
});

