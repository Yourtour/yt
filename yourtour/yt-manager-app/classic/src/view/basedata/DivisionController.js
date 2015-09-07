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
                name: 'code', typye: 'string'
            }, {
                name: 'text', type: 'string'
            }, {
                name: 'fullCode', type: 'string'
            }]
        });
        var store = Ext.create('Ext.data.TreeStore', {
            model: 'Division',

            //root: {
            //    text: 'root',
            //    children: [{"code":"item 1","text":"text 1","fullCode":"item 1","expanded":true,"children":[],"id":0,"leaf":true},{"code":"item 2","text":"text 2","fullCode":"item 2","expanded":true,"children":[{"code":"child 1","text":"child text 1","fullCode":"item 2-child 1","expanded":true,"children":[],"id":9,"leaf":true},{"code":"child 2","text":"child text 2","fullCode":"item 2-child 2","expanded":true,"children":[],"id":10,"leaf":true},{"code":"child 3","text":"child text 3","fullCode":"item 2-child 3","expanded":true,"children":[],"id":11,"leaf":true},{"code":"child 4","text":"child text 4","fullCode":"item 2-child 4","expanded":true,"children":[],"id":12,"leaf":true},{"code":"child 5","text":"child text 5","fullCode":"item 2-child 5","expanded":true,"children":[],"id":13,"leaf":true},{"code":"child 6","text":"child text 6","fullCode":"item 2-child 6","expanded":true,"children":[],"id":14,"leaf":true},{"code":"child 7","text":"child text 7","fullCode":"item 2-child 7","expanded":true,"children":[],"id":15,"leaf":true},{"code":"child 8","text":"child text 8","fullCode":"item 2-child 8","expanded":true,"children":[],"id":16,"leaf":true},{"code":"child 9","text":"child text 9","fullCode":"item 2-child 9","expanded":true,"children":[],"id":17,"leaf":true}],"id":1,"leaf":false},{"code":"item 3","text":"text 3","fullCode":"item 3","expanded":true,"children":[],"id":2,"leaf":true},{"code":"item 4","text":"text 4","fullCode":"item 4","expanded":true,"children":[],"id":3,"leaf":true},{"code":"item 5","text":"text 5","fullCode":"item 5","expanded":true,"children":[{"code":"child 1","text":"child text 1","fullCode":"item 5-child 1","expanded":true,"children":[],"id":18,"leaf":true},{"code":"child 2","text":"child text 2","fullCode":"item 5-child 2","expanded":true,"children":[],"id":19,"leaf":true},{"code":"child 3","text":"child text 3","fullCode":"item 5-child 3","expanded":true,"children":[],"id":20,"leaf":true},{"code":"child 4","text":"child text 4","fullCode":"item 5-child 4","expanded":true,"children":[],"id":21,"leaf":true},{"code":"child 5","text":"child text 5","fullCode":"item 5-child 5","expanded":true,"children":[],"id":22,"leaf":true},{"code":"child 6","text":"child text 6","fullCode":"item 5-child 6","expanded":true,"children":[],"id":23,"leaf":true},{"code":"child 7","text":"child text 7","fullCode":"item 5-child 7","expanded":true,"children":[],"id":24,"leaf":true},{"code":"child 8","text":"child text 8","fullCode":"item 5-child 8","expanded":true,"children":[],"id":25,"leaf":true},{"code":"child 9","text":"child text 9","fullCode":"item 5-child 9","expanded":true,"children":[],"id":26,"leaf":true}],"id":4,"leaf":false},{"code":"item 6","text":"text 6","fullCode":"item 6","expanded":true,"children":[],"id":5,"leaf":true},{"code":"item 7","text":"text 7","fullCode":"item 7","expanded":true,"children":[],"id":6,"leaf":true},{"code":"item 8","text":"text 8","fullCode":"item 8","expanded":true,"children":[],"id":7,"leaf":true},{"code":"item 9","text":"text 9","fullCode":"item 9","expanded":true,"children":[],"id":8,"leaf":true}]
            //},

            idProperty: 'id',
            pageSize: 0,

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
            record.set('id', record.getData().graphId);
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
        record.set('id', record.getData().graphId);
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