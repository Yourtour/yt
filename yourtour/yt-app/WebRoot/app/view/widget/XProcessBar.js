Ext.define('YourTour.view.widget.XProcessBar', {
    extend: 'Ext.Container',
    xtype: 'xprocessbar',
    config: {
        cls:'x-xprocessbar text-align-center'
    },

    initialize:function(){
        this.callParent(arguments);
    },

    setPercent:function(percent){
        this.setHtml(Math.floor(percent * 100) + '%');
    }
});

