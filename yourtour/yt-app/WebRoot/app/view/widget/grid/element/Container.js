Ext.define('YourTour.view.widget.grid.element.Container', {
    extend: 'Ext.dataview.element.Container',

    config:{
        cols:null,
        row : null
    },

    updateCols:function(cols){
        this.cols = cols;
    },

    updateListItem: function(record, item) {
        var me       = this,
            dataview = me.dataview,
            store    = dataview.getStore(),
            index    = store.indexOf(record),
            data     = dataview.prepareData(record.getData(true), index, record);

        data.xcount = store.getCount();
        data.xindex = typeof data.xindex === 'number' ? data.xindex : index;

        item.innerHTML = dataview.getItemTpl().apply(data);
    },

    addListItem: function(index, record) {
        var me         = this,
            dataview   = me.dataview,
            store      = dataview.getStore(),
            data       = dataview.prepareData(record.getData(true), index, record),
            element    = me.element,
            childNodes = element.dom.childNodes,
            ln         = childNodes.length,
            wrapElement;

        data.xcount = typeof data.xcount === 'number' ? data.xcount : store.getCount();
        data.xindex = typeof data.xindex === 'number' ? data.xindex : index;

        if(index % me.cols == 0){
            me.row = Ext.Element.create(this.getRowConfig());
            me.row.appendTo(element);
        }

        wrapElement = Ext.Element.create(this.getItemElementConfig(index, data));
        wrapElement.appendTo(me.row);

        /*
        if (!ln || index == ln) {
            wrapElement.appendTo(me.row);
        } else {
            wrapElement.insertBefore(childNodes[index]);
        }*/
    },

    getRowConfig:function(){
        var dataview = this.dataview, baseCls = dataview.getBaseCls();
        return {
            cls:baseCls + '-row'
        };
    },

    getItemElementConfig: function(index, data) {
        var config = this.callParent(arguments);
        return config;
    }
});
