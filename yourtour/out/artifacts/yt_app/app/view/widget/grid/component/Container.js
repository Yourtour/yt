Ext.define('YourTour.view.widget.grid.component.Container', {
    extend: 'Ext.dataview.component.Container',
    requires: [
        'YourTour.view.widget.grid.component.DataItem'
    ],

    config:{
        cols:null,
        row : null,
        size : null
    },

    updateCols:function(cols){
        this.cols = cols;
    },

    updateSize:function(size){
        this.size = size;
    },

    doInitialize: function() {
        this.allItems = [];
        this.innerElement.on({
            touchstart: 'onItemTouchStart',
            touchend: 'onItemTouchEnd',
            tap: 'onItemTap',
            taphold: 'onItemTapHold',
            touchmove: 'onItemTouchMove',
            singletap: 'onItemSingleTap',
            doubletap: 'onItemDoubleTap',
            swipe: 'onItemSwipe',
            delegate: ' .' + Ext.baseCSSPrefix + 'xgrid-item',
            scope: this
        });
    },

    indexOf:function(item){
        return Ext.Array.indexOf(this.allItems, item);
    },

    moveItemsFromCache: function(records) {
        var me = this,
            dataview = me.dataview,
            baseCls = dataview.getBaseCls(),
            store = dataview.getStore(),
            ln = records.length < me.size ? records.length : me.size,
            xtype = dataview.getDefaultType(),
            itemConfig = dataview.getItemConfig(),
            itemCache = me.itemCache,
            cacheLn = itemCache.length,
            items = [],
            i, item, record;

        if (ln) {
            dataview.hideEmptyText();
        }

        for (i = 0; i < ln; i++) {
            records[i]._tmpIndex = store.indexOf(records[i]);
        }

        Ext.Array.sort(records, function(record1, record2) {
            return record1._tmpIndex > record2._tmpIndex ? 1 : -1;
        });

        for (i = 0; i < ln; i++) {
            record = records[i];
            if (cacheLn) {
                cacheLn--;
                item = itemCache.pop();
                this.updateListItem(record, item);
            }
            else {
                item = me.getDataItemConfig(xtype, record, itemConfig);
            }

            if(i % me.cols == 0){
                me.row = Ext.create('Ext.Panel',{layout:'hbox', cls: baseCls + '-row'});
                this.add(me.row);
            }

            item = me.row.insert(record._tmpIndex, item);

            this.allItems.push(item);

            delete record._tmpIndex;
        }

        if(me.row.getItems().length != me.cols) {
            for (i = 0; i < me.cols - ln % me.cols; i++) {
                item = me.getEmptyItemConfig();
                me.row.add(item);
            }
        }

        return items;
    },

    getDataItemConfig: function(xtype, record, itemConfig) {
        var dataview = this.dataview,
            dataItemConfig = {
                xtype: xtype,
                record: record,
                flex:1,
                itemCls: dataview.getItemCls(),
                defaults: itemConfig,
                dataview: dataview
            };
        return Ext.merge(dataItemConfig, itemConfig);
    },

    getEmptyItemConfig:function(){
        return {
            xtype:'panel',
            flex:1
        }
    }
});
