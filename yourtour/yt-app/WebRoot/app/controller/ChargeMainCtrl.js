Ext.define('YourTour.controller.ChargeMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
        refs: {
            chargeMainView:'#ChargeMainView',
        },

        control: {

        }
    },

    showPage: function (routeId) {
        var me = this;

        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.charge.ChargeMainView'));
    },

    onMemberListItemTap:function(dataview, index, item, record, e){
        this.showMemberDetailView(record, 'cancel');
    },

    showMemberDetailView:function(record, action){
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.member.MemberView'));
        var me = this, view = me.getMemberView(), btnAdd = view.down('#btnAdd'), btnDelete = view.down('#btnDelete');
        view.setData(record);

        if(action == 'cancel'){
            btnAdd.hide();
            btnDelete.show();
        }else{
            btnAdd.show();
            btnDelete.hide();
        }
    },

    showAddPage: function () {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.member.MemberAddView'));
    },

    showMessagePage: function () {
        var sessionId = '1111';
        this.redirectTo('/message/session/' + sessionId);
    },

    /**
     *
     */
    showPositionPage: function () {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.member.MemberPositionView'));

        var map = new BMap.Map('map');//指向map的容器
        map.centerAndZoom(new BMap.Point(121.491, 31.233), 11);
        window.setTimeout(function () {
                map.panTo(new BMap.Point(116.409, 39.918));
            },
            2000);
    },

    /**
     *
     */
    onBarScanner: function () {
        cordova.plugins.barcodeScanner.scan(
            function (result) {
                alert("We got a barcode\n" +
                    "Result: " + result.text + "\n" +
                    "Format: " + result.format + "\n" +
                    "Cancelled: " + result.cancelled);
            },
            function (error) {
                alert("Scanning failed: " + error);
            }
        );
    },

    /**
     *
     */
    showMemberSearchView: function () {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.member.MemberSearchView'));
    },

    /**
     * 用户搜索
     */
    onSearchFieldTap:function(){
        var me = this, memberList = me.getMemberSearchList();
        var options = {
            model: 'YourTour.model.UserModel',
            url: '/users/query',
            success: function (store) {
                memberList.setStore(store);
            }
        };
        me.getApplication().query(options);
    },

    onSearchListItemtap: function (dataview, index, item, record, e) {
        this.showMemberDetailView(record, 'add');
    },

    onAddAsMemberTap: function () {
        this.onMemberAdd('MEMBER');
    },

    onAddAsLeaderTap: function () {
        this.onMemberAdd('LEADER');
    },

    onAddAsExpertTap: function () {
        this.onMemberAdd('EXPERT');
    },

    /**
     *
     */
    onMemberAdd: function (role) {
        var data = {};

        var record = this.selectedMember;

        data.routeId = this.routeId;
        data.userId = record.get('id');
        data.role = role;

        Ext.Ajax.request({
            url: YourTour.util.Context.getContext('route/members/save'),
            method: "POST",
            params: Ext.JSON.encode(data),
            success: function (response) {
                var respObj = Ext.JSON.decode(response.responseText);
                if (respObj.errorCode != '0') {
                    Ext.Msg.alert(resp.errorText);
                    return;
                }
                ;

                Ext.ComponentManager.get('MainView').pop();
            },

            failure: function (response) {
                var respObj = Ext.JSON.decode(response.responseText);
                Ext.Msg.alert("Error", respObj.status.statusMessage);
            }
        });
    },

    onItemDelete: function (dataview, record) {
        var list = this.getMemberList();
        var store = list.getStore();
        var index = store.indexOf(record);

        var data = {};
        data.routeId = this.routeId;
        data.userId = record.get('id');

        Ext.Ajax.request({
            url: YourTour.util.Context.getContext('route/members/' + this.routeId + '/' + record.get('id') + '/delete'),
            method: "GET",
            success: function (response) {
                var respObj = Ext.JSON.decode(response.responseText);
                if (respObj.errorCode != '0') {
                    Ext.Msg.alert(respObj.errorText);
                    return;
                }
                ;

                store.removeAt(index);
            },

            failure: function (response) {
                var respObj = Ext.JSON.decode(response.responseText);
                Ext.Msg.alert("Error", respObj.status.statusMessage);
            }
        });
    }
});
