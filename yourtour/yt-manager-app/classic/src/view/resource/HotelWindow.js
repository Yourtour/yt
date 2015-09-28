/**
 * Created by john on 15-8-24.
 */
Ext.define('yt_manager_app.view.resource.HotelWindow', {
    extend: 'yt_manager_app.view.widget.GeneralFormWindow',
    xtype: 'hotelWindow',
    reference: 'hotelPopupWindow',

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
                emptyText: '宾馆名称'
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
                referenece: 'ratingParent',
                items: [{
                    xtype: 'rating',
                    scale: '150%',
                    reference: 'star',
                    name: 'star',
                    minimum: 0,
                    limit: 5
                }]
            }, {
                fieldLabel: '电话',
                reference: 'phone',
                allowBlank: true,
                name: 'phone',
                emptyText: '联系电话号码'
            }, {
                xtype: 'fieldcontainer',
                fieldLabel: '目的地',
                reference: 'placeParent',
                layout: 'hbox',
                items: [{
                    xtype: 'textfield',
                    reference: 'place',
                    allowBlank: false,
                    editable: false,
                    margin: '0 0 0 0',
                    name: 'place',
                    flex: 1,
                    emptyText: '线路目的地'
                }, {
                    xtype: 'button',
                    text: '选择',
                    margin: '0 0 0 0',
                    handler: 'onPopupDivisionSelectWindow'
                }]
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
                name: 'postCode',
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
                fieldLabel: '住宿标准',
                reference: 'accommondationStandard',
                name: 'accommodationStandard',
                emptyText: '住宿标准'
            }, {
                fieldLabel: '特色房',
                reference: 'specialRoom',
                name: 'specialRoom',
                emptyText: '特色房'
            }, {
                fieldLabel: '房间设施',
                reference: 'roomEquipment',
                name: 'roomEquipment',
                emptyText: '房间设施'
            }, {
                fieldLabel: '网络信息',
                reference: 'networkInfo',
                name: 'networkInfo',
                emptyText: '网络信息'
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