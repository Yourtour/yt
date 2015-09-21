Ext.define('yt_manager_app.store.NavigationTree', {
    extend: 'Ext.data.TreeStore',
    alias: 'store.navigationTree',

    fields: [{
        name: 'text'
    }],

    root: {
        expanded: true,
        children: [{
            text: '总概',
            //view: 'dashboard.Dashboard',
            leaf: true,
            iconCls: 'right-icon hot-icon x-fa fa-pie-chart',
            routeId: 'dashboard'
        }, {
            text: '工作台',
            //view: 'dashboard.Workspace',
            iconCls: 'right-icon new-icon x-fa fa-edit',
            leaf: true,
            routeId: 'workspace'
        }, {
            text: '基础',
            expanded: false,
            selectable: false,
            iconCls: 'x-fa fa-leanpub',
            routeId: 'basedata-parent',
            id: 'basedata-parent',
            children: [{
                text: '行政区划',
                view: 'basedata.Division',
                leaf: true,
                iconCls: 'x-fa fa-file-o',
                routeId: 'basedata.Division'
            }, {
                text: '目的地',
                //view: 'operate.Ready',
                leaf: true,
                iconCls: 'x-fa fa-file-o',
                routeId: 'basedata.target'
            }, {
                text: '飞机',
                //view: 'operate.Review',
                leaf: true,
                iconCls: 'x-fa fa-file-o',
                routeId: 'basedata.flight'
            }, {
                text: '火车',
                //view: 'operate.Submit',
                leaf: true,
                iconCls: 'x-fa fa-file-o',
                routeId: 'basedata.train'
            }, {
                text: '长途汽车',
                //view: 'pages.BlankPage',
                leaf: true,
                iconCls: 'x-fa fa-file-o',
                routeId: 'basedata.car'
            }]
        }, {
            text: '会员',
            expanded: false,
            selectable: false,
            iconCls: 'x-fa fa-leanpub',
            routeId: 'member-parent',
            id: 'member-parent',
            children: [{
                text: '普通会员',
                view: 'member.User',
                leaf: true,
                iconCls: 'x-fa fa-file-o',
                routeId: 'member.user'
            }, {
                text: '达人',
                view: 'member.Expert',
                leaf: true,
                iconCls: 'x-fa fa-file-o',
                routeId: 'member.expert'
            }, {
                text: '地主',
                //view: 'operate.Review',
                leaf: true,
                iconCls: 'x-fa fa-file-o',
                routeId: 'member.host'
            }, {
                text: '足迹',
                //view: 'operate.Submit',
                leaf: true,
                iconCls: 'x-fa fa-file-o',
                routeId: 'member.footprint'
            }, {
                text: '费用',
                //view: 'pages.BlankPage',
                leaf: true,
                iconCls: 'x-fa fa-file-o',
                routeId: 'member.money'
            }]
        }, {
            text: '行程',
            expanded: false,
            selectable: false,
            iconCls: 'x-fa fa-leanpub',
            routeId: 'route-parent',
            id: 'route-parent',
            children: [{
                text: '线路',
                view: 'route.Line',
                leaf: true,
                iconCls: 'x-fa fa-file-o',
                routeId: 'route.line'
            }, {
                text: '安排',
                //view: 'operate.Ready',
                leaf: true,
                iconCls: 'x-fa fa-file-o',
                routeId: 'route.plan'
            }, {
                text: '活动',
                //view: 'operate.Review',
                leaf: true,
                iconCls: 'x-fa fa-file-o',
                routeId: 'route.activity'
            }, {
                text: '团队',
                //view: 'operate.Submit',
                leaf: true,
                iconCls: 'x-fa fa-file-o',
                routeId: 'route.team'
            }, {
                text: '行程随记',
                //view: 'pages.BlankPage',
                leaf: true,
                iconCls: 'x-fa fa-file-o',
                routeId: 'route.mind'
            }]
        }, {
            text: '资源',
            expanded: false,
            selectable: false,
            iconCls: 'x-fa fa-user',
            routeId: 'resource-parent',
            id: 'resource-parent',
            children: [{
                text: '景点',
                view: 'resource.Scene',
                leaf: true,
                iconCls: 'x-fa fa-file-o',
                routeId: 'resource.scene'
            }, {
                text: '宾馆',
                view: 'resource.Hotel',
                leaf: true,
                iconCls: 'x-fa fa-file-o',
                routeId: 'resource.hotel'
            }, {
                text: '饭店',
                view: 'resource.Restaurant',
                leaf: true,
                iconCls: 'x-fa fa-file-o',
                routeId: 'resource.restaurant'
            }, {
                text: '购物',
                //view: 'data.Department',
                leaf: true,
                iconCls: 'x-fa fa-file-o',
                routeId: 'resource.shopping'
            }, {
                text: '娱乐',
                //view: 'data.Employee',
                leaf: true,
                iconCls: 'x-fa fa-file-o',
                routeId: 'resource.entertainment'
            }]
        }]
    }
});
