Ext.define('YourTour.view.widget.XField', {
    extend: 'YourTour.view.widget.XTappable',
    xtype: 'xfield',
    config: {
        baseCls:'x-xfield',
        cls:'x-xfield-default',
        layout: 'hbox',

        field:{ //Field配置项
            placeHolder:null,
            cls:null,
            ifNull:'',
            align:'right',
            binding:null,
            editable:{ //是否可编辑
                enable:false,
                icon:'icon-edit'
            }
        },

        label:{ //标签配置项
            text:null,
            cls:null,
            margin:'0 10 0 0',
            visible:false
        },

        value: null,
        text:null,

        underline: null,
        padding: '10 10 10 10',
        dataChange:null,

        items: [
            {
                xtype: 'label',
                itemId: 'label',
                hidden: true,
                style:'text-align:left'
            },
            {
                xtype: 'container',
                itemId: 'value',
                cls:'x-xfield-text',
                flex: 1
            }
        ]
    },

    constructor: function(config){
        this.callParent(arguments);
    },

    initialize:function(){
        this.callParent(arguments);

        var me = this;
        if(me.underline == null){
            me.addCls('underline');
        }

        var config = me.field || me.getField();
        if(! this.getDataChange()) {
            me.element.on({
                dataChange: function (field, record) {
                    var binding = config.binding;
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

        me.modified = false;

        me.initLabel();
        me.initField();
    },

    /**
     * 初始化标签
     */
    initLabel:function(){
        var me = this,
            label = me.label || me.getLabel();
            element = me.down('#label');

        if(label.visible) {
            element.setMargin(label.margin);

            if (label.text != null) {
                element.setHtml(label.text);
            }

            if (label.cls != null) {
                element.addCls(label.cls);
            }

            element.show();
        }
    },

    /**
     * 初始化Field
     */
    initField:function(){
        var me = this,
            field = me.field || me.getField(),
            value = this.down('#value');

        var fieldCls = field.cls;
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

        var placeHolder = field.placeHolder;
        if(placeHolder != null){
            value.setHtml(placeHolder);
        }

        value.setStyle('text-align:' + field.align);

        //判断是否可编辑
        var editable = field.editable || field.getEditable();
        if(editable.enable) {
            me.on('tap', me.onEditTap, this);

            if(editable.icon != 'none') {
                value.addCls(editable.icon);
            }
        }
    },

    onEditTap:function(){
        var me = this, application = YourTour.util.Context.getApplication(), controller = application.getController('CommonMainCtrl');

        controller.editField(me);
    },

    updateDataChange:function(dataChange){
        this.element.on({
            dataChange:dataChange
        });
    },

    updateUnderline: function (underline) {
        this.underline = underline;
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

    setFieldCls: function (fieldCls) {
        var me = this,
            field = me.field || me.getField(),
            value = this.down('#value');

        Ext.apply(field, {cls:fieldCls});

        value.addCls(fieldCls);
    },

    removeFieldCls: function (fieldCls) {
        var value = this.down('#value');
        value.removeCls(fieldCls);
    },

    setLabelCls: function (labelCls) {
        var me = this,
            label = me.label || me.getLabel(),
            labelEl = this.down('#label');

        Ext.apply(label, {cls:labelCls});

        labelEl.addCls(labelCls);
    },

    removeLabelCls: function (labelCls) {
        var label = this.down('#label');
        label.removeCls(labelCls);
    },

    updateLabel: function (config) {
        var me = this,
            label = me.label || me.getLabel(),
            labelEl = this.down('#label');

        if(Ext.isObject(label)){
            config.visible = true;
            Ext.apply(label, config);
        }else{
            Ext.apply(label, {text:config, visible:true});
        }
    },

    getLabelText:function(){
        var label = this.label || this.getLabel();

        return label.text;
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
            valueEl.setHtml(text);
        }
    },

    modifyText:function(text){
        this.setText(text);

        this.modified = true;
    },

    getText: function () {
        return this.text;
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
        if(Ext.isObject(editable)){
            Ext.apply(this.editable || this.getEditable(), editable);
        }else{
            Ext.apply(this.editable || this.getEditable(), {enable:editable});
        }
    },

    updateRecord:function(record){
        this.element.fireEvent('dataChange', this, record);
    },

    updatePlaceHolder:function(placeHolder){
        this.placeHolder = placeHolder;
    },

    isModified:function(){
        return this.modified;
    }
});

