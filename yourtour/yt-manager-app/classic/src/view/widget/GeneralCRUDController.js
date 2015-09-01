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
        data: null
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

    showPopWindow: function (title, data, editable) {
        var win = this.lookupReference(this.config.popupWindowName);
        if (!win) {
            win = new yt_manager_app.view.member.UserWindow();
            this.getView().add(win);
        }
        win.setTitle(title);

        var me = this,
            formWindow = me.lookupReference(this.config.formWindowName),
            baseInfo = me.lookupReference('baseInfo'),
            detailInfo = me.lookupReference('detailInfo'),
            extendInfo = me.lookupReference('extendInfo'),
            operateInfo = me.lookupReference('operatedInfo'),
            reset = me.lookupReference('reset'),
            save = me.lookupReference('save');
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

        // show
        win.show();
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
        var model = new yt_manager_app.model.User();
        model.setId(-1);
        this.showPopWindow(Ext.String.format('新增 一个{0}', this.config.name), model, true);
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
            console.log(record.getProxy());
            if (this.config.currentGrid != null) {
                var store = this.config.currentGrid.getStore();
                if (store == null) {
                    Ext.MessageBox.alert('警告', '当前表格对象中的Store为空，不能保存数据。');
                    return;
                }
                yt_manager_app.model.User.setProxy(store.getProxy());
                record.erase({
                    success: function () {
                        // TODO 删除数据成功
                        console.log('delete successful.');
                    },
                    failure: function () {
                        console.log('delete failure.');
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
            formWindow = me.lookupReference(this.config.formWindowName);
        formWindow.loadRecord(this.getData());
    },

    onFormSave: function () {
        var me = this,
            formWindow = me.lookupReference(this.config.formWindowName);

        if (!formWindow.isValid()) {
            Ext.MessageBox.alert('提示', '有部分输入的数据校验不通过，请检查。');
            return;
        }

        formWindow.saveState();
        //var record = formWindow.getRecord();
        // 将form中的数据存储到record中。
        var data = formWindow.getValues(false, false, false, true);
        var record = new yt_manager_app.model.User({id: data.id, data: data});
        console.log(record);
        if (this.config.currentGrid != null) {
            var store = this.config.currentGrid.getStore();
            if (store == null) {
                Ext.MessageBox.alert('警告', '当前表格对象中的Store为空，不能保存数据。');
                return;
            }
            yt_manager_app.model.User.setProxy(store.getProxy());
            record.save({
                success: function () {
                    console.log('Save .............');
                    // TODO 保存数据成功
                },
                failure: function () {
                    console.log('failure............');
                }
            });
            var win = this.lookupReference(this.config.popupWindowName);
            if (win) {
                win.destroy();
            }
        } else {
            Ext.MessageBox.alert('提示', '没有指定获取Store的当前表格对象。');
        }
    },

    onFormClose: function () {
        var win = this.lookupReference(this.config.popupWindowName);
        if (win) {
            win.hide();
        }
    }
});