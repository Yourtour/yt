/**
 * Created by john on 15-8-21.
 */
Ext.define('yt_manager_app.view.widget.GeneralCRUDController', {
    extend: 'Ext.app.ViewController',

    config: {
        popupWindowName: 'popupWindow',
        formWindowName: 'formWindow',
        name: 'NA',
        currentGrid: null
    },

    getSelectedRecords: function () {
        var me = this,
            grid = this.config.currentGrid;
        if (grid == null) {
            return null;
        }
        var model = grid.getSelectionModel();
        return model.getSelection();
    },

    onShow: function () {
        var record = this.getSelectedRecords()[0];
        if (record) {
            this.showPopWindow(Ext.String.format('显示 {0}的详细信息', this.config.name), record, false);
        } else {
            Ext.MessageBox.alert('提示', '在操作之前请先选中一行数据。');
        }
    },

    onAdd: function () {
        var win = this.lookupReference(this.config.popupWindowName);
        if (win) {
            win.destroy();
        }
        this.showPopWindow(Ext.String.format('新增 一个{0}', this.config.name), null, true);
    },

    onModify: function () {
        var record = this.getSelectedRecords()[0];
        if (record) {
            this.showPopWindow(Ext.String.format('修改 {0}信息', this.config.name), record, true);
        } else {
            Ext.MessageBox.alert('提示', '在操作之前请先选中一行数据。');
        }
    },

    onDelete: function () {
        var record = this.getSelectedRecords()[0];
        if (record) {
            // TODO 删除选中的数据
            Ext.MessageBox.alert('提示', '删除数据，尚未实现。');
        } else {
            Ext.MessageBox.alert('提示', '在操作之前请先至少选中一行数据。');
        }
    },

    onFormReset: function () {
        var me = this,
            formWindow = me.lookupReference(this.config.formWindowName);
        formWindow.reset();
    },

    onFormSave: function () {
        var me = this,
            formWindow = me.lookupReference(this.config.formWindowName);
        // TODO 保存数据
        Ext.MessageBox.alert('提示', '保存数据，尚未实现。');
        var win = this.lookupReference(this.config.popupWindowName);
        if (win) {
            win.destroy();
        }
    },

    onFormClose: function () {
        var win = this.lookupReference(this.config.popupWindowName);
        if (win) {
            win.hide();
        }
    }
});