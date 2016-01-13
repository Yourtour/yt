Ext.define('YourTour.view.widget.XCarousel', {
    extend: 'Ext.Container',
    xtype: 'xcarousel',
    config: {
        cls: 'x-xcarousel',
        layout: 'vbox',
        items: []
    },

    initialize: function () {
        this.callParent();

        this.element.on({
            scope: this,
            activeitemchange: 'onActiveItemChange'
        });
    },

    beforeInitialize: function () {
        this.applyItems = this.applyInitialItems;
    },

    initialize: function () {
        delete this.applyItems;

        this.add(this.initialItems);
        delete this.initialItems;
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
            flex: 1
        });

        me.carousel.on('activeitemchange', function(carousel, value, oldValue, eOpts){
                var activeIndex = carousel.getActiveIndex();
                var navBar = me.navBar;

                if(! navBar ) return;

                navBar.getItems().each(function(item){
                    item.removeCls('active');
                });
                navBar.getAt(activeIndex).addCls('active');

                me.fireEvent('activeitemchange', carousel, value, oldValue, eOpts);
        });

        me.doAdd = me._doAdd;
        me.remove = me.doItemRemove;
        me.doInsert = me.doItemInsert;
    },

    /*add:function(item){
        this._doAdd(item);
    },*/

    _doAdd: function (item){
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
        this.activeIndex = activeIndex;

        var me = this;
        var navBar = me.navBar;
        navBar.getAt(activeIndex).addCls('active');

        var carousel = me.carousel;
        carousel.setActiveItem(carousel.getAt(activeIndex));
    },

    getActiveIndex: function () {
        return this.activeIndex;
    },

    setActiveItem: function (index, item) {
        if (!item || item == null) return;

        var me = this;

        var navBar = me.navBar;
        navBar.getItems().each(function(item){
           item.removeCls('active');
        });

        navBar.getAt(index).addCls('active');

        var carousel = me.carousel;
        carousel.setActiveItem(item);
    }
});

