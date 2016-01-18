Ext.define('YourTour.view.widget.XTappable', {
    extend: 'Ext.Container',
    config: {
        tappable: null,
    },

    updateTappable: function (tappable) {
        var me = this;

        var indicator = 'nav-arrow';
        if(tappable instanceof Object){
            indicator = tappable.indicator;
        }

        if(indicator != 'none') {
            me.addCls(indicator);
        }

        me.element.on({
            scope: me,
            tap: function (e, t) {
                me.fireEvent('tap', me, e, t);
            }
        });
    }
});

