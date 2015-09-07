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
        Ext.define('Division', {
            extend: 'Ext.data.Model',
            fields: [{
                name: 'graphId', type: 'int'
            }, {
                name: 'code', typye: 'string'
            }, {
                name: 'text', type: 'string'
            }, {
                name: 'fullCode', type: 'string'
            }]
        });
        var store = Ext.create('Ext.data.TreeStore', {
            model: 'Division',

            idProperty: 'graphId',
            pageSize: 0,

            /*root: {
             expanded: true,
             text: 'root',
             children: [{
             graphId: 1,
             text: 'item 1',
             code: '1',
             fullCode: '1',
             leaf: true
             }, {
             text: 'item 2',
             code: '01',
             fullCode: '1-01',
             expanded: true,
             children: [{
             text: 'leaf 1',
             code: '001',
             fullCode: '1-01-001',
             leaf: true
             }, {
             text: 'leaf 2',
             leaf: true
             }, {
             text: 'leaf 1',
             leaf: true
             }, {
             text: 'leaf 2',
             leaf: true
             }, {
             text: 'leaf 1',
             leaf: true
             }, {
             text: 'leaf 2',
             leaf: true
             }, {
             text: 'leaf 1',
             leaf: true
             }, {
             text: 'leaf 2',
             leaf: true
             }, {
             text: 'leaf 1',
             leaf: true
             }, {
             text: 'leaf 2',
             leaf: true
             }, {
             text: 'leaf 1',
             leaf: true
             }, {
             text: 'leaf 2',
             leaf: true
             }]
             }]
             },*/

            proxy: {
                type: 'rest',
                format: 'json',
                api: {
                    create: 'http://localhost:8080/yt-web/rest/divisions/save',
                    read: 'http://localhost:8080/yt-web/rest/divisions/load',
                    update: 'http://localhost:8080/yt-web/rest/divisions/save',
                    destroy: 'http://localhost:8080/yt-web/rest/divisions/remove'
                },
                actionMethods: {
                    create: 'POST',
                    read: 'GET',
                    update: 'POST',
                    destroy: 'GET'
                },
                reader: {
                    type: 'json',
                    rootProperty: 'data'
                },
                writer: {
                    type: 'json',
                    writeAllFields: true
                }
            }
        });
        var me = this,
            tree = me.lookupReference('tree');
        tree.setStore(store);
    },

    onSelectionChange: function (model, selected) {
        var me = this,
            del = me.lookupReference('delete');
        if (selected.length <= 0) {
            del.setDisabled(true);
            return;
        }

        var form = me.lookupReference('form'),
            reset = me.lookupReference('reset'),
            edit = me.lookupReference('edit'),
            save = me.lookupReference('save'),
            code = me.lookupReference('code'),
            name = me.lookupReference('name');
        form.loadRecord(selected[0]);
        me.setRecord(selected[0]);
        reset.setDisabled(true);
        save.setDisabled(true);
        edit.setDisabled(false);
        del.setDisabled(false);
        code.setEditable(false);
        name.setEditable(false);
    },

    onAdd: function () {
        var me = this,
            form = me.lookupReference('form'),
            reset = me.lookupReference('reset'),
            edit = me.lookupReference('edit'),
            save = me.lookupReference('save'),
            tree = me.lookupReference('tree'),
            code = me.lookupReference('code'),
            name = me.lookupReference('name'),
            store = tree.getStore();
        fullCode = '';
        if (me.getRecord() != null) {
            fullCode = me.getRecord().fullCode;
        }
        var record = new Division({code: '', text: '', fullCode: fullCode});
        form.loadRecord(record);
        reset.setDisabled(false);
        edit.setDisabled(true);
        save.setDisabled(false);
        code.setEditable(true);
        name.setEditable(true);
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
        } else {
            Division.setProxy(store.getProxy());
            record.erase({
                success: function() {
                    console.log('delete success.');
                },
                failure: function() {
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
            save = me.lookupReference('save'),
            code = me.lookupReference('code'),
            name = me.lookupReference('name');
        edit.setDisabled(true);
        code.setEditable(true);
        name.setEditable(true);
        reset.setDisabled(false);
        save.setDisabled(false);
        me.setOperateType('edit');
    },

    onFormSave: function () {
        var me = this,
            form = me.lookupReference('form'),
            tree = me.lookupReference('tree'),
            store = tree.getStore();
        var record = form.getRecord();
        form.updateRecord(record);
        Division.setProxy(store.getProxy());
        record.save({
            success: function () {
                var type = me.getOperateType();
                if (type == 'add') {
                    store.update(record);
                }
                me.setOperateType('');
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