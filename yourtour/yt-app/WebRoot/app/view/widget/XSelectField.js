Ext.define('YourTour.view.widget.XSelectField', {
    extend: 'YourTour.view.widget.XField',
    requires:['YourTour.view.widget.XGridSheet'],
    xtype: 'xselectfield',
    config: {
        /**
         * 数据源
         */
        store: null,

        selectable: {
            style: 'list', //grid or list
            multiselect: false,
            cols: 3,
            value: 'id',
            text: 'name'
        },

        view: null
    },

    updateSelectable:function(selectable){
        var me = this;

        Ext.apply(me.selectable || me.getSelectable(), selectable)
    },

    updateStore: function (store) {
        this.setStore(store);
    },

    setStore: function (store) {
        this.store = store;
    },

    onEditTap: function () {
        var me = this, selectable = me.selectable || me.getSelectable();
        if(me.view == null) {
            if (selectable.style == 'list') {
                me.view = me.getListView();
            } else {
                me.view = me.getGridView();
            }
        }

        me.view.show();
    },

    /**
     * 获取列表式选择视图
     * @returns {YourTour.view.widget.XPicker}
     */
    getListView: function () {
        var me = this, selectable = me.selectable || me.getSelectable(), options = [];

        if (me.store != null) {
            me.store.each(function (item) {
                options.push({text: item.get(selectable.text), value: item.get(selectable.value)});
            });
        }

        var picker = Ext.create('YourTour.view.widget.XPicker', {
            slots: [
                {
                    name: 'slot',
                    data: options
                }
            ]
        });

        picker.on('change', function (picker, newValue) {
            me.modifyText(newValue.slot);
            me.setValue(newValue.slot);
        });

        Ext.Viewport.add(picker);

        return picker;
    },

    /**
     * 获取Grid式选择视图
     */
    getGridView: function () {
        var me = this, selectable = me.selectable || me.getSelectable();

        var sheet = Ext.create('YourTour.view.widget.XGridSheet');
        sheet.setStore(me.store);

        sheet.on('done', function(){
            var value='', text='';

            me.store.each(function(item){
                if(item.get('selected')){
                    if(text != ''){
                        text += ',';
                        value += '|';
                    }

                    text += item.get('name');
                    value += item.get('id') + ',' + item.get('name');
                };
            });

            me.setValue(value);
            me.modifyText(text);
        })

        return sheet;
    },

    destroy: function() {
        this.callParent(arguments);
        Ext.destroy(this.view);
    }
});

