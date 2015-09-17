/**
 * Created by john on 15-8-21.
 */
Ext.define('yt_manager_app.view.basedata.DivisionSelectWindowController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.divisionSelectWindow',

    init: function() {
        var me = this,
            view = me.getView(),
            initDivision = view.config.initDivision;
        if (initDivision != null) {
            // TODO 选中初始化的行政区划
            console.log(initDivision);
        }
    },

    onSelectionChange: function(model, selected) {
        // do nothing
    },

    onOK: function() {
        var me = this,
            tree = me.lookupReference('divisionTree'),
            select = tree.getSelection(),
            view = me.getView(),
            parent = view.config.parentWindow,
            win = me.lookupReference('divisionTree').up();
        if (select.length > 0) {
            parent.setPlaceData(select[0].getData());
            win.destroy();
        } else {
            Ext.MessageBox.alert('提示', '没有选择行政区划。');
        }
    },

    onClose: function() {
        // 关闭窗口
        var me = this,
            win = me.lookupReference('divisionTree').up();
        win.destroy();
    }
});