Ext.define('YourTour.controller.route.RouteSchedulePlanCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires: ['YourTour.store.RouteStore', 'YourTour.model.RouteModel'],

    config: {
        refs: {
            //行程规划列表页面
            routeSchedulePlanView: '#RouteSchedulePlanView',
            scheduleList: '#RouteSchedulePlanView #scheduleList',
            scheduleItemList: '#RouteSchedulePlanView #scheduleItemList',
            toolbar: '#RouteSchedulePlanView #toolbar',

            //行程安排编辑页面
            routeActivityEditView: '#RouteActivityEditView',
            routeProvisionEditView: '#RouteProvisionEditView',
            routeScheduleEditView: '#RouteScheduleEditView',
            routeDiscussView: '#RouteDiscussView',
            settingView: '#RouteSettingView',
            placeChangeView: '#PlaceChangeView',

            //行程目的地编辑页面
            routePlaceEditView: '#RoutePlaceEditView',

            //行程主题选择页面

            //行程时间设置页面
            timeSelectionView: '#TimeSelectionView',

            //智能匹配线路结果页面
            routeRecommendListView: '#RouteRecommendListView',
            routeRecommendList: '#RouteRecommendListView #routeRecommendList',

            //智能匹配线路结果页面
            routeRecommendIntroductionView: '#RouteRecommendIntroductionView',
            introductionItem: '#RouteRecommendIntroductionView #introductionItem',
            expertItem: '#RouteRecommendIntroductionView #expertItem',
            scheduleItem: '#RouteRecommendIntroductionView #scheduleItem'
        },

        control: {
            '#RouteSchedulePlanView #discuss': {
                tap: 'onPlanViewRouteDiscussTapHandler'
            },

            '#RouteSchedulePlanView #scheduleList': {
                itemtap: 'onPlanViewScheduleTapHandler'
            },

            '#RouteSchedulePlanView #scheduleItemList': {
                itemtap: 'onPlanViewScheduleListItemTapHandler',
                itemtaphold: 'onPlanViewScheduleListItemTapHolderHandler'
            },

            '#RouteSchedulePlanView #btnAdd': {
                tap: 'onPlanViewAddTapHandler'
            },

            '#RouteSchedulePlanView #btnDelete': {
                tap: 'onPlanViewDeleteTapHandler'
            },

            '#RouteSchedulePlanView #btnCancel': {
                tap: 'onPlanViewCancelTapHandler'
            },

            toolbar: {
                activate: 'onToolbarActivate'
            },

            '#RoutePlaceEditView #btnNext': {
                tap: 'onRoutePlaceNextTapHandler'
            },

            '#RoutePlaceEditView #btnAdd': {
                tap: 'onRoutePlaceAddTapHandler'
            },

            '#RoutePlaceEditView #selectedList': {
                itemtap: 'onSelectedListItemTapHandler',
                itemoperation: 'onItemRemoveHandler'
            },

            '#RoutePlaceEditView #relatedList': {
                itemoperation: 'onItemAddHandler'
            },

            '#RouteProvisionEditView #save': {
                tap: "onSaveProvisionItem"
            },

            '#toolbar #insertShcedule': {
                tap: 'onInsertSchedule'
            },

            '#RouteScheduleEditView #btnSave': {
                tap: 'onSaveSchedule'
            },

            '#toolbar #newActivity': {
                tap: 'onNewScheduleActivity'
            },

            '#RouteActivityEditView #btnDelete': {
                tap: "onDeleteScheduleActivity"
            },

            '#RouteActivityEditView #btnSave': {
                tap: 'onSaveScheduleActivity'
            },

            '#RouteActivityEditView #btnItemAdd': {
                tap: 'onActivityItemAddTap'
            },

            '#RouteActivityEditView #itemList': {
                itemtap: 'onActivityItemTap'
            },

            '#RouteActivityEditView #btnServiceAdd': {
                tap: 'onActivityServiceAddTap'
            },

            '#RouteActivityEditView #serviceList': {
                itemtap: 'onActivityServiceItemTap'
            },

            '#RouteActivityEditView #resName': {
                tap: 'onShowResourceView'
            },

            '#RouteSettingView #fromPlace': {
                tap: "onFromPlaceTap"
            },

            '#RouteSettingView #toPlaces': {
                tap: "onToPlacesTap"
            },

            '#RouteSettingView #btnRefer': {
                tap: 'OnRouteReferTap'
            },

            '#RouteSettingView #btnCutomized': {
                tap: 'onRouteCustomizeTap'
            },

            '#LineRecommendListView #btnCustomize': {
                tap: 'onRouteCustomizeTap'
            },

            '#RouteRecommendListView #routeRecommendList': {
                itemtap: 'onRouteRecommendItemTapHandler'
            },

            '#RouteRecommendIntroductionView #recommendCarousel': {
                activateitem: 'onRecommendActivateItem'
            },

            '#RouteRecommendIntroductionView #btnClone': {
                tap: 'onRouteCloneTap'
            }
        },

        route: null,

        index: 0
    },

    /*************************************************************************************************
     * 行程目的地部分
     ************************************************************************************************/
    createNewRoute: function () {
        var me = this;

        me.route = {toPlaces: ''};
        var controller = this.getApplication().getController('PlaceSelectionCtrl');
        controller.showPage(function (dataview, index, item, record) {
            me.showPlaceEditView(record);
        });
    },

    showPlaceEditView: function (place) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RoutePlaceEditView'));

        var me = this, route = me.route;
        var toPlaces = route.toPlaces;
        if (toPlaces != '') {
            toPlaces += '|';
        }
        toPlaces += place.get('id') + ',' + place.get('name');
        route.toPlaces = toPlaces;

        this.fillPlaceEditView();
    },

    /**
     * 显示目的地设置界面
     */
    fillPlaceEditView: function () {
        var me = this, view = me.getRoutePlaceEditView();
        var toPlaces = me.route.toPlaces, arrPlaces = toPlaces.split('|');

        var selectedList = view.down('#selectedList');
        var selectedStore = selectedList.getStore();
        if (selectedStore == null) {
            selectedStore = Ext.create('YourTour.store.AjaxStore', {model: 'YourTour.model.PlaceModel'});
            selectedList.setStore(selectedStore);
        }

        Ext.Array.forEach(arrPlaces, function (place) {
            var places = place.split(',');
            var model = Ext.create('YourTour.model.PlaceModel');
            model.set('id', places[0]);
            model.set('name', places[1]);
            model.set('action', 'delete');
            selectedStore.add(model);
        });

        me.getRelatedPlaces(selectedStore.last());
    },

    onRoutePlaceAddTapHandler: function () {
        Ext.ComponentManager.get('MainView').pop();
    },

    /**
     * 已选择目的地点击触发事件处理
     * @param dataview
     * @param index
     * @param item
     * @param record
     */
    onSelectedListItemTapHandler: function (dataview, index, item, record) {
        this.getRelatedPlaces(record);
    },

    /**
     * 显示相关目的地
     * @param place
     */
    getRelatedPlaces: function (place) {
        var me = this, view = me.getRoutePlaceEditView();

        var message = view.down('#message');
        message.setHtml('选择' + place.get('name') + '的人还选择了');

        //获取相关的目的地
        var options = {
            model: 'YourTour.model.RouteModel',
            url: '/place/relative/' + place.get('id') + '/query',
            success: function (store) {
                var relatedList = view.down('#relatedList');
                relatedList.setStore(store);
            }
        };
        me.getApplication().query(options);
    },

    /**
     * 删除已选择目的地
     * @param dataview
     * @param action
     * @param record
     */
    onItemRemoveHandler: function (dataview, action, record) {
        var me = this, route = me.route, toPlaces = route.toPlaces;

        var store = dataview.getStore();
        store.remove(record);

        var arrPlaces = toPlaces.split('|');
        var newPlaces = '';
        Ext.Array.forEach(arrPlaces, function (place) {
            var places = place.split(',');
            if(places[0] != record.get('id')){
                if(newPlaces != '' ) newPlaces += '|';

                newPlaces += place;
            }
        });

        route.toPlaces = new Places;
    },

    /**
     * 相关目的地添加
     * @param dataview
     * @param action
     * @param record
     */
    onItemAddHandler: function (dataview, action, record) {
        var me = this, view = me.getRoutePlaceEditView(), store = dataview.getStore();
        store.remove(record);

        var selectedList = view.down('#selectedList'), selectedStore = selectedList.getStore();
        record.set('action', 'delete');
        selectedStore.add(record);

        me.route.toPlaces += '|' + record.get('id') + ',' + record.get('name');

        me.getRelatedPlaces(record);
    },

    /**
     * 进入行程时间设置界面
     */
    onRoutePlaceNextTapHandler: function () {
        this.showTimeSelectionView();
    },

    /*************************************************************************************************
     * 行程时间设置部分
     ************************************************************************************************/
    showTimeSelectionView: function () {
        var me = this, route = me.route;
        var controller = this.getApplication().getController('CommonMainCtrl');
        controller.showTimeSelectionView(new Date(), function (startDate, endDate, duration) {
            route.startDate = startDate;
            route.endDate = endDate;
            route.duration = duration;

            me.showRouteSettingView();
        });
    },

    showRouteSettingView:function(){
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteSettingView'));

        var me = this, view = me.getSettingView(), route = me.route;
        var startDate = view.down('#startDate');
        startDate.setText(route.startDate);

        var endDate = view.down('#endDate');
        endDate.setText(route.endDate);

        var duration = view.down('#duration');
        duration.setText(route.duration + '天');

        var toPlaces = view.down('#toPlaces');
        toPlaces.setPair(route.toPlaces);
    },

    getRouteSettingInfo: function () {
        var me = this, view = me.getSettingView(), route = me.route;

        var id = view.down('#id');
        var name = view.down('#name');
        var startDate = view.down('#startDate');
        var endDate = view.down('#endDate');
        var fromPlace = view.down('#fromPlace');
        var toPlaces = view.down('#toPlaces');
        var adultNum = view.down('#adultNum');
        var childNum = view.down('#childNum');
        var olderNum = view.down('#olderNum');

        route.id = id.getValue();
        route.name = name.getValue();
        route.startDate = startDate.getValue();
        route.endDate = endDate.getValue();
        route.fromPlace = fromPlace.getPair();
        route.toPlaces = toPlaces.getPair();
        route.adultNum = adultNum.getValue();
        route.childNum = childNum.getValue();
        route.olderNum = olderNum.getValue();

        console.log(route);
        return route;
    },

    OnRouteReferTap: function () {
        var me = this;

        var route = this.getRouteSettingInfo();
        me.getRecommendRoutes(5, route.toPlaces)
    },

    /*************************************************************************************************
     * 行程智能匹配部分
     ************************************************************************************************/
    /**
     *
     * @param duration
     * @param places
     */
    getRecommendRoutes: function (duration, places) {
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

        var pagebody = view.down('#pagebody');
        var options = {
            model: 'YourTour.model.RouteModel',
            url: '/routes/recommend/' + ids + '/' + duration,
            success: function (store) {
                var routeRecommendList = me.getRouteRecommendList();
                routeRecommendList.setStore(store);
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
    onRouteRecommendItemTapHandler: function (dataview, index, item, record) {
        var me = this;
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteRecommendIntroductionView'));
        var view = me.getRouteRecommendIntroductionView();
        view.setData(record);

        var headerbar = view.down('#headerbar');
        headerbar.setTitle(record.get('name'));

        //处理概述部分
        var introductionItem = me.getIntroductionItem();
        introductionItem.updateData(record);

        //处理达人部分
        var expertItem = me.getExpertItem();
        expertItem.updateData(record.expertStore.first());

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
        var me = this, view = me.getRouteRecommendIntroductionView(), record = view.getData();
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

    /**
     *
     */
    onRouteCustomizeTap: function () {
        var me = this, route = me.getRouteSettingInfo();

        this.getApplication().callService({
            url: '/routes/main/save',
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

    /*************************************************************************************************
     * 行程计划安排页面部分
     ************************************************************************************************/
    /**
     *
     * @param store
     */
    updateRouteSchedule: function (store) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteSchedulePlanView'));

        var me = this;
        var routeSchedulePlanView = me.getRouteSchedulePlanView();
        routeSchedulePlanView.bindData(store);

        var record = store.first();

        var headerbar = routeSchedulePlanView.down('#headerbar');
        headerbar.setTitle(record.get('name'));

        var schedulesStore = record.schedulesStore;
        var hidden = false, type;
        schedulesStore.each(function (item, index) {
            type = item.get('type');

            if (index > 0) {
                if (type == 'Provision' || type == 'Schedule') {
                    hidden = true;
                }

                item.set('planhidden', hidden)
            }
        });

        var scheduleList = me.getScheduleList();
        scheduleList.setStore(record.schedulesStore);

        var scheduleItemList = me.getScheduleItemList();
        scheduleItemList.setStore(record.schedulesStore);

        scheduleList.select(record.schedulesStore.first());
    },

    /**
     *
     * @param dataview
     * @param index
     * @param item
     * @param record
     */
    onPlanViewScheduleTapHandler: function (dataview, index, item, record) {
        var store = dataview.getStore();
        var count = store.getAllCount(), type, data, hidden = false;

        for (var i = 0; i < count; i++) {
            data = store.getAt(i);
            if (i > index) {
                type = data.get('type');
                if (type == 'Provision' || type == 'Schedule') {
                    hidden = true;
                    continue;
                }

                data.set('planhidden', hidden);
            } else {
                data.set('planhidden', true);
            }
        }
    },

    /**
     * 点击行程编辑页面行程项目出发函数
     * @param dataview
     * @param index
     * @param item
     * @param record
     * @param e
     */
    onPlanViewScheduleListItemTapHandler: function (dataview, index, item, record, e) {
        this.index = index;
        if (!dataview.config.istplhold) {
            var type = record.get('type');
            if (type == 'ProvisionItem') {
                this.editProvisionItem(record);
            } else if (type == 'Schedule') {
                this.editSchedule(record);
            } else if (type == 'ScheduleItem') {
                this.editScheduleActivity(record);
            }
        }
    },

    /**
     * 行程列表长按事件处理，显示删除按钮
     * @param dataview
     * @param index
     * @param item
     * @param record
     * @param e
     */
    onPlanViewScheduleListItemTapHolderHandler: function (dataview, index, item, record, e) {
        var view = this.getRouteSchedulePlanView();

        var btnAdd = view.down('#btnAdd');
        btnAdd.hide();

        var btnDelete = view.down('#btnDelete');
        btnDelete.show();

        var btnCancel = view.down('#btnCancel');
        btnCancel.show();

        dataview.config.istplhold = true;
        dataview.addCls('scheduleItemList');
    },

    /**
     * 添加行程信息
     */
    onPlanViewAddTapHandler: function () {
        var me = this, scheduleList = me.getScheduleList();
        var items = scheduleList.getSelection();
        if (items.length == 1) {
            var item = items[0];
            var type = item.get('type');
            if (type == 'Provision') {
                this.createProvisionItem();
            } else if (type == 'Schedule') {
                this.createScheduleActivity();
            }
        }
    },

    /**
     * 删除选中的行程信息
     */
    onPlanViewDeleteTapHandler: function () {
        var me = this, dataview = me.getScheduleItemList(), store = dataview.getStore();
        var items = dataview.getSelection();
        if (items.length == 1) {
            var item = items[0];
            var type = item.get('type');

            var callback = function (response) {
                store.remove(item);
            };

            if (type == 'ProvisionItem') {
                this.deleteProvisionItem(item, callback);
            } else if (type == 'ScheduleItem') {
                this.deleteScheduleActivity(item, callback);
            }
        }
    },

    /**
     * 取消删除
     */
    onPlanViewCancelTapHandler: function () {
        var me = this, dataview = me.getScheduleItemList(), view = this.getRouteSchedulePlanView();
        dataview.config.istplhold = false;
        dataview.removeCls('scheduleItemList');

        var btnAdd = view.down('#btnAdd'), btnDelete = view.down('#btnDelete'), btnCancel = view.down('#btnCancel');
        btnAdd.show();
        btnDelete.hide();
        btnCancel.hide();
    },

    /*************************************************************************************************
     * 行程计划中准备实现功能部分
     ************************************************************************************************/
    /**
     * 新建准备事项
     */
    createProvisionItem: function () {
        var store = this.getRouteScheduleList().getStore();
        var i;
        var len = store.getData().length;
        for (i = this.index; i < len; i++) {
            if (store.getAt(i).get('type') == 'Schedule') {
                break;
            }
        }

        this.editProvisionItem();
    },

    /**
     * 编辑行程准备事项
     */
    editProvisionItem: function (provisionItem) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteProvisionEditView'));

        var me = this;
        var view = me.getRouteProvisionEditView();

        var id = view.down('#id');
        if (provisionItem != undefined) {
            var title = view.down('#title');
            var memo = view.down('#memo');

            title.setValue(provisionItem.get('title'));
            memo.setValue(provisionItem.get('memo'));

            id.setValue(provisionItem.get('id'));
        } else {
            id.setValue('0');
        }

        var routeId = view.down('#routeId');
        routeId.setValue(this.routeId);
    },

    /**
     *
     */
    deleteProvisionItem: function (item, callback) {
        this.getApplication().callService({
            url: '/routes/provision/' + item.get('id') + '/delete',
            method: "GET",
            success: function (response) {
                callback(response);
            }
        });
    },

    /**
     *
     */
    onSaveProvisionItem: function (record) {
        var me = this;

        var view = this.getRouteProvisionEditView();
        var title = view.down('#title');
        var memo = view.down('#memo');
        var id = view.down('#id');

        var data = {};
        data.routeId = this.routeId;
        data.id = id.getValue();

        if (data.id == '0')
            data.index = me.getNewIndex();
        else
            data.index = me.getIndex();

        data.title = title.getValue();
        data.memo = memo.getValue();

        this.getApplication().callService({
            url: '/routes/' + this.routeId + '/provision/save',
            method: "POST",
            data: Ext.JSON.encode(data),
            success: function (response) {
                var scheduleStore = me.getRouteScheduleList().getStore();
                if (data.id == '0') {
                    data.id = response;
                    data.type = 'ProvisionItem';
                    var schedule = Ext.create('YourTour.model.RouteScheduleModel', data);
                    scheduleStore.insert(data.index, schedule);
                } else {
                    var provision = scheduleStore.getAt(me.index);
                    provision.set('title', data.title);
                    provision.set('memo', data.memo);
                }

                Ext.ComponentManager.get('MainView').pop();
            }
        });
    },

    /*************************************************************************************************
     * 行程计划中日程部分实现代码
     ************************************************************************************************/
    onInsertSchedule: function () {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteScheduleEditView'));
    },

    /**
     * 保存日程
     */
    onSaveSchedule: function () {
        var me = this;

        var view = this.getRouteScheduleEditView();
        var date = view.down('#date');
        date.setValue(record.get('date'));

        var title = view.down('#title');
        title.setValue(record.get('title'));

        var memo = view.down('#memo');
        memo.setValue(record.get('memo'));

        this.getApplication().callService({
            url: '/routes/' + me.getRouteId() + '/schedule/save',
            method: "POST",
            params: data,
            success: function (response) {
                var scheduleItemList = me.getScheduleItemList(), schedules = scheduleItemList.getSelection();

                if(schedules.length == 1) {
                    var schedule = schedules[0];

                    schedule.set('title', data.title);
                    schedule.set('memo', data.memo);
                }

                Ext.ComponentManager.get('MainView').pop();
            }
        });
    },

    /**
     *编辑日程
     */
    editSchedule: function (record) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteScheduleEditView'));

        var view = this.getRouteScheduleEditView();
        var date = view.down('#date');
        date.setValue(record.get('date'));

        var title = view.down('#title');
        title.setValue(record.get('title'));

        var memo = view.down('#memo');
        memo.setValue(record.get('memo'));
    },

    /*************************************************************************************************
     * 行程计划中日程详细安排部分实现代码
     ************************************************************************************************/

    createScheduleActivity: function () {
        var controller = this.getApplication().getController('ResourceMainCtrl');
        controller.showSelectionPage();
    },

    /**
     * 编辑日程安排
     */
    editScheduleActivity: function (record) {
        Ext.ComponentManager.get('MainView').pop('RouteSchedulePlanView');
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteActivityEditView'));

        var me = this, view = this.getRouteActivityEditView();

        //处理计划部分
        var options = {
            model: 'YourTour.model.RouteActivityModel',
            url: '/routes/activity/' + record.get('id'),
            success: function (store) {
                var activity = store.first();
                view.setData(activity);
            }
        };
        me.getApplication().query(options);
    },

    /**
     * 新增日程安排
     */
    addScheduleActivity: function (resource) {
        var me = this;
        var scheduleList = me.getScheduleList(), schedules = scheduleList.getSelection();
        var planView = this.getRouteSchedulePlanView(),route = planView.getData().first(), scheduleStore = route.schedulesStore;

        var data = {};
        data.title = resource.get('name');
        data.status = 'DRAFT';

        data.schedule = {};
        data.schedule.id = schedules[0].get('id');

        data.resource = {};
        data.resource.id = resource.get('id');
        data.resource.type = resource.get('type');

        this.saveScheduleActivity(data, function (response) {
            data.id = response;
            data.type = 'ScheduleItem';
            var schedule = Ext.create('YourTour.model.RouteScheduleModel', data);
            schedule.set('planhidden', false);
            scheduleStore.add(schedule);

            me.editScheduleActivity(schedule);
        });
    },

    /**
     * 保存日程安排
     */
    onSaveScheduleActivity: function () {
        var me = this, view = this.getRouteActivityEditView(), activity = view.getData();
        var scheduleList = me.getScheduleList(), schedule = scheduleList.getSelection()[0];
        var scheduleItemList = me.getScheduleItemList() , scheduleStore = scheduleItemList.getStore();

        var data = {};
        data.status = 'VALIDATED';
        data.schedule = {};

        data.schedule.id = schedule.get('id');
        data.date = schedule.get('date');

        data.resource = {};

        var resource = activity.resourceStore.first();
        data.resource.id = resource.get('id');
        data.resource.type = resource.get('type');

        data.id = activity.get('id');

        var title = view.down('#title');
        data.title = title.getValue();

        var memo = view.down('#memo');
        data.memo = memo.getValue();

        var startTime = view.down('#startTime');
        data.startTime = startTime.getValue();

        var endTime = view.down('#endTime');
        data.endTime = endTime.getValue();

        this.saveScheduleActivity(data, function (response) {
            var activity = scheduleItemList.getSelection()[0];
            activity.set('title', data.title);
            activity.set('memo', data.memo);

            Ext.ComponentManager.get('MainView').pop();
        });
    },

    /**
     * 保存日程安排
     */
    saveScheduleActivity: function (data, handle) {
        var planView = this.getRouteSchedulePlanView(), store = planView.getData(), routeId = store.first().get('id');

        this.getApplication().callService({
            url: '/routes/' + routeId + '/activity/save',
            method: "POST",
            params: data,
            success: function (response) {
                handle(response);
            }
        });
    },

    deleteScheduleActivity: function (item, callback) {
        this.getApplication().callService({
            url: '/routes/activity/' + item.get('id') + '/delete',
            method: "GET",
            success: function (response) {
                callback(response);
            }
        });
    },

    onShowResourceView:function(){
        var view = this.getRouteActivityEditView();
        var activity = view.getData();
        var resource = activity.resourceStore.first();
        var resourceController = this.getApplication().getController('ResourceMainCtrl');
        resourceController.showResourcePage(resource);
    },

    /**
     * 获取新增项目的位置索引
     */
    getNewIndex: function () {
        var newIndex = 0;
        var store = this.getRouteScheduleList().getStore();
        var length = store.getData().length;

        var planView = this.getRouteSchedulePlanView();
        var schedule = planView.getData();

        var index = store.indexOf(schedule);

        var parentId;
        if (schedule.get('type') == 'Schedule' || schedule.get('type') == 'Provision') {
            parentId = schedule.get('id');

            var count = index;
            var item;
            for (var i = index; i < length; i++) {
                item = store.getAt(i);
                if (item.get('id') != parentId && item.get('parentId') != parentId) {
                    break;
                }

                count += 1;
            }

            newIndex = count;
        } else {
            parentId = schedule.get('parentId');
            newIndex = schedule.get('index');

            var item;
            for (var i = index; i < length; i++) {
                item = store.getAt(i);

                if (item.get('parentId') != parentId) break;

                item.set('index', item.get('index') + 1);
            }
        }

        return newIndex;
    },

    onRouteDiscuss: function () {
        var sessionId = '111';
        this.redirectTo('/message/session/' + sessionId);
    },

    onRecommendCarouselItemChange: function (carousel, value, oldValue, eOpts) {
        var me = this;
        var view = me.getRouteRecommendIntroductionView();
        var headerbar = view.down('#headerbar');

        if (value.getItemId() == 'scheduleList') {
            headerbar.setTitle('行程安排');
            var record = view.getData();
            var routeId = record.get('id');

            var store = me.getScheduleList().getStore();
            if (!store) {
                var showView = function () {
                    var record = store.first();

                    var scheduleList = me.getScheduleList();
                    scheduleList.setStore(record.schedulesStore);
                };

                store = Ext.create('YourTour.store.AjaxStore', {
                    storeId: 'recommendItem',
                    model: 'YourTour.model.RouteModel'
                });
                store.getProxy().setUrl(YourTour.util.Context.getContext('/routes/' + routeId + '/query'));
                store.load(showView, this);
            }
        } else {
            headerbar.setTitle('行程概要');
        }
    },


    /**
     * 日程编辑页面点击添加服务项触发函数
     */
    onActivityServiceAddTap: function () {
        var serviceController = this.getApplication().getController('ServiceMainCtrl');

        serviceController.showExpertServices();
    },

    /**
     * 日程编辑页面点击添加服务项触发函数
     */
    onActivityServiceItemTap: function (dataview, index, item, record, e) {
        var serviceController = this.getApplication().getController('ServiceMainCtrl');

        serviceController.showExpertService(record, 'cancel');
    },

    /**
     * 预订服务项
     * @param service
     * @param handler
     */
    bookService: function (service, handler) {
        var view = this.getRouteActivityEditView();
        var activity = view.getData();
        var activityId = activity.get('id');

        var serviceId = service.get('id');

        this.getApplication().callService({
            url: '/routes/service/activity/' + activityId + '/' + serviceId + '/save',
            method: "POST",
            success: function (data) {
                service.set
                var serviceList = view.down('#serviceList');
                var store = serviceList.getStore();
                store.add(service);

                handler();
            },
        });
    },

    /**
     * 取消服务项
     * @param service
     * @param handler
     */
    cancelService: function (service, handler) {
        var view = this.getRouteActivityEditView();

        var serviceId = service.get('id');
        this.getApplication().callService({
            url: '/routes/service/' + serviceId + '/delete',
            method: "POST",
            success: function (data) {
                var serviceList = view.down('#serviceList');
                var store = serviceList.getStore();
                store.remove(service);

                handler();
            },
        });
    },

    /**
     * 日程编辑页面点击添加活动项触发函数
     */
    onActivityItemAddTap: function () {
        var view = this.getRouteActivityEditView();
        var activity = view.getData();
        var resource = activity.resourceStore.first();

        var title = resource.get('name');

        var controller = this.getApplication().getController('ResourceMainCtrl');

        controller.showResourceActivities(title, resource);
    },

    /**
     * 添加日程中的活动项目
     * @param resourceActivityItem
     */
    addScheduleActivityItem: function (resourceActivityItem, handler) {
        var view = this.getRouteActivityEditView();
        var activity = view.getData();
        var activityId = activity.get('id');

        var resourceActivityItemId = resourceActivityItem.get('id');
        this.getApplication().callService({
            url: '/routes/activity/' + activityId + '/item/' + resourceActivityItemId + '/save',
            method: "POST",
            success: function (data) {
                resourceActivityItem.set('resourceActivityItemId', resourceActivityItemId);
                resourceActivityItem.set('id', data);

                var itemList = view.down('#itemList');
                var store = itemList.getStore();
                store.add(resourceActivityItem);

                handler();
            },
        });
    },

    /**
     * 删除日程中的活动项目
     * @param activityItem
     */
    cancelScheduleActivityItem: function (activityItem) {
        var view = this.getRouteActivityEditView();

        var activityItemId = activityItem.get('id');
        this.getApplication().callService({
            url: '/routes/activity/item/' + activityItemId + '/delete',
            method: "POST",
            success: function (data) {
                var itemList = view.down('#itemList');
                var store = itemList.getStore();
                store.remove(activityItem);

                handler();
            },
        });
    },

    /**
     * 日程页面活动项点击出发函数
     * @param dataview
     * @param index
     * @param item
     * @param record
     * @param e
     */
    onActivityItemTap: function (dataview, index, item, record, e) {
        var controller = this.getApplication().getController('ResourceMainCtrl');

        controller.showResourceActivity(record);
    }
});