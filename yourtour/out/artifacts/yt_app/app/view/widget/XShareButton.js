Ext.define('YourTour.view.widget.XShareButton', {
    extend: 'YourTour.view.widget.XButton',
    xtype: 'xsharebutton',
    config: {
        actionsheet:null
    },

    initialize:function(){
        this.callParent(arguments);

        this.on('tap', this.popup)
    },

    popup:function(){
        var me = this;

        if(me.actionsheet == null) {
            me.actionsheet = Ext.create('YourTour.view.widget.XShare');
        }

        me.actionsheet.show();
    },

    destroy: function() {
        this.callParent(arguments);
        Ext.destroy(this.actionsheet);
    }
});

