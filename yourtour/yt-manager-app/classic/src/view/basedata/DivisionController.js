/**
 * Created by john on 15-9-6.
 */
Ext.define('yt_manager_app.view.basedata.DivisionController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.division',

    config: {
        record: null,
        operateType: ''
    },

    init: function () {
        this.onRefresh();
    },

    onRefresh: function () {
        var me = this,
            tree = me.lookupReference('tree'),
            store = tree.getStore();
        store.load();
    },

    onSelectionChange: function (model, selected) {
        var me = this,
            parent = me.lookupReference('parent'),
            del = me.lookupReference('delete');
        parent.setHidden(true);
        if (selected.length <= 0) {
            del.setDisabled(true);
            this.setRecord(null);
            return;
        }

        var form = me.lookupReference('form'),
            reset = me.lookupReference('reset'),
            edit = me.lookupReference('edit'),
            save = me.lookupReference('save');
        form.loadRecord(selected[0]);
        me.setRecord(selected[0]);
        reset.setDisabled(true);
        save.setDisabled(true);
        edit.setDisabled(false);
        del.setDisabled(false);
        me.setEditable(false);
    },

    setEditable: function (editable) {
        var me = this,
            code = me.lookupReference('code'),
            shorter = me.lookupReference('shorter'),
            name = me.lookupReference('name'),
            memo = me.lookupReference('memo');
        code.setEditable(editable);
        shorter.setEditable(editable);
        name.setEditable(editable);
        memo.setEditable(editable);
    },

    onAdd: function () {
        var me = this,
            form = me.lookupReference('form'),
            parent = me.lookupReference('parent'),
            reset = me.lookupReference('reset'),
            edit = me.lookupReference('edit'),
            save = me.lookupReference('save'),
            tree = me.lookupReference('tree'),
            store = tree.getStore();
        if (me.getRecord() != null) {
            parent.setValue(me.getRecord().get('code'));
        } else {
            parent.setValue('');
        }
        parent.setHidden(false);

        var record = new yt_manager_app.model.basedata.Division({code: '', text: '', memo: ''});
        form.loadRecord(record);
        reset.setDisabled(false);
        edit.setDisabled(true);
        save.setDisabled(false);
        me.setEditable(true);
        me.setOperateType('add');
    },

    onDelete: function () {
        var me = this,
            tree = me.lookupReference('tree'),
            store = tree.getStore(),
            record = me.getRecord();

        if (record == null) {
            Ext.MessageBox.alert('提示', '在删除前必须要选择一行数据。');
            return;
        } else if (!record.get('leaf')) {
            Ext.MessageBox.alert('提示', '不能直接删除包含下级行政区划的节点。');
            return;
        } else {
            yt_manager_app.model.basedata.Division.setProxy(store.getProxy());
            record.set('id', record.getData().id);
            record.erase({
                success: function () {
                    console.log('delete success.');
                },
                failure: function () {
                    console.log('delete failure.');
                }
            });
        }
        // TODO delete the selected division
    },

    onFormReset: function () {
        var me = this,
            form = me.lookupReference('form');
        form.loadRecord(me.getRecord());
    },

    onFormEdit: function () {
        var me = this,
            edit = me.lookupReference('edit'),
            reset = me.lookupReference('reset'),
            save = me.lookupReference('save');
        edit.setDisabled(true);
        me.setEditable(true);
        reset.setDisabled(false);
        save.setDisabled(false);
        me.setOperateType('edit');
    },

    onFormSave: function () {
        var me = this,
            parent = me.lookupReference('parent'),
            form = me.lookupReference('form'),
            tree = me.lookupReference('tree'),
            store = tree.getStore();
        var record = form.getRecord(),
            type = me.getOperateType();
        form.updateRecord(record);
        record.set('id', record.getData().id);
        if (me.getRecord() != null && type == 'add') {
            // 新增并选择了节点，则设置父节点ID
            record.set('parentId', me.getRecord().get('id'));
        }
        if (type == 'add') {
            record.set('id', null);
        } else {
            record.set('id', me.getRecord().get('id'));
        }
        if (record.get('parentId') == 'root') {
            record.set('parentId', -1);
        }
        yt_manager_app.model.basedata.Division.setProxy(store.getProxy());
        record.save({
            success: function () {
                if (type == 'add') {
                    store.insert(record);
                }
                me.setEditable(false);
                me.setOperateType('');
                parent.setHidden(true);
                // TODO
                console.log('save the form data.');
            },
            failure: function () {
                Ext.MessageBox.alert('错误', '保存表单数据失败。');
                // TODO
                console.log('save failure.');
            }
        });
    }
});