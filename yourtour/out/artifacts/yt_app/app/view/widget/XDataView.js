Ext.define('YourTour.view.widget.XDataView', {
    extend: 'Ext.DataView',
    xtype: 'xdataview',
    config: {
        binding: '',
        direction:'vertical', // or  horizontal
        useComponents: true,
        itemHeight: 0,
        scrollable: {
            direction: 'vertical',
            indicators: false,
            directionLock: true,
            momentumEasing: {
                /*momentum: {
                 acceleration: 10,
                 friction: 0.9
                 },*/
                bounce: {
                    acceleration: 0.0001,
                    springTension: 0.9999
                }
                /*minVelocity: 5*/
            },
            outOfBoundRestrictFactor: 0
        }
    },

    initialize: function() {
        this.callParent();
        var me = this,
            container,
            triggerEvent = me.getTriggerEvent();

        me.on(me.getTriggerCtEvent(), me.onContainerTrigger, me);

        var containerLayout = (me.direction == 'horizontal'?'hbox':'vbox');
        container = me.container = this.add(new Ext.dataview[me.getUseComponents() ? 'component' : 'element'].Container({
            baseCls: this.getBaseCls(), layout:containerLayout
        }));
        container.dataview = me;

        if (triggerEvent) {
            me.on(triggerEvent, me.onItemTrigger, me);
        }

        container.on({
            itemtouchstart: 'onItemTouchStart',
            itemtouchend: 'onItemTouchEnd',
            itemtap: 'onItemTap',
            itemtaphold: 'onItemTapHold',
            itemtouchmove: 'onItemTouchMove',
            itemsingletap: 'onItemSingleTap',
            itemdoubletap: 'onItemDoubleTap',
            itemswipe: 'onItemSwipe',
            scope: me
        });

        if (me.getStore()) {
            if (me.isPainted()) {
                me.refresh();
            }
            else {
                me.on({
                    painted: 'refresh',
                    single: true
                });
            }
        }
    },

    updateDirection:function(direction){
        this.direction = direction;
    },

    updateItemHeight:function(itemHeight){
        this.itemHeight = itemHeight;
    },

    updateBinding: function (binding) {
        this.binding = binding;
    },

    isUpdatable: function () {
        return this.binding != '';
    },

    updateRecord: function (record) {
        if (this.binding == '') {
            console.warn("Binding config is not provided.");
        } else {
            var store = record[this.binding];
            this.setStore(store);
        }
    },

    refresh: function () {
        if (this.itemHeight > 0) {
            var allCount = this.getStore().getAllCount();
            if (allCount) {
                this.setHeight(this.itemHeight * allCount);
            }
        }
        this.callParent(arguments);
    }
});

