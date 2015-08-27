/**
 * Created by john on 15-8-27.
 */
Ext.define('yt_manager_app.view.widget.GeneralCypherPanel', {
    extend: 'Ext.container.Container',
    xtype: 'generalCypherPanel',

    initComponent: function() {
        var nameEN = this.config.nameEN,
            nameZHCN = this.config.nameZHCN,
            gridName = this.config.gridName,
            gridColumns = this.config.gridColumns;

        var tools = [{
            xtype: 'textareafield',
            fieldLabel: 'Cypher',
            reference: 'cypher',
            name: 'cypher',
            flex: 1,
            minWidth: 300,
            maxWidth: 800,
            shrinkWrap: 0,
            emptyText: 'Cypher查询语句'
        }, {
            xtype: 'button',
            tooltip: 'Query the cypher statement.',
            html: '<img class="tool-icon" src="/resources/images/icon/search-128x128.png" alt="Query">',
            handler: 'onQuery'
        }, '->', {
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
            title: '条件查询',
            items: [Ext.create('yt_manager_app.view.widget.GeneralGrid', {
                reference: gridName,
                columns: gridColumns,
                store: {
                    //type: nameEN
                },
                tbar: {
                    xtype: 'toolbar',
                    items: tools
                }
            })]
        });
        this.callParent();
    }
});