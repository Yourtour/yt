/**
 * Created by john on 15-8-27.
 */
Ext.define('yt_manager_app.view.widget.GeneralCRUDPanel', {
    extend: 'Ext.panel.Panel',
    xtype: 'generalCRUDPanel',

    title: null,

    initComponent: function() {
        var nameEN = this.config.nameEN,
            nameZHCN = this.config.nameZHCN,
            gridName = this.config.gridName,
            gridColumns = this.config.gridColumns,
            store = this.config.store;

        var tools = ['->', {
            xtype: 'button',
            tooltip: (function() {
                return Ext.String.format('显示[{0}]详情。', nameZHCN);
            })(),
            html: '<img class="tool-icon" src="/resources/images/icon/info-128x128.png" alt="Details">',
            handler: 'onShow'
        }, '-', {
            xtype: 'button',
            tooltip: (function() {
                return Ext.String.format('新增一个[{0}]。', nameZHCN);
            })(),
            html: '<img class="tool-icon" src="/resources/images/icon/add-128x128.png" alt="Add">',
            handler: 'onAdd'
        }, {
            xtype: 'button',
            tooltip: (function() {
                return Ext.String.format('修改指定[{0}]的信息。', nameZHCN);
            })(),
            html: '<img class="tool-icon" src="/resources/images/icon/edit-128x128.png" alt="Modify">',
            handler: 'onModify'
        }, '-', {
            xtype: 'button',
            tooltip: (function() {
                return Ext.String.format('删除指定的[{0}]。',nameZHCN);
            })(),
            html: '<img class="tool-icon" src="/resources/images/icon/delete-128x128.png" alt="Delete">',
            handler: 'onDelete'
        }];

        Ext.apply(this, {
            title: (function() {
                return '所有' + nameZHCN;
            })(),
            items: [Ext.create('yt_manager_app.view.widget.GeneralGrid', {
                reference: gridName,
                columns: gridColumns,
                store: store,
                tbar: {
                    xtype: 'toolbar',
                    items: tools
                }
            })]
        });
        this.callParent();
    }
});