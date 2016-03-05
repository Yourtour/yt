/**
 *
 */
Ext.define('YourTour.view.widget.XField', {
    extend: 'YourTour.view.widget.XTappable',
    xtype: 'xfield',
    config: {
        baseCls: 'x-xfield',
        cls: 'x-xfield-default',
        layout: 'hbox',

        label: { //标签配置项
            text: null,
            cls: null,
            margin: '0 10 0 0',
            visible: false
        },

        field: { //Field配置项
            placeHolder: null,
            cls: null,
            ifNull: '',
            binding: null,
            editable: { //是否可编辑
                enable: false,
                icon: 'icon-edit'
            }
        },

        underline: null,
        padding: 10,

        items: [
            {
                xtype: 'label',
                itemId: 'label',
                hidden: true,
                style: 'text-align:left'
            },
            {
                xtype: 'container',
                itemId: 'value',
                cls: 'x-xfield-text',
                flex: 1
            }
        ],

        listeners:{ //监听器
            /**
             * 调用getValue时触发，用来对返回值进行预处理
             * @param field
             * @param value
             */
            getter: function (field, value) {
                field.setValue(value);

                return true;
            },

            /**
             * 调用setText时触发，用来对显示的文本进行预处理
             * @param field
             * @param text
             */
            setter: function (field, text) {
                field.fillText(text);

                return true;
            },

            /**
             * 自动绑定时(updateReocrd)触发，用来进行定制化
             * @param field
             * @param record
             */
            binder:function(field, record){
                var me = field,
                    config = me.getField() || me.field,
                    binding = config.binding,
                    name = (binding == null ? me.getItemId() : binding);

                var names = name.split('.');
                var len = names.length;

                var data = record;
                var store = null;
                for (var index = 0; index < len - 1; index++) {
                    eval('store = data.' + [names[index]] + '()');
                    data = store.first();
                }
                name = names[len - 1];
                value = data.get(name);
                me.setText(value);
            }
        }
    },

    initialize: function () {
        this.callParent(arguments);

        var me = this;
        if (me.underline == null) {
            me.addCls('underline');
        }

        me.modified = false;
        me.text = null;
        me.value = null;

        me.initLabel();

        me.initField();
    },

    /**
     * 初始化标签
     */
    initLabel: function () {
        var me = this,
            label = me.label || me.getLabel(),
            element = me.down('#label');

        if (label.visible) {
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
    initField: function () {
        var me = this,
            field = me.field || me.getField(),
            value = this.down('#value');

        var fieldCls = field.cls;
        if (fieldCls == null) {
            value.addCls('font-grey font-medium');
        } else {
            var index = fieldCls.indexOf('!important');
            if (index < 0) {
                value.addCls('font-grey font-medium ' + fieldCls);
            } else {
                value.addCls(fieldCls.substring(0, index - 1));
            }
        }

        var placeHolder = field.placeHolder;
        if (placeHolder != null) {
            value.setHtml(placeHolder);
        }

        //判断是否可编辑
        var editable = field.editable || field.getEditable();
        if (editable.enable) {
            me.on('tap', me.onEditTap, this);

            if (editable.icon != 'none') {
                value.addCls(editable.icon);
            }
        }
    },

    onEditTap: function () {
        var me = this, application = YourTour.util.Context.getApplication(), controller = application.getController('CommonMainCtrl');

        controller.editField(me);
    },

    updateUnderline: function (underline) {
        this.underline = underline;
    },

    setFieldCls: function (fieldCls) {
        var me = this,
            field = me.field || me.getField(),
            value = this.down('#value');

        Ext.apply(field, {cls: fieldCls});

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

        Ext.apply(label, {cls: labelCls});

        labelEl.addCls(labelCls);
    },

    removeLabelCls: function (labelCls) {
        var label = this.down('#label');
        label.removeCls(labelCls);
    },

    /**
     * Field配置项
     * @param config
     */
    updateField:function(config){
        var me = this,
            field = me.field || me.getField()

        Ext.apply(field, config);
    },

    /**
     * 更新Label配置项
     * @param config
     */
    updateLabel: function (config) {
        var me = this,
            label = me.label || me.getLabel();

        if (Ext.isObject(label)) {
            config.visible = true;
            Ext.apply(label, config);
        } else {
            Ext.apply(label, {text: config, visible: true});
        }
    },

    /**
     * 获取显示标签
     * @returns {*}
     */
    getFieldLabel: function () {
        var label = this.label || this.getLabel();

        return label.text;
    },

    /**
     * 获取显示文本
     * @returns {*}
     */
    getFieldText:function(){
        return this.text;
    },

    /**
     * 获取Placeholder
     * @returns {null|string|string}
     */
    getPlaceHolder: function () {
        var me = this, field = me.field || me.getField();

        return field.placeHolder;
    },

    /**
     * 获取Field的值
     * @returns {*}
     */
    getValue: function () {
        var me = this,
            value = this.value == null ? this.text : this.value;

        me.fireEvent('getter', this, value);

        return this.value;
    },

    /**
     * 设置Field值
     * @param value
     */
    setValue:function(value){
        this.value = value;
    },

    /**
     * 设置Field的显示文本
     * @param text
     */
    setText: function (text) {
        this.text = text;

        var me = this;
        me.fireEvent('setter', this, text);
    },

    /**
     * 修改
     * @param text
     */
    modifyText: function (text) {
        this.setText(text);

        this.modified = true;
    },

    getPair: function () {
        var me = this
        return me.pair;
    },

    setPair: function (pair) {
        var me = this;

        if (pair) {
            var pairs = pair.split('|');
            var values = '', texts = '', pArray;
            pairs.forEach(function (p) {
                if (values != '') {
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

    /**
     * 自动绑定
     * @param record
     */
    updateRecord: function (record) {
        var me = this;
        me.fireEvent('binder', this, record);
    },

    /**
     * 输出文本
     * @param text
     */
    fillText:function(text){
        text += '';
        var valueEl = this.down('#value');
        if (text == null || text == '') {
            valueEl.setHtml(this.ifNull);
        } else {
            valueEl.setHtml(text);
        }
    },

    isModified: function () {
        return this.modified;
    }
});

