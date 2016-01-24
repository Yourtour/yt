Ext.define('YourTour.view.widget.XDateField', {
    extend: 'YourTour.view.widget.XField',
    xtype: 'xdatefield',
    config: {

    },

    setText: function (text) {
        var me = this;
        me.callParent(arguments);
        console.log(new Date(text).getTime());
        me.value = new Date(text).getTime();
        console.log(me.value);
    }
});

