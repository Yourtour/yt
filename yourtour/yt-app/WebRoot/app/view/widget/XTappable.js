Ext.define('YourTour.view.widget.XTappable', {
    extend: 'Ext.Container',
    config: {
        indicator: 'none'
    },

    initialize:function(){
        this.callParent(arguments);

        var me = this;
        me.element.on({
            scope: me,
            tap: function (e, t) {
                me.fireEvent('tap', me, e, t);
            }
        });

        if(me.indicator != 'none'){
            me.addCls(me.indicator);
        }
    },

    updateIndicator: function (indicator) {
        this.indicator = indicator;
    }
});

