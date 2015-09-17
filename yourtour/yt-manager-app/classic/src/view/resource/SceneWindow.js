/**
 * Created by john on 15-8-24.
 */
Ext.define('yt_manager_app.view.resource.SceneWindow', {
    extend: 'yt_manager_app.view.widget.GeneralFormWindow',
    xtype: 'sceneWindow',
    reference: 'scenePopupWindow',

    requires: [
        //'Ext.ux.rating.Picker'
    ],

    config: {
        base: {
            items: [{
                fieldLabel: '名称',
                reference: 'name',
                allowBlank: false,
                name: 'name',
                emptyText: '景点名称'
            }, {
                fieldLabel: '开放时间',
                reference: 'openTime',
                allowBlank: true,
                name: 'openTime',
                emptyText: '开放时间信息'
            }, {
                fieldLabel: '关闭时间',
                reference: 'closeTime',
                allowBlank: true,
                name: 'closeTime',
                emptyText: '关闭时间信息'
            }, {
                xtype: 'fieldcontainer',
                fieldLabel: '星级',
                items: [{
                    xtype: 'rating',
                    scale: '150%',
                    reference: 'star',
                    name: 'star',
                    minimum: 0,
                    limit: 5
                }]
            }, /*{
                xtype: 'numberfield',
                fieldLabel: '星级',
                name: 'star',
                minValue: 0,
                maxValue: 5,
                reference: 'star',
                hidden: false
            }, */{
                fieldLabel: '电话',
                reference: 'phone',
                allowBlank: true,
                name: 'phone',
                emptyText: '联系电话号码'
            }],
            hidden: false
        },
        detail: {
            items: [{
                fieldLabel: '照片',
                reference: 'imageUrl',
                name: 'imageUrl',
                emptyText: 'Image URL'
            }, {
                fieldLabel: '地址',
                reference: 'address',
                allowBlank: true,
                name: 'address',
                emptyText: '联系地址'
            }, {
                fieldLabel: '邮编',
                reference: 'postCode',
                allowBlank: true,
                name: 'posCode',
                emptyText: '邮政编码'
            }, {
                fieldLabel: '网址',
                reference: 'website',
                allowBlank: true,
                name: 'website',
                emptyText: '网站地址'
            }, {
                fieldLabel: '交通',
                reference: 'trafficIntro',
                allowBlank: true,
                name: 'trafficIntro',
                emptyText: '交通信息'
            }, {
                fieldLabel: '支付',
                reference: 'payment',
                allowBlank: true,
                name: 'payment',
                emptyText: '支付信息'
            }, {
                fieldLabel: '位置',
                reference: 'position',
                allowBlank: true,
                name: 'position',
                emptyText: '位置信息'
            }, {
                xtype: 'combobox',
                store: {
                    data: [{
                        abbr: 'VALIDATED', name: '有效'
                    }, {
                        abbr: 'ACTIVED', name: '激活'
                    }, {
                        abbr: 'FROZEN', name: '冻结'
                    }, {
                        abbr: 'CLOSED', name: '关闭'
                    }]
                },
                displayField: 'name',
                valueField: 'abbr',
                queryMode: 'local',
                editable: false,
                fieldLabel: '状态',
                reference: 'status',
                name: 'status',
                emptyText: '状态'
            }],
            hidden: false
        },
        extend: {
            items: [{
                fieldLabel: '简介',
                reference: 'intro',
                name: 'intro',
                emptyText: '景点简介'
            }, {
                fieldLabel: '票务',
                reference: 'ticket',
                name: 'ticket',
                emptyText: '票务信息'
            }, {
                fieldLabel: '地图',
                reference: 'sceneMap',
                name: 'sceneMap',
                emptyText: '景区地图'
            }, {
                fieldLabel: '特色',
                reference: 'specialScene',
                name: 'specialScene',
                emptyText: '特色景点'
            }, {
                fieldLabel: '交通',
                reference: 'sceneTraffic',
                name: 'sceneTraffic',
                emptyText: '景区交通信息'
            }, {
                fieldLabel: '预定',
                reference: 'bookingMemo',
                name: 'bookingMemo',
                emptyText: '预定信息'
            }, {
                fieldLabel: '技巧',
                reference: 'tips',
                name: 'tips',
                emptyText: '旅行技巧'
            }],
            hidden: false
        },
        operate: {
            hidden: false
        }
    }
});