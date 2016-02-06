Ext.define('YourTour.view.widget.XDataItem', {
    extend: 'Ext.dataview.component.DataItem',
    config: {
        cls:'x-xdataitem'
    },

    initialize:function(){
        this.callParent(arguments);
    },

    updateRecord:function(record){
        var me = this, dataview = me.dataview || this.getDataview();
        var index = dataview.getStore().indexOf(record);
        if(index == 0){
            var item = me.getAt(0);
            if(item instanceof YourTour.view.widget.XSpacer){
                item.hide();
            }
        }

        if(record) {
            YourTour.util.Context.fillViewFields(me, record);
        }
    }
});

