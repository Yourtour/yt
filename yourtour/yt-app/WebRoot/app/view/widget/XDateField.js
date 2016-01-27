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
    }
});

