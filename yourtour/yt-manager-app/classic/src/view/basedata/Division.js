/**
 * Created by john on 15-9-6.
 */
Ext.define('yt_manager_app.view.basedata.Division', {
    extend: 'Ext.container.Container',
    xtype: 'division',
    controller: 'division',

    defaults: {
        bodyPadding: 10,
        scrollable: true,
        width: 500,
        height: 300
    },

    layout: {
        type: 'hbox',
        align: 'stretch'
    },

    items: [{
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        bbar: {
            //xtype: 'toolbar',
            items: ['->', {
                xtype: 'button',
                tooltip: '删除选中的行政区域。',
                reference: 'delete',
                html: '<img class="tool-icon" src="./resources/images/icon/delete-128x128.png" alt="Delete">',
                handler: 'onDelete'
            }, {
                xtype: 'button',
                tooltip: '新增一个行政区域。',
                html: '<img class="tool-icon" src="./resources/images/icon/add-128x128.png" alt="Add">',
                handler: 'onAdd'
            }]
        },
        items: [{
            xtype: 'treepanel',
            title: '行政区划',
            reference: 'tree',
            width: 100,
            height: '100%',
            border: 1,
            flex: 1,
            useArrows: true,
            rootVisible: false,
            columns: [{
                xtype: 'treecolumn',
                text: 'Name',
                flex: 2,
                sortable: true,
                dataIndex: 'text'
            }, {
                text: 'Code',
                flex: 1,
                width: 100,
                dataIndex: 'code',
                sortable: true
            }, {
                text: 'Full code',
                flex: 1,
                width: 150,
                dataIndex: 'fullCode',
                sortable: true
            }],
            selModel: {
                allowDeselect: true,
                listeners: {
                    selectionchange: 'onSelectionChange'
                }
            }
        }]
    }, {
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        width: 300,
        items: [{
            xtype: 'form',
            reference: 'form',
            defaults: {
                xtype: 'textfield'
            },
            buttonAlign: 'center',
            buttons: [{
                text: '重置',
                reference: 'reset',
                disabled: true,
                handler: 'onFormReset'
            }, {
                text: '修改',
                reference: 'edit',
                disabled: true,
                handler: 'onFormEdit'
            }, {
                text: '保存',
                reference: 'save',
                disabled: true,
                handler: 'onFormSave'
            }],
            items: [{
                fieldLabel: '代码',
                reference: 'code',
                allowBlank: false,
                editable: false,
                name: 'code'
            }, {
                fieldLabel: '名称',
                reference: 'name',
                allowBlank: false,
                editable: false,
                name: 'text',
                maxlength: 30,
                emptyText: '行政区域名称！'
            }]
        }]
    }]
});