Ext.define('YourTour.controller.route.RouteMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires: ['YourTour.store.RouteStore', 'YourTour.view.route.RouteMainItem'],

    config: {
        refs: {
            routeMainView: '#RouteMainView',
            newRoute: '#RouteMainView #new',
            deleteRoute: '#RouteMainView #delete',

            routeImpressionView: '#RouteImpressionView',
            routeImageView: '#RouteImageView',

            //行程列表
            routeListView: '#RouteListView',
            routeList: '#RouteListView #routeList',

            //行程介绍
            routeFormView: '#RouteFormView',
            routeFormCarousel:'#RouteFormView #routeFormCarousel',
            routeButtonGroup:'#RouteFormView #buttonGroup',

            overviewItem: '#RouteFormView #overviewItem',
            expertItem: '#RouteFormView #expertItem',
            scheduleItem: '#RouteFormView #scheduleItem',

            //智能匹配线路结果页面
            routeRecommendListView: '#RouteRecommendListView',
            routeRecommendList: '#RouteRecommendListView #routeRecommendList',
        },

        control: {
            newRoute: {
                tap: 'onRouteNew'
            },

            deleteRoute: {
                tap: 'onRouteDelete'
            },

            '#RouteImpressionView #save': {
                tap: 'onImpressionSave'
            },

            '#RouteImageView #save': {
                tap: 'onImageSave'
            },

            '#RouteMainView #btnRoute': {
                tap: 'onRouteTap'
            },

            '#RouteMainView #btnService': {
                tap: 'onRouteServiceTap'
            },

            '#RouteMainView #btnMember': {
                tap: 'onRouteMemberTap'
            },

            '#RouteMainView #btnCharge': {
                tap: 'onRouteChargeTap'
            },

            '#RouteMainView #impression': {
                tap: 'onImpressionEdit'
            },

            '#LineRecommendListView #btnCustomize': {
                tap: 'onRouteCustomizeTap'
            },

            '#RouteRecommendListView #routeRecommendList': {
                itemtap: 'showRouteFormView'
            },

            '#RouteListView #routeList': {
                itemtap: 'showRouteFormView'
            },

            '#RouteFormView #recommendCarousel': {
                activateitem: 'onRecommendActivateItem'
            },

            '#RouteFormView #btnClone': {
                tap: 'onRouteCloneTap'
            },

            '#RouteFormView #routeFormCarousel': {
                activeitemchange:'showRouteInfo4Carousel'
            },

            '#RouteFormView #buttonGroup': {
                itemtap:'showRouteInfo4Button'
            }
        },

        store: null
    },

    showMainPage: function () {
        var me = this, mainview = me.getRouteMainView(), routePanel = mainview.down('#routePanel');

        var options = {
            model: 'YourTour.model.RouteModel',
            url: '/routes/personal/query',
            success: function (store) {
                var route = store.first();
                mainview.setData(route);

                var url = YourTour.util.Context.getImageResource(route.get('imageUrl'));
                var style = {};
                style['background-image'] = 'url(' + url + ')';
                style['background-repeat'] = 'no-repeat';
                style['background-position'] = 'center center';
                style['background-size'] = '100% 100%';
                routePanel.setStyle(style);
            }
        };
        me.getApplication().query(options);
    },

    onRouteNew: function () {
        var controller = this.getApplication().getController('route.RouteSchedulePlanCtrl')
        controller.showRoutePlanView();
    },

    /**
     *
     */
    onRouteDelete: function () {
        var me = this;
        var routeCarousel = me.getRouteCarousel();
        var index = routeCarousel.getActiveIndex();

        var model = me.store.getAt(index);

        Ext.Ajax.request({
            confirm: '是否要删除当前行程?',
            url: YourTour.util.Context.getContext('/routes/' + model.get('id') + '/delete'),
            method: "GET",
            success: function (response) {
                var respObj = Ext.JSON.decode(response.responseText);
                if (respObj.errorCode != '0') {
                    Ext.Msg.alert(resp.errorText);
                    return;
                }
                ;

                Ext.Msg.alert('删除成功。');
                me.store.removeAt(index);
                routeCarousel.removeAt(index);
            }
        });
    },

    onRouteTap: function () {
        var me = this,
            mainview = me.getRouteMainView(),
            record = mainview.getData(),
            controller = me.getApplication().getController('route.RouteScheduleListCtrl');

        controller.showPage(record.get('id'));
    },

    onRouteServiceTap: function (record) {
        var me = this, mainview = me.getRouteMainView(), record = mainview.getData();

        var controller = me.getApplication().getController('ServiceMainCtrl');
        controller.showRouteService(record);
    },

    onRouteMemberTap: function (record) {
        var me = this, mainview = me.getRouteMainView(), record = mainview.getData();

        var controller = me.getApplication().getController('MemberMainCtrl');
        controller.showMainPage(record);
    },

    onRouteChargeTap: function (record) {
        var me = this, mainview = me.getRouteMainView(), record = mainview.getData();

        var controller = me.getApplication().getController('ChargeMainCtrl');
        controller.showPage(record.get('id'));
    },

    /**
     *
     */
    onImpressionEdit: function (route) {
        var me = this;
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteImpressionView'));

        var impressionView = me.getRouteImpressionView();
        var impression = impressionView.down('#impression');

        impression.setValue(route.get('impression'));
    },

    /**
     *
     */
    onImpressionSave: function () {
        var me = this;

        var data = {};
        data.attr = 'impression';

        var impressionView = me.getRouteImpressionView();
        var impression = impressionView.down('#impression');
        data.attrValue = impression.getValue();

        var routeCarousel = me.getRouteCarousel();
        var index = routeCarousel.getActiveIndex();
        var model = me.store.getAt(index);
        data.routeId = model.get('id');

        var userId = me.getApplication().getUserId();
        data.userId = userId;

        Ext.Ajax.request({
            url: YourTour.util.Context.getContext('/route/members/setting/save'),
            method: "POST",
            data: Ext.JSON.encode(data),
            success: function (response) {
                var respObj = Ext.JSON.decode(response.responseText);
                if (respObj.errorCode != '0') {
                    Ext.Msg.alert(respObj.errorText);
                    return;
                }
                ;

                Ext.ComponentManager.get('MainView').pop();
            }
        });
    },

    onImageSave: function () {
        var routeImageView = this.getRouteImageView();

        var imageSize = routeImageView.down('#imageSize');
    },

    onShareTap: function () {
        window.plugins.socialsharing.share(null, null, 'http://www.baidu.com/img/bdlogo.gif', null);
    },

    /**
     * 显示目的地推荐行程
     * @param store
     * @param place
     */
    showRouteListView: function (place) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteListView'));
        var me = this, listview = me.getRouteListView(), routeList = me.getRouteList();
        routeList.setDefaultType('RouteListDataItemVBox')

        var headerbar = listview.down('#headerbar');
        headerbar.setTitle(place.get('name'));

        me.searchRoute('0',place.get('id'), function(store){
            routeList.setStore(store);
        });
    },

    /*************************************************************************************************
     * 行程智能匹配部分
     ************************************************************************************************/
    /**
     *
     * @param duration
     * @param places
     */
    showRouteRecommendListView: function (duration, places) {
        duration = 5;
        var ids = '', names = '';
        var pArray = places.split('|');
        for (var index = 0; index < pArray.length; index++) {
            if (index > 0) {
                ids = ids + ',';
                names = names + ',';
            }

            var array = pArray[index].split(',');
            ids = ids + array[0];
            names = names + array[1];
        }

        var me = this;
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteRecommendListView'));
        var view = me.getRouteRecommendListView();

        var headerbar = view.down('#headerbar');
        headerbar.setTitle(names);

        me.searchRoute(duration, ids, function(store){
            var routeRecommendList = me.getRouteRecommendList();
            routeRecommendList.setStore(store);
        });
    },

    /**
     * 检索行程
     * @param duration
     * @param places
     * @param calllback
     */
    searchRoute:function(duration, ids, callback){
        var me = this;
        var options = {
            model: 'YourTour.model.RouteModel',
            url: '/routes/recommend/' + ids + '/' + duration,
            success: function (store) {
                callback(store);
            }
        };
        me.getApplication().query(options);
    },

    /**
     *
     * @param dataview
     * @param index
     * @param item
     * @param record
     */
    showRouteFormView: function (dataview, index, item, record) {
        this.showRouteInfo(record);
    },

    /**
     *
     * @param dataview
     * @param index
     * @param item
     * @param record
     */
    showRouteInfo: function (record) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteFormView'));
        var me = this, view = me.getRouteFormView();
        view.setData(record);

        var headerbar = view.down('#headerbar');
        headerbar.setTitle(record.get('name'));

        //处理概述部分
        var overviewItem = me.getOverviewItem();
        overviewItem.updateData(record);

        /*//处理达人部分
         var expertItem = me.getExpertItem();
         expertItem.updateData(record.expertStore.first());
         */
        //处理计划部分
        var options = {
            model: 'YourTour.model.RouteModel',
            url: '/routes/' + record.get('id') + '/query',
            success: function (store) {
                var route = store.first();
                var schedulesStore = route.schedulesStore;

                var type;
                schedulesStore.each(function (record) {
                    type = record.get('type');

                    if (type == 'Provision' || type == 'ProvisionItem') {
                        record.set('planhidden', true);
                    }
                });

                var scheduleItem = me.getScheduleItem();
                scheduleItem.updateData(schedulesStore);
            }
        };
        me.getApplication().query(options);
    },

    /**
     * 行程复制
     */
    onRouteCloneTap: function () {
        var me = this, view = me.getRouteFormView(), record = view.getData();
        var sourceId = record.get('id');

        var route = me.getRouteSettingInfo();

        this.getApplication().callService({
            url: '/routes/' + sourceId + '/clone',
            method: "POST",
            params: route,
            success: function (response) {
                Ext.ComponentManager.get('MainView').reset();

                var store = Ext.create('YourTour.store.AjaxStore', {model: 'YourTour.model.RouteModel'});
                store.add(response);
                var controller = me.getApplication().getController('route.RouteScheduleListCtrl');
                controller.showPage(store);
            }
        });
    },

    showRouteInfo4Carousel:function( carousel, value, oldValue, eOpts){
        var me = this, routeButtonGroup = me.getRouteButtonGroup();

        routeButtonGroup.setActiveItem(carousel.getActiveIndex());
    },

    showRouteInfo4Button:function(buttongroup, button, index){
        var me = this, routeFormCarousel = me.getRouteFormCarousel();
        routeFormCarousel.setActiveItem(index);
    }
});
