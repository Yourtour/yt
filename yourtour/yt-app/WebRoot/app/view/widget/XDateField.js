Ext.define('YourTour.view.widget.XDateField', {
    extend: 'YourTour.view.widget.XField',
    xtype: 'xdatefield',
    config: {

    },

    getValue:function(){
        var value = this.callParent(arguments);

        if(value && value != ''){
            return new Date(value).getTime();
        }else{
            return 0;
        }
    },

    setText:function(text){
        if(text && text != ''){
            if(Ext.isDate(text)){
                this.callParent([text]);
            }else if(Ext.isNumber(text)){
                this.callParent([Ext.Date.format(new Date(text),'Y/m/d')]);
            }else{
                this.callParent([text]);
            }
        }
    },

    updateValue:function(value){
        this.setValue(value);
    },

    setValue:function(value){
        this.setText(Ext.Date.format(value, 'Y-m-d'));
    }
});

