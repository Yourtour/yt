Ext.define('YourTour.view.widget.XProcessing', {
    extend: 'Ext.Container',
    xtype: 'xprocessing',
    config: {
        layout:'vbox',
        itemId:'processing',
        cls:'x-xprocessing',
        items:[
            {
                xtype:'spacer',
                flex:1
            },
            {
                xtype:'label',
                itemId:'process'
            },
            {
                xtype:'spacer',
                flex:1
            }
        ]
    },

    initialize:function(){
        this.callParent(arguments);

        var me = this, process = me.down('#process');
        process.setHtml('<div class="spinner"><i></i></div>');
    }
});

