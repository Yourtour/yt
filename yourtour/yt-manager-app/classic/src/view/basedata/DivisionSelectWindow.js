/**
 * Created by john on 15-9-17.
 */
Ext.define('yt_manager_app.view.basedata.DivisionSelectWindow', {
    extend: 'Ext.window.Window',
    xtype: 'divisionSelectWindow',
    reference: 'divisionSelectPopupWindow',

    controller: 'divisionSelectWindow',

    title: '行政区划选择窗口',
    autoShow: true,
    closable: true,
    width: 600,
    height: 500,
    minWidth: 500,
    minHeight: 400,
    layout: 'fit',
    resizable: true,
    modal: true,
    closeAction: 'hide',
    scrollable: true,
    bodyPadding: 10,

    items: {
        xtype: 'divisionTree',
        reference: 'divisionTree',
        buttons: [{
            text: '确定',
            reference: 'ok',
            handler: 'onOK'
        }, {
            text: '关闭',
            reference: 'close',
            handler: 'onClose'
        }]
    }
});