/**
 * Created by john on 15-8-24.
 */
Ext.define('yt_manager_app.view.route.RouteWindow', {
    extend: 'yt_manager_app.view.widget.GeneralFormWindow',
    xtype: 'routeWindow',
    reference: 'routePopupWindow',

    config: {
        base: {
            items: [{
                fieldLabel: '名称',
                reference: 'name',
                allowBlank: false,
                name: 'name',
                emptyText: '行程名称'
            }, {
                fieldLabel: '线路名称',
                reference: 'lineName',
                allowBlank: false,
                name: 'lineName',
                emptyText: '线路名称'
            }, {
                fieldLabel: '目的地',
                reference: 'place',
                allowBlank: false,
                name: 'place',
                emptyText: '行程目的地'
            }],
            hidden: false
        },
        detail: {
            items: [{
                fieldLabel: '照片',
                reference: 'imageUrl',
                name: 'imageUrl',
                emptyText: 'Image URL'
            },{
                fieldLabel: '开始日期',
                reference: 'startTime',
                name: 'startTime',
                emptyText: 'Y/m/d'
            },{
                fieldLabel: '结束日期',
                reference: 'endTime',
                name: 'endTime',
                emptyText: 'Y/m/d'
            },{
                fieldLabel: '行程天数',
                reference: 'period',
                name: 'period',
                emptyText: '1.0'
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
            }],
            hidden: false
        },
        extend: {
            items: [{
                fieldLabel: '简介',
                reference: 'intro',
                name: 'intro',
                emptyText: '行程简介'
            }, {
                fieldLabel: '特质',
                reference: 'feature',
                name: 'feature',
                emptyText: '行程特质'
            }, {
                fieldLabel: '推荐理由',
                reference: 'reason',
                name: 'reason',
                emptyText: '行程推荐理由'
            }],
            hidden: false
        },
        operate: {
            hidden: false
        }
    }
});