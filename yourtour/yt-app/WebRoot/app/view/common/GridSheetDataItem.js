Ext.define('YourTour.view.common.GridSheetDataItem', {
	extend: 'YourTour.view.widget.grid.component.DataItem',
    xtype: 'GridSheetDataItem',
    config: {
        items: [
            {
                xtype:'xfield',
                itemId:'name',
                align:'left',
                underline:false
            }
        ]
    },

    updateRecord:function(record){
        var me = this;

        if(record){
            var name = me.down('#name');
            name.setText(record.get('name'));

            if(record.get('selected')){
                name.removeLabelCls('icon-unchecked');
                name.setLabelCls('icon-checked');
            }else{
                name.removeLabelCls('icon-checked');
                name.setLabelCls('icon-unchecked');
            }
        }
    }
});

