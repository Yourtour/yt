Ext.define('YourTour.view.common.SimpleDataItem', {
	extend: 'YourTour.view.widget.grid.component.DataItem',
    xtype: 'SimpleDataItem',
    config: {
        items: [
            {
                xtype:'xfield',
                itemId:'name',
                align:'center',
                underline:false
            }
        ]
    },

    updateRecord:function(record){
        var me = this;

        if(record){
            me.down('#name').setText(record.get('name'));
        }
    }
});

