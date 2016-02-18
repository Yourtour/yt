Ext.define('YourTour.controller.PlaceMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
        refs: {
            placeMainView: '#PlaceMainView',
            placeRouteList: '#PlaceMainView #placeRouteList'
        },

        control: {
            '#PlaceMainView #placeChatRoom': {
                tap: 'showPlaceChatRoom'
            },

            '#PlaceMainView #placeExpertItem #placeExpertList': {
                itemtap: 'showExpertInfo'
            },

            '#PlaceMainView #placeMoreExperts': {
                tap: 'showMoreExperts'
            },

            '#PlaceMainView #placeRouteList': {
                itemtap: 'showRouteInfo'
            },

            '#PlaceMainView #placeMoreRoutes': {
                tap: 'showMoreRoutes'
            },

            '#PlaceMainView #placeAlongList': {
                itemtap: 'showAlongInfo'
            },

            '#PlaceMainView #placeMoreAlongs': {
                tap: 'showMoreAlongs'
            },
        },

        placeId: null
    },

    init: function () {
    },

    /**
     * 进入目的地首页
     * @param placeId
     * @param placeName
     */
    showPage: function (placeId, placeName) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.place.PlaceMainView'));

        placeId = 6;
        this.placeId = placeId;

        this.refreshHomeData();
    },

    showPlaceChatRoom: function() {
        console.log('showPlaceChatRoom');
       var placeId = this.placeId;
        var ctrl = this.getApplication().getController('MessageMainCtrl');
        ctrl.showMainPage({type: 'place', roomCode: placeId});
    },

    /**
     * 刷新目的地首页数据
     */
    refreshHomeData: function () {
        var me = this, placeId = me.placeId,
            mainview = me.getPlaceMainView(),
            expertItem = mainview.down('#placeExpertItem'),
            expertList = expertItem.down('#placeExpertList'),
            routeItem = mainview.down('#placeRouteItem'),
            placeRouteList = me.getPlaceRouteList(),
            alongItem = mainview.down('#placeAlongItem'),
            alongList = alongItem.down('#placeAlongList');

        var options = {
            model: 'YourTour.model.PlaceModel',
            url: '/place/' + placeId + '/main',
            success: function (store) {
                var place = store.first();
                mainview.setData(place);

                if(place) {
                    var expertStore = place.expertsStore;
                    if (expertStore && expertStore.getAllCount() > 0) {
                        expertItem.show();
                        expertList.setStore(expertStore);
                    }

                    var routeStore = place.routesStore;
                    if (routeStore && routeStore.getAllCount() > 0) {
                        var store = Ext.create('YourTour.store.AjaxStore',{model:'YourTour.model.RouteModel'});
                        routeStore.each(function(record, index){
                            if(index < 5) {
                                store.add(record);
                            }
                        });

                        placeRouteList.setStore(store);
                    }

                    /*var alongStore = place.alongsStore;
                    if (alongStore && alongStore.getAllCount() > 0) {
                        alongItem.hide();
                        alongList.setStore(alongStore);
                    }*/
                }
            }
        };
        me.getApplication().query(options);
    },

    /**
     * 显示达人详情信息
     * @param dataview
     * @param index
     * @param item
     * @param record
     */
    showExpertInfo:function(dataview, index, item, record){
        var controller = this.getApplication().getController('ExpertMainCtrl');
        controller.showExpertInfo(record.get('id'), record);
    },

    /**
     * 显示更多目的地达人
     */
    showMoreExperts:function(){
        var me = this,
            placeId = me.placeId,
            mainview = me.getPlaceMainView(),
            place = mainview.getData();

        var controller = this.getApplication().getController('ExpertMainCtrl');
        controller.showExpertList(placeId, place.expertsStore);
    },


    /**
     * 显示线路信息
     * @param dataview
     * @param index
     * @param item
     * @param record
     */
    showRouteInfo:function(dataview, index, item, record){
        var controller = this.getApplication().getController('route.RouteMainCtrl');
        controller.showRouteInfo(record);
    },

    /**
     * 显示更多目的地线路
     */
    showMoreRoutes:function(){
        var me = this,
            mainview = me.getPlaceMainView(),
            place = mainview.getData();

        var controller = this.getApplication().getController('route.RouteMainCtrl');
        controller.showRouteListView(place.routesStore);
    },

    /**
     * 显示结伴信息
     * @param dataview
     * @param index
     * @param item
     * @param record
     */
    showAlongInfo:function(dataview, index, item, record){
        /*var controller = this.getApplication().getController('AlongMainCtrl');
        controller.showLineInfo(record);*/
    },

    /**
     * 显示更多目的地结伴
     */
    showMoreAlongs:function(){
        var me = this,
            placeId = me.placeId,
            mainview = me.getPlaceMainView(),
            place = mainview.getData();

        var controller = this.getApplication().getController('AlongMainCtrl');
        controller.showLineList(placeId, place.linesStore);
    }
});
