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
                html:'Loading',
                cls:'x-indicator'
            },
            {
                xtype:'spacer',
                flex:1
            },
        ]
    }
});

