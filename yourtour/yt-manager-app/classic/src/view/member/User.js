/**
 * Created by john on 15-8-21.
 */
Ext.define('yt_manager_app.view.member.User', {
    extend: 'Ext.tab.Panel',
    xtype: 'user',

    controller: 'user',

    defaults: {
        bodyPadding: 10,
        scrollable: true,
        width: 500,
        height: 300
    },

    cls: 'tab-panel',

    listeners: {
        tabchange: 'onTabChange'
    },

    initComponent: function () {
        var userGridColumns = [{
            text: 'User name',
            dataIndex: 'userName',
            width: 120,
            sortable: true
        }, {
            text: 'Nick name',
            dataIndex: 'nickName',
            width: 120,
            sortable: true
        }, {
            text: 'Real name',
            dataIndex: 'realName',
            width: 120,
            sortable: true
        }, {
            text: 'Role',
            dataIndex: 'role',
            width: 100,
            sortable: true,
            renderer: function(val) {
                if (val == 'EXPERT') {
                    return '达人';
                } else if (val == 'HOST') {
                    return '地主';
                } else {
                    return '一般会员';
                }
            }
        }, {
            text: 'Mobile',
            dataIndex: 'mobileNo',
            width: 150,
            sortable: true
        }, {
            text: 'Email',
            dataIndex: 'email',
            flex: 1,
            sortable: true
        }];

        var generalStore = new yt_manager_app.store.User();

        var generalTab = Ext.create('yt_manager_app.view.widget.GeneralCRUDPanel', {
            id: 'user-crud-tab',
            nameEN: 'user',
            nameZHCN: '用户',
            gridName: 'user_crud_grid_paging',
            icon: './resources/images/toggle-icon.png',
            gridColumns: userGridColumns,
            store: generalStore
        });

        var cypherTab = Ext.create('yt_manager_app.view.widget.GeneralCypherPanel', {
            id: 'user-cypher-tab',
            nameEN: 'user',
            nameZHCN: '用户',
            gridName: 'user_cypher_grid_paging',
            icon: './resources/images/toggle-icon.png',
            gridColumns: userGridColumns
        });

        var graphTab = {
            id: 'user-graph-tab',
            title: '会员关系',
            icon: './resources/images/user-profile/3.png',
            html: 'tab pane 3.'
        };

        Ext.apply(this, {
            items: [generalTab, cypherTab, graphTab]
        });
        //
        this.callParent();
    }
});
