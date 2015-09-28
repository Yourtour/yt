/**
 * Created by john on 15-8-21.
 */
Ext.define('yt_manager_app.view.widget.GeneralCRUDController', {
    extend: 'Ext.app.ViewController',

    config: {
        popupWindowName: 'popupWindow',
        formWindowName: 'formWindow',
        name: 'NA',
        currentGrid: null,
        data: null,
        operate: null
    },

    afterUpdateRecord: function(record) {
        // Ext.Boot.debug('这是父类方法。。');
        // Do nothing
        return;
    },

    beforeLoadRecord: function(record, editable) {
        // Do nothing
        return;
    },

    afterLoadRecord: function(record, editable) {
        // Do nothing
        return;
    },

    dowithRecord: function (data) {
        // Do nothing
        return data;
    },

    getSelectedRecords: function () {
        var me = this,
            grid = me.getCurrentGrid();
        if (grid == null) {
            return null;
        }
        var model = grid.getSelectionModel();
        return model.getSelection();
    },

    showPopWindow: function (title, data, editable) {
        var me = this,
            win = me.lookupReference(me.getPopupWindowName());
        if (!win) {
            win = me.createNewWindow();
            me.getView().add(win);
        }
        win.setTitle(title);

        var formWindow = me.lookupReference(me.getFormWindowName()),
            baseInfo = me.lookupReference('baseInfo'),
            detailInfo = me.lookupReference('detailInfo'),
            extendInfo = me.lookupReference('extendInfo'),
            operateInfo = me.lookupReference('operatedInfo'),
            reset = me.lookupReference('reset'),
            save = me.lookupReference('save');

        me.beforeLoadRecord(data, editable);
        // fill the data
        this.setData(data);
        if (data) {
            formWindow.loadRecord(data);
        }

        // set editable
        var disabled = !editable;
        baseInfo.setDisabled(disabled);
        detailInfo.setDisabled(disabled);
        extendInfo.setDisabled(disabled);
        operateInfo.setDisabled(disabled);
        if (disabled) {
            detailInfo.expand();
            extendInfo.expand();
            operateInfo.expand();
            reset.hide();
            save.hide();
        } else {
            detailInfo.collapse();
            extendInfo.collapse();
            operateInfo.collapse();
            reset.show();
            save.show();
            baseInfo.focus();
        }
        me.afterLoadRecord(data, editable);

        // show
        win.show();
    },

    onShow: function () {
        var record = this.getSelectedRecords()[0];
        var model = this.dowithRecord(record);
        if (model) {
            this.showPopWindow(Ext.String.format('显示 {0}的详细信息', this.getName()), model, false);
        } else {
            Ext.MessageBox.alert('提示', '在操作之前请先选中一行数据。');
        }
    },

    onAdd: function () {
        var win = this.lookupReference(this.getPopupWindowName());
        if (win) {
            win.destroy();
        }
        var model = this.dowithRecord(null);
        model.setId(-1);
        this.setOperate('add');
        this.showPopWindow(Ext.String.format('新增 一个{0}', this.getName()), model, true);
    },

    onModify: function () {
        var record = this.getSelectedRecords()[0];
        var model = this.dowithRecord(record);
        if (record) {
            this.setOperate('edit');
            this.showPopWindow(Ext.String.format('修改 {0}信息', this.getName()), model, true);
        } else {
            Ext.MessageBox.alert('提示', '在操作之前请先选中一行数据。');
        }
    },

    onDelete: function () {
        var record = this.getSelectedRecords()[0];
        if (record) {
            if (this.getCurrentGrid() != null) {
                var store = this.getCurrentGrid().getStore();
                if (store == null) {
                    Ext.MessageBox.alert('警告', '当前表格对象中的Store为空，不能保存数据。');
                    return;
                }
                this.setRecordProxy(store.getProxy());
                record.erase({
                    success: function () {
                        // TODO 删除数据成功
                        Ext.Boot.debug('delete successful.');
                    },
                    failure: function () {
                        Ext.Boot.debug('delete failure.');
                    }
                });
            } else {
                Ext.MessageBox.alert('提示', '没有指定获取Store的当前表格对象。');
            }
        } else {
            Ext.MessageBox.alert('提示', '在操作之前请先至少选中一行数据。');
        }
    },

    onFormReset: function () {
        var me = this,
            formWindow = me.lookupReference(ths.getFormWindowName());
        formWindow.loadRecord(me.getData());
    },

    onFormSave: function () {
        var me = this,
            formWindow = me.lookupReference(me.getFormWindowName()),
            record = formWindow.getRecord();

        if (!formWindow.isValid()) {
            Ext.MessageBox.alert('提示', '有部分输入的数据校验不通过，请检查。');
            return;
        }

        formWindow.updateRecord(record);
        me.afterUpdateRecord(record);
        if (me.getCurrentGrid() != null) {
            var store = me.getCurrentGrid().getStore();
            if (store == null) {
                Ext.MessageBox.alert('警告', '当前表格对象中的Store为空，不能保存数据。');
                return;
            }
            this.setRecordProxy(store.getProxy());
            record.save({
                success: function (record) {
                    if (me.getOperate() == 'add') {
                        store.insert(record);
                    }
                    me.setOperate(null);
                    var win = me.lookupReference(me.getPopupWindowName());
                    if (win) {
                        win.destroy();
                    }                },
                failure: function () {
                    Ext.MessageBox.alert('警告', '保存数据失败。');
                }
            });
        } else {
            Ext.MessageBox.alert('提示', '没有指定获取Store的当前表格对象。');
        }
    },

    onFormClose: function () {
        var win = this.lookupReference(this.getPopupWindowName());
        if (win) {
            win.hide();
        }
    }
});