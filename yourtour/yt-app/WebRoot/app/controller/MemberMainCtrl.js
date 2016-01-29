Ext.define('YourTour.controller.MemberMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
        refs: {
            memberMainView:'#MemberMainView',
            memberList:'#MemberMainView #memberList' ,

            memberSearchView: '#MemberSearchView',
            memberSearchList: '#MemberSearchView #memberSearchList',

            memberView: '#MemberView',

            memberSelectionView: '#MemberSelectionView',
            memberSelectionList: '#MemberSelectionView #memberList'
        },

        control: {
            memberSearchList: {
                itemtap: 'onSearchListItemtap'
            },

            '#MemberMainView #memberList':{
                itemtap:'onMemberListItemTap'
            },

            '#MemberSelectionView #memberList':{
                itemtap:'onMemberSelectionItemTap'
            },

            '#MemberMainView #btnMessage': {
                tap: 'showMessagePage'
            },

            '#MemberMainView #btnCancel': {
                tap: 'onMainViewBtnCancelTap'
            },

            '#MemberMainView #btnPosition': {
                tap: 'showPositionPage'
            },

            '#MemberMainView #btnAdd': {
                tap: 'showAddPage'
            },

            '#MemberAddView #barscanner': {
                tap: 'onBarScanner'
            },

            '#MemberAddView #searchfield': {
                tap: 'showMemberSearchView'
            },

            '#MemberSearchView #btnSearch': {
                tap: 'onSearchFieldTap'
            },

            '#MemberSearchView #btnAddAsExpert': {
                tap: 'onAddAsExpertTap'
            },

            '#MemberSearchView #btnAddAsMember': {
                tap: 'onAddAsMemberTap'
            },

            '#MemberSearchView #btnAddAsLeader': {
                tap: 'onAddAsLeaderTap'
            },

            '#MemberView #btnAdd':{
                tap:'onMemberAddTap'
            },

            '#MemberView #btnDelete':{
                tap:'onMemberDeleteTap'
            }
        },

        routes: {
            '/routes/:routeId/members': 'showPage'
        },

        routeId: null,

        memberStore: null,

        selectedMember: null
    },

    init: function () {
        this.memberStore = Ext.create('YourTour.store.RouteMemberStore');
    },

    showPage: function (routeId) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.member.MemberMainView'));

        var me = this, view = me.getMemberMainView(), memberList = me.getMemberList();
        me.queryRouteMember(routeId, function(store){
            var data = {routeId:routeId, store:store};
            view.bindData(data);

            memberList.setStore(store);
        })

    },

    queryRouteMember:function(routeId, callback){
        var options = {
            model: 'YourTour.model.UserModel',
            url: '/route/' + routeId + '/members/query',
            success: function (store) {
                callback(store);
            }
        };
        this.getApplication().query(options);
    },

    onMemberListItemTap:function(dataview, index, item, record, e){
        this.showMemberDetailView(record, 'cancel');
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
        var me = this, mainview = me.getMemberMainView(), store = mainview.getData().store, pos, arra;
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.member.MemberPositionView'));

        var map = new BMap.Map('map');//指向map的容器
        map.enableScrollWheelZoom(true);
        /*window.setTimeout(function () {
                map.centerAndZoom('上海', 11);
        },2000);*/
        map.centerAndZoom('上海', 11);

        store.each(function(member){
            if(member.get('position') != '') {
                pos = member.get('position').split(',');
                map.clearOverlays();
                var new_point = new BMap.Point(pos[0], pos[1]);
                var marker = new BMap.Marker(new_point);  // 创建标注
                map.addOverlay(marker);              // 将标注添加到地图中
                map.panTo(new_point);
            }
        });
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

    /**
     *
     * @param record
     * @param action
     */
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

    /**
     *
     */
    onMemberAddTap: function () {
        var me = this, mainView = me.getMemberMainView(), memberView = me.getMemberView(), data = {};
        var routeData = mainView.getData(), memberData = memberView.getData();

        data.routeId = routeData.routeId;
        data.userId = memberData.get('id');
        data.role = 'MEMBER';

        this.getApplication().callService({
            url: 'route/members/save',
            method: "POST",
            params: data,
            success: function (response) {
                var store = routeData.store;
                store.add(memberData);
                Ext.ComponentManager.get('MainView').pop(3);
            }
        });
    },

    /**
     *
     */
    onMemberDeleteTap: function () {
        var me = this, mainView = me.getMemberMainView(), memberView = me.getMemberView(), data = {};
        var routeData = mainView.getData(), memberData = memberView.getData();
        var routeId = routeData.routeId, memberId = memberData.get('id');

        this.getApplication().callService({
            url: 'route/members/' + routeId + '/' + memberId + '/delete',
            method: "GET",
            success: function (response) {
                var store = routeData.store;
                store.remove(memberData);
                Ext.ComponentManager.get('MainView').pop();
            }
        });
    },

    showMemberSelectionView:function(routeId, callback){
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.member.MemberSelectionView', {callback:callback}));

        var me = this, view = me.getMemberSelectionView(), memberList = me.getMemberSelectionList();

        me.queryRouteMember(routeId, function(store){
            var data = {routeId:routeId, store:store};
            view.bindData(data);

            memberList.setStore(store);
        })
    },

    onMemberSelectionItemTap:function(dataview, index, item, record, e){
        var me = this, view = me.getMemberSelectionView(), callback = view.getCallback();;

        if(callback){
            callback(record);
        }
    }
});
