Ext.define('YourTour.view.widget.XActionSheet', {
    extend: 'Ext.ActionSheet',
    config: {
        items:[
            {
                xtype:'container',
                height:70,
                items:[
                    {
                        xtype: 'xbutton',
                        text: 'Close',
                        itemId: 'btnCheckin',
                    }
                ]
            }
        ]
    },

    show: function() {
        if (this.getParent() === undefined) {
            Ext.Viewport.add(this);
        }

        this.callParent(arguments);
        Ext.util.InputBlocker.blockInputs();
        this.hide();
    },

    destroy: function() {
        this.callParent();
        Ext.destroy(this);
    }
});

