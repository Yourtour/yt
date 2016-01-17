Ext.define('YourTour.view.widget.XField', {
    extend: 'Ext.Container',
    xtype: 'xfield',
    config: {
        baseCls:'x-xfield',
        label: null,
        icon: null,
        value: null,
        tappable: null,
        fieldCls: null,
        labelCls: null,
        underline: true,
        layout: 'hbox',
        padding: '10 20 10 10',
        ifNull:'',
        binding:null,
        dataChange:undefined,
        items: [
            {
                xtype: 'label',
                itemId: 'label',
                hidden: true
            },
            {
                xtype: 'label',
                itemId: 'value',
                cls: 'font-grey',
                flex: 1
            }
        ]
    },

    initialize:function(){
        this.callParent(arguments);

        var me = this;
        if(! this.getDataChange()) {
            me.element.on({
                dataChange: function (field, record) {
                    var binding = me.getBinding();
                    var name = (binding == null ? me.getItemId() : binding);
                    var names = name.split('.');
                    var len = names.length;

                    var data = record;
                    var store = null;
                    for (var index = 0; index < len - 1; index++) {
                        eval('store = data.' + [names[index]] + '()');
                        data = store.first();
                    }
                    name = names[len - 1];

                    me.setText(data.get(name));
                }
            });
        }
    },

    constructor: function(config){
        this.callParent(arguments);

        var underline = config.underline;
        if(underline == undefined || underline){
            this.addCls('underline');
        }
    },

    updateDataChange:function(dataChange){
        this.element.on({
            dataChange:dataChange
        });
    },

    updateUnderline: function (underline) {
        this.underline = underline;

        if (underline) {
            this.addCls('underline');
        } else {
            this.removeCls('x-xfield');
        }
    },

    updateIcon: function (icon) {
        this.setIcon(icon);
    },

    setIcon: function (icon) {
        var label = this.down('#label');
        label.show();
        label.addCls('icon');
        label.addCls(icon);
    },

    updateFieldCls: function (cls) {
        this.setFieldCls(cls);
    },

    setFieldCls: function (cls) {
        var value = this.down('#value');
        value.addCls(cls);
    },

    updateLabelCls: function (cls) {
        this.setLabelCls(cls);
    },

    setLabelCls: function (cls) {
        var label = this.down('#label');
        label.addCls(cls);
    },

    updateTappable: function (tappable) {
        var me = this;
        me.addCls('nav-arrow');
        me.element.on({
            scope: me,
            tap: function (e, t) {
                me.fireEvent('tap', me, e, t);
            }
        });
    },

    updateLabel: function (label) {
        this.setLabel(label);
    },

    setLabel: function (text) {
        var label = this.down('#label');
        label.setMargin('0 10 0 0');
        label.show();
        label.setHtml(text);
    },

    setValue: function (value) {
        this.value = value;
    },

    getValue: function () {
        return this.value == null ? this.getText() : this.value;
    },

    updateText: function (text) {
        this.setText(text);
    },

    setText: function (text) {
        var valueEl = this.down('#value');
        if(text == null || text == ''){
            valueEl.setHtml(this.ifNull);
        }else {
            valueEl.setHtml(text);
        }
    },

    getText: function () {
        return this.text;
    },

    updateIfNull:function(ifNull){
        this.ifNull = ifNull;
    },

    getPair:function(){
        var values = this.getValue().split(',');
        var texts =  this.getText().split(',');

        var pair = '';
        for(var index = 0; index < values.length; index++){
            if(pair != ''){
                pair = pair + '|';
            }

            pair = pair + values[index] + ',' + texts[index];
        }

        return pair;
    },

    setPair:function(pair){
        if(pair){
            var pairs = pair.split('|');
            var values = '', texts = '', pArray;
            pairs.forEach(function(p){
                if(values != ''){
                    values = values + ',';
                    texts = texts + ',';
                }

                pArray = p.split(',');
                values = values + pArray[0];
                texts = texts + pArray[1];
            });

            this.setValue(values);
            this.setText(texts);
        }
    },

    updateRecord:function(record){
        this.element.fireEvent('dataChange', this, record);
    }
});

