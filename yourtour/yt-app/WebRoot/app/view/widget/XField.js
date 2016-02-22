Ext.define('YourTour.view.widget.XField', {
    extend: 'YourTour.view.widget.XTappable',
    xtype: 'xfield',
    config: {
        baseCls:'x-xfield',
        cls:'x-xfield-default',
        layout: 'hbox',
        align:'left',
        label: null,
        icon: null,
        value: null,
        text:null,
        fieldCls: null,
        labelCls: null,
        underline: null,
        padding: '10 10 10 10',
        ifNull:'',
        placeHolder:null,
        binding:null,
        dataChange:null,
        editable:false,
        items: [
            {
                xtype: 'label',
                itemId: 'label',
                hidden: true,
                style:'text-align:left'
            },
            {
                xtype: 'label',
                itemId: 'value',
                cls:'x-xfield-text',
                flex: 1
            }
        ]
    },

    initialize:function(){
        this.callParent(arguments);

        var me = this, fieldCls = me.fieldCls;
        var value = this.down('#value');
        if(fieldCls == null){
            value.addCls('font-grey font-medium');
        }else{
            var index = fieldCls.indexOf('!important');
            if(index < 0){
                value.addCls('font-grey font-medium ' + fieldCls);
            }else{
                value.addCls(fieldCls.substring(0, index - 1));
            }
        }

        if(me.underline == null){
            me.addCls('underline');
        }

        if(me.placeHolder != null){
            value.setHtml(me.placeHolder);
        }

        if(me.editable){
            me.on('tap', function(){
                var application = YourTour.util.Context.getApplication(), controller = application.getController('CommonMainCtrl');

                controller.editField(me);
            })
        }

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

        var value = this.down('#value');
        value.setStyle('text-align:' + this.align);
    },

    updateBinding:function(binding){
        this.binding = binding;
    },

    updateAlign:function(align){
        this.align = align;
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

    updateFieldCls: function (fieldCls) {
        this.setFieldCls(fieldCls);
    },

    setFieldCls: function (fieldCls) {
        this.fieldCls = fieldCls;
    },

    updateLabelCls: function (labelCls) {
        this.setLabelCls(labelCls);
    },

    setLabelCls: function (labelCls) {
        this.labelCls = labelCls;

        var label = this.down('#label');
        label.show();
        label.addCls(labelCls);
    },

    updateLabel: function (label) {
        this.label = label;
        var labelEl = this.down('#label');
        labelEl.setMargin('0 10 0 0');
        labelEl.show();
        labelEl.setHtml(label);
    },

    setValue: function (value) {
        this.value = value;
    },

    getValue: function () {
        var value = this.value;
        return value == null || value == ''? this.getText() : value;
    },

    updateText: function (text) {
        this.setText(text);
    },

    setText: function (text) {
        this.text = text;

        text += '';

        var valueEl = this.down('#value');
        if(text == null || text == ''){
            valueEl.setHtml(this.ifNull);
        }else {
            if(this.editable)  text = text + '<div class="icon-edit"></div>';

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
        var me = this;
        return me.pair;
    },

    setPair:function(pair){
        var me = this;

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

            me.pair = pair;
        }
    },

    updateEditable:function(editable){
        this.editable = editable;
    },

    updateRecord:function(record){
        this.element.fireEvent('dataChange', this, record);
    },

    updatePlaceHolder:function(placeHolder){
        this.placeHolder = placeHolder;
    },

    getPlaceHolder:function(){
        return this.placeHolder;
    }
});

