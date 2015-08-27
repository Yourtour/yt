/**
 * Created by john on 15-8-24.
 */
Ext.define('yt_manager_app.view.member.UserWindow', {
    extend: 'Ext.window.Window',
    xtype: 'userWindow',
    reference: 'userPopupWindow',

    requires: [
        //'Ext.ux.rating.Picker'
    ],

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
            xtype: 'fieldset',
            title: '基本信息',
            reference: 'baseInfo',
            collapsible: false,
            defaultType: 'textfield',
            bodyPadding: 2,
            defaults: {
                anchor: '100%',
                afterLabelTextTpl: [
                    '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                ]
            },
            items: [{
                fieldLabel: '登录名',
                reference: 'userName',
                allowBlank: false,
                name: 'userName',
                emptyText: '登录名，只能包含半角字母（大小写）、数字和下划线(_)！'
            }, {
                fieldLabel: '真实名字',
                reference: 'realName',
                allowBlank: false,
                name: 'realName',
                emptyText: '真实姓名'
            }, {
                fieldLabel: '昵称',
                reference: 'nickName',
                allowBlank: false,
                name: 'nickName',
                emptyText: '昵称'
            }, {
                fieldLabel: '口令',
                reference: 'password',
                allowBlank: false,
                name: 'password',
                emptyText: '口令',
                inputType: 'password'
            }, {
                fieldLabel: '性别',
                reference: 'gender',
                name: 'gender',
                xtype: 'radiogroup',
                layout: {
                    autoFlex: false
                },
                defaults: {
                    name: 'ccType',
                    margin: '0 30 0 0'
                },
                items: [{
                    inputValue: 'male',
                    boxLabel: '男',
                    checked: true
                }, {
                    inputValue: 'female',
                    boxLabel: '女'
                }, {
                    inputValue: 'NA',
                    boxLabel: 'NA'
                }]
            }, {
                fieldLabel: '移动电话',
                reference: 'mobileNo',
                allowBlank: false,
                name: 'mobileNo',
                emptyText: '移动电话号码'
            }]
        }, {
            xtype: 'fieldset',
            title: '详细信息',
            reference: 'detailInfo',
            collapsible: true,
            collapsed: true,
            defaultType: 'textfield',
            bodyPadding: 2,
            defaults: {
                anchor: '100%'
            },
            items: [{
                fieldLabel: '照片',
                reference: 'imageUrl',
                name: 'imageUrl',
                emptyText: 'Image URL'
            }, {
                fieldLabel: '生日',
                xtype: 'datefield',
                format: 'Y/m/d',
                maxValue: new Date(),
                reference: 'birthday',
                name: 'birthday',
                emptyText: 'YYYY/mm/dd'
            }, {
                fieldLabel: '邮件',
                reference: 'email',
                name: 'email',
                vtype: 'email',
                emptyText: '邮件地址'
            }, {
                fieldLabel: '地址',
                reference: 'residence',
                name: 'residence',
                emptyText: '地址'
            }, {
                xtype: 'combobox',
                store: (function () {
                    return new Ext.data.Store({
                        fields: ['abbr', 'name'],
                        data: (function () {
                            var data = new Array(3);
                            data[0] = {abbr: 'MEMBER', name: '一般会员'};
                            data[1] = {abbr: 'EXPERT', name: '达人'};
                            data[2] = {abbr: 'HOST', name: '地主'};
                            return data;
                        })()
                    });
                })(),
                displayField: 'name',
                valueField: 'abbr',
                queryMode: 'local',
                editable: false,
                fieldLabel: '角色',
                reference: 'role',
                name: 'role',
                emptyText: '角色'
            }, {
                xtype: 'numberfield',
                minValue: 0,
                maxValue: 11,
                value: 5,
                fieldLabel: '等级',
                reference: 'rank',
                name: 'rank',
                emptyText: '等级'
            }, {
                xtype: 'combobox',
                store: (function () {
                    return new Ext.data.Store({
                        fields: ['abbr', 'name'],
                        data: (function () {
                            var data = new Array(4);
                            data[0] = {abbr: 'VALIDATED', name: '有效'};
                            data[1] = {abbr: 'ACTIVED', name: '激活'};
                            data[2] = {abbr: 'FROZEN', name: '冻结'};
                            data[3] = {abbr: 'CLOSED', name: '关闭'};
                            return data;
                        })()
                    });
                })(),
                displayField: 'name',
                valueField: 'abbr',
                queryMode: 'local',
                editable: false,
                fieldLabel: '状态',
                reference: 'status',
                name: 'status',
                emptyText: '状态'
            }]
        }, {
            xtype: 'fieldset',
            title: '拓展信息',
            reference: 'extendInfo',
            collapsible: true,
            collapsed: true,
            defaultType: 'textareafield',
            bodyPadding: 2,
            defaults: {
                anchor: '100%'
            },
            items: [{
                fieldLabel: '个人特质',
                reference: 'character',
                name: 'character',
                emptyText: '个人特质信息'
            }, {
                fieldLabel: '籍贯',
                reference: 'nativePlace',
                name: 'nativePlace',
                emptyText: '籍贯'
            }, {
                fieldLabel: '星座',
                reference: 'constellation',
                name: 'constellation',
                emptyText: '星座'
            }, {
                fieldLabel: '个人口号',
                reference: 'slogan',
                name: 'slogan',
                emptyText: '个人口号'
            }]
        }],

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
        }]
    }]
});