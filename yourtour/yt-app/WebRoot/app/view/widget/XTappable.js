Ext.define('YourTour.view.widget.XTappable', {
    extend: 'Ext.Container',
    xtype:'xtappablecontainer',
    config: {
        indicator: 'none'
    },

    initialize:function(){
        this.callParent(arguments);

        var me = this;
        me.element.on({
            scope: me,
            tap: function (e, t) {
                me.onTap(e, t);
            }
        });

        if(me.indicator != 'none'){
            me.addCls(me.indicator);
        }
    },

    onTap:function(e, t){
        var me = this;

        me.fireEvent('tap', me, e, t);
    },

    updateIndicator: function (indicator) {
        this.indicator = indicator;
    }
});

