Ext.define('YourTour.view.widget.XDataItem', {
    extend: 'Ext.dataview.component.DataItem',
    config: {
        cls:'x-xdataitem'
    },

    updateRecord:function(record){
        var me = this;

        if(record) {
            YourTour.util.Context.fillViewFields(me, record);
        }
    }
});

