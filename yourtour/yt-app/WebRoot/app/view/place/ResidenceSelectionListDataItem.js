Ext.define('YourTour.view.place.ResidenceSelectionListDataItem', {
    extend: 'Ext.dataview.component.DataItem',
    requires: ['Ext.Panel', 'YourTour.view.widget.XField', 'YourTour.view.widget.XPanel'],
    xtype: 'ResidenceSelectionListDataItem',
    config: {
        layout: 'hbox',
        cls: 'underline row',
        items: [
            {
                itemId: 'name',
                xtype: 'xfield',
                underline:false
            }
        ]
    },

    updateRecord: function (record) {
        var me = this;
        if (record) {
            var name = me.down('#name');
            name.setText(record.get('name'));
        }
    }
});

