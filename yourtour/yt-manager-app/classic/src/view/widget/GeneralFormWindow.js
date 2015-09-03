/**
 * Created by john on 15-8-24.
 */
Ext.define('yt_manager_app.view.widget.GeneralFormWindow', {
    extend: 'Ext.window.Window',

    config: {
        base: {
            items: null,
            hidden: false
        },
        detail: {
            items: null,
            hidden: false
        },
        extend: {
            items: null,
            hidden: false
        },
        operate: {
            hidden: false
        }
    },

    defaults: {
        bodyPadding: 10,
        scrollable: true
    },

    width: 600,
    height: 500,
    minWidth: 500,
    minHeight: 400,
    layout: 'fit',
    resizable: true,
    modal: true,
    closeAction: 'hide',

    buttons: [{
        text: '重置',
        reference: 'reset',
        handler: 'onFormReset'
    }, {
        text: '保存',
        reference: 'save',
        handler: 'onFormSave'
    }, {
        text: '关闭',
        handler: 'onFormClose'
    }],

    initComponent: function () {
        var me = this,
            config = me.config;
        Ext.apply(me, {
            items: [{
                xtype: 'form',
                reference: 'formWindow',
                layout: {
                    type: 'vbox',
                    align: 'stretch'
                },
                border: false,
                bodyPadding: 20,

                fieldDefaults: {
                    msgTarget: 'side',
                    labelAlign: 'left',
                    labelWidth: 100,
                    labelStyle: 'font-weight:bold'
                },
                items: [{
                    xtype: 'numberfield',
                    name: 'id',
                    reference: 'id',
                    hidden: true
                }, {
                    xtype: 'fieldset',
                    title: '基本信息',
                    reference: 'baseInfo',
                    collapsible: false,
                    defaultType: 'textfield',
                    hidden: config.base.hidden,
                    bodyPadding: 2,
                    defaults: {
                        anchor: '100%',
                        afterLabelTextTpl: [
                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                        ]
                    },
                    items: config.base.items
                }, {
                    xtype: 'fieldset',
                    title: '详细信息',
                    reference: 'detailInfo',
                    collapsible: true,
                    collapsed: true,
                    defaultType: 'textfield',
                    hidden: config.detail.hidden,
                    bodyPadding: 2,
                    defaults: {
                        anchor: '100%'
                    },
                    items: config.detail.items
                }, {
                    xtype: 'fieldset',
                    title: '拓展信息',
                    reference: 'extendInfo',
                    collapsible: true,
                    collapsed: true,
                    defaultType: 'textareafield',
                    hidden: config.extend.hidden,
                    bodyPadding: 2,
                    defaults: {
                        anchor: '100%'
                    },
                    items: config.extend.items
                }, {
                    xtype: 'fieldset',
                    title: '操作信息',
                    reference: 'operatedInfo',
                    collapsible: true,
                    collapsed: true,
                    defaultType: 'textfield',
                    hidden: config.operate.hidden,
                    bodyPadding: 2,
                    defaults: {
                        anchor: '100%',
                        editable: false
                    },
                    items: [{
                        fieldLabel: '创建时间',
                        name: 'createdTimeStr'
                    }, {
                        fieldLabel: '创建人',
                        name: 'createdUserId'
                    }, {
                        fieldLabel: '最后修改时间',
                        name: 'updatedTimeStr'
                    }, {
                        fieldLabel: '修改人',
                        name: 'updatedUserId'
                    }]
                }]
            }]
        });
        this.callParent();
    }
});