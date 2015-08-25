/**
 * Created by john on 15-8-21.
 */
Ext.define('yt_manager_app.view.member.UserController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.user',

    getSelectedRecords: function () {
        var me = this,
            grid = me.lookupReference('grid_paging'),
            model = grid.getSelectionModel();
        return model.getSelection();
    },

    showPopWindow: function (title, data, editable) {
        var win = this.lookupReference('userPopupWindow');
        if (!win) {
            win = new yt_manager_app.view.member.UserWindow();
            this.getView().add(win);
        }

        var me = this,
            formWindow = me.lookupReference('formWindow'),
            baseInfo = me.lookupReference('baseInfo'),
            userName = me.lookupReference('userName'),
            realName = me.lookupReference('realName'),
            nickName = me.lookupReference('nickName'),
            password = me.lookupReference('password'),
            gender = me.lookupReference('gender'),
            mobileNo = me.lookupReference('mobileNo'),

            detailInfo = me.lookupReference('detailInfo'),
            imageUrl = me.lookupReference('imageUrl'),
            birthday = me.lookupReference('birthday'),
            email = me.lookupReference('email'),
            residence = me.lookupReference('residence'),
            role = me.lookupReference('role'),
            rank = me.lookupReference('rank'),
            status = me.lookupReference('status'),

            extendInfo = me.lookupReference('extendInfo'),
            character = me.lookupReference('character'),
            nativePlace = me.lookupReference('nativePlace'),
            constellation = me.lookupReference('constellation'),
            slogan = me.lookupReference('slogan'),

            reset = me.lookupReference('reset'),
            save = me.lookupReference('save');
        // fill the data
        if (data) {
            // TODO 填充数据
            console.log(data);
            formWindow.loadRecord(data);
        }

        // set editable
        var disabled = !editable;
        baseInfo.setDisabled(disabled);
        detailInfo.setDisabled(disabled);
        extendInfo.setDisabled(disabled);
        if (disabled) {
            detailInfo.expand();
            extendInfo.expand();
            reset.hide();
            save.hide();
        } else {
            detailInfo.collapse();
            extendInfo.collapse();
            reset.show();
            save.show();
            baseInfo.focus();
        }

        // show
        win.setTitle(title);
        win.show();
    },

    onTabChange: function (tabs, newTab, oldTab) {
        // TODO 切换了tab
    },

    onShowDetails: function () {
        var record = this.getSelectedRecords()[0];
        if (record) {
            this.showPopWindow('显示 用户信息', record, false);
        } else {
            Ext.MessageBox.alert('提示', '在操作之前请先选中一行数据。');
        }
    },

    onAddUser: function () {
        var win = this.lookupReference('userPopupWindow');
        if (win) {
            win.destroy();
        }
        this.showPopWindow('新增 一个用户', null, true);
    },

    onModifyUser: function () {
        var record = this.getSelectedRecords()[0];
        if (record) {
            // TODO 从表格中获取当前选中行，并获取数据
            console.log(record);
            this.showPopWindow('修改 用户信息', record, true);
        } else {
            Ext.MessageBox.alert('提示', '在操作之前请先选中一行数据。');
        }
    },

    onDeleteUser: function () {
        var record = this.getSelectedRecords()[0];
        if (record) {
            // TODO 删除选中的数据
        } else {
            Ext.MessageBox.alert('提示', '在操作之前请先至少选中一行数据。');
        }
    },

    onFormReset: function () {
        var me = this,
            formWindow = me.lookupReference('formWindow');
        formWindow.reset();
    },

    onFormSave: function () {
        var me = this,
            formWindow = me.lookupReference('formWindow');
        // TODO 保存数据
        var win = this.lookupReference('userPopupWindow');
        if (win) {
            win.destroy();
        }
    },

    onFormClose: function () {
        var win = this.lookupReference('userPopupWindow');
        if (win) {
            win.hide();
        }
    }
});