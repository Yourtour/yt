Ext.define('YourTour.view.widget.XCarousel', {
    extend: 'Ext.Container',
    xtype: 'xcarousel',
    config: {
        cls: 'x-xcarousel',
        layout: 'vbox',
        items: []
    },

    beforeInitialize: function () {
        this.applyItems = this.applyInitialItems;
    },

    initialize: function () {
        delete this.applyItems;

        this.add(this.initialItems);
        delete this.initialItems;

        var me = this;

        me.carousel.on('activeitemchange', function (carousel, value, oldValue, eOpts) {
            var navBar = me.navBar;
            var activeIndex = carousel.getActiveIndex();
            if (!navBar || !navBar.getAt(activeIndex)) return;

            navBar.getItems().each(function (item) {
                item.removeCls('active');
            });
            navBar.getAt(activeIndex).addCls('active');

            me.fireEvent('activateitem', me, value);
        });
    },

    applyInitialItems: function (items) {
        var me = this;
        me.initialItems = items;

        me.navBar = me.add({
            xtype: 'panel',
            layout: 'hbox',
            cls: 'x-xcarousel-nav'
        });

        me.carousel = me.add({
            xtype: 'carousel',
            flex: 1,
            indicator:false
        });

        me.doAdd = me._doAdd;
        me.remove = me.doItemRemove;
        me.doInsert = me.doItemInsert;
    },

    _doAdd: function (item) {
        var me = this;

        var label = Ext.create('Ext.Label', {html: item.label, flex: 1, cls: 'x-xcarousel-nav-item'});
        if (item.getActive()) {
            label.addCls('active');
        }

        var index = me.navBar.getItems().length;
        label.element.on(
            {
                scope: me,
                tap: function (e, t) {
                    me.setActiveItem(index, item); //fireEvent('activeitemchange', index);
                }
            }
        )

        me.navBar.add(label);
        me.carousel.add(item);
    },

    doItemRemove: function (item, destroy) {
    },

    doItemInsert: function (index, item) {
    },

    setActiveIndex: function (activeIndex) {
        var me = this;
        var navBar = me.navBar;
        navBar.getAt(activeIndex).addCls('active');

        var carousel = me.carousel;
        carousel.setActiveItem(carousel.getAt(activeIndex));
    },

    getActiveIndex: function () {
        var me = this;

        var carousel = me.carousel;
        if(carousel) {
            return carousel.getActiveIndex();
        }
    },

    setActiveItem: function (index, item) {
        if (!item || item == null) return;

        var me = this;

        var navBar = me.navBar;
        navBar.getItems().each(function (item) {
            item.removeCls('active');
        });

        navBar.getAt(index).addCls('active');

        var carousel = me.carousel;
        carousel.setActiveItem(item);
    },
});