Ext.define('YourTour.view.widget.XGridView', {
    extend: 'Ext.DataView',
    xtype: 'xgridview',
    config: {},

    doRefresh: function(me){
        var items = me.getViewItems();
        Ext.Array.forEach(items, function(item){
            console.log(item);
        })

        this.callParent(arguments);
    }
/*
    doItemLayoutAdd: function (item, index) {
        this.callParent(arguments);
    }
*/

});

