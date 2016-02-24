Ext.define('YourTour.view.widget.XToolbar', {
    extend: 'Ext.Toolbar',
    xtype: 'xtoolbar',
    config: {
		cls:'x-xtoolbar'
    },

    initialize:function(){
        this.callParent(arguments);

        var me = this, buttons = me.query("button");
        Ext.Array.forEach(buttons, function(button, index){
            button.element.on('tap', function(){
                me.fireEvent('activeitemchange', me, button);
            });
        });
    },
});

