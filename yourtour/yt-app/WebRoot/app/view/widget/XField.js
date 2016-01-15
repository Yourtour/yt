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

    constructor: function(config){
        this.callParent(arguments);

        var underline = config.underline;
        if(underline == undefined || underline){
            this.addCls('underline');
        }
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

    updateRecord:function(record){
        var itemId = this.getItemId();
        var value = record.get(itemId);

        this.setText(record.get(itemId));
    }
});

