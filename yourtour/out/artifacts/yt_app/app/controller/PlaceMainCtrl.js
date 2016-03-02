Ext.define('YourTour.controller.PlaceMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.view.place.PlaceMainItem'],
    config: {
        refs: {
            placeMainView: '#PlaceMainView',
            imageCarousel: '#PlaceMainView #imageCarousel',
            resourceList:'#PlaceMainView #resourceList',

            placeCommunityView:'#PlaceCommunityView',

            residenceSelectionView:'#ResidenceSelectionView',
            residencePlaceList:'#ResidenceSelectionView #placeList',

        },

        control: {
            resourceList:{
                itemtap:'showResource'
            },

            '#PlaceMainView #placeCommunity': {
                tap: 'showCommunityMainView'
            },

            '#PlaceMainView #placeAlongs': {
                tap: 'showAlongView'
            },

            '#PlaceMainView #placeExperts': {
                tap: 'showExpertView'
            },

            '#PlaceMainView #placeLines': {
                tap: 'showLineView'
            },

            '#PlaceMainView #placePosition': {
                tap: 'showResourceView'
            }
        }
    },

    /**
     * 进入目的地首页
     * @param placeId
     * @param placeName
     */
    showMainPage: function (placeId, placeName) {
        /*Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.place.PlaceMainView'));*/
        var me = this, mainview = me.getPlaceMainView(), resourceList = me.getResourceList(), navPanel = mainview.down('#navPanel');

        mainview.down('#headerbar').setTitle(placeName);

        if(! mainview.getData()) {
            var options = {
                model: 'YourTour.model.PlaceModel',
                url: '/place/' + placeId + '/main',
                success: function (store) {
                    var place = store.first();
                    mainview.setData(place);

                    var url = YourTour.util.Context.getImageResource(place.get('imageUrl'));
                    var style = {};
                    style['background-image'] = 'url(' + url + ')';
                    style['background-repeat'] = 'no-repeat';
                    style['background-position'] = 'center center';
                    style['background-size'] = '100% 100%';
                    navPanel.setStyle(style);

                    var resourceStore = place.resourcesStore;
                    resourceList.setStore(resourceStore);

                    me.showResourceImages(resourceStore.getAt(0));
                }
            };
            me.getApplication().query(options);
        }else{
            me.showResourceImages(resourceList.getStore().getAt(0));
        }
    },

    showCommunityMainView:function(){
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.place.PlaceCommunityView'));
    },

    showResource:function(dataview, index, item, record){
        this.showResourceImages(record);
    },

    showResourceImages:function(resource){
        var me = this, imageCarousel = me.getImageCarousel();

        if(imageCarousel){
            imageCarousel.removeAll(false);
        }

        var images = resource.get('imageUrl').split(';');
        Ext.Array.forEach(images, function(image, index){
            imageCarousel.add({xtype:'PlaceMainItem', data:image})
        });

        if(imageCarousel.getItemLength() > 0){
            imageCarousel.setActiveIndex(0);
        }
    },

    showPlaceChatRoom: function () {
        var me = this, mainview = me.getPlaceMainView(), place = mainview.getData();

        var placeId = place.get('id');
        var ctrl = this.getApplication().getController('MessageMainCtrl');
        ctrl.showMainPage({type: 'place', roomCode: placeId});
    },

    /**
     * 显示目的地附近
     */
    showResourceView:function(){
        var me = this,
            placeId = me.placeId,
            mainview = me.getPlaceMainView(),
            place = mainview.getData();

        var controller = this.getApplication().getController('ResourceMainCtrl');
        controller.showListView4Place(place);
    },

    /**
     * 显示达人详情信息
     * @param dataview
     * @param index
     * @param item
     * @param record
     */
    showExpertInfo: function (dataview, index, item, record) {
        var controller = this.getApplication().getController('ExpertMainCtrl');
        controller.showExpertInfo(record.get('id'), record);
    },

    /**
     * 显示更多目的地达人
     */
    showExpertView: function () {
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
    showRouteInfo: function (dataview, index, item, record) {
        var controller = this.getApplication().getController('route.RouteMainCtrl');
        controller.showRouteInfo(record);
    },

    /**
     * 显示更多目的地线路
     */
    showLineView: function () {
        var me = this,
            mainview = me.getPlaceMainView(),
            place = mainview.getData();

        var controller = this.getApplication().getController('route.RouteMainCtrl');
        controller.showRouteListView(place);
    },

    /**
     * 显示更多目的地结伴
     */
    showAlongView: function () {
        var me = this,
            mainview = me.getPlaceMainView(),
            place = mainview.getData();

        var controller = this.getApplication().getController('AlongMainCtrl');
        controller.showAlongList(place);
    },

    showResidenceSelectionView:function(field){
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.place.ResidenceSelectionView'));

        var me = this, residenceSelectionView = me.getResidenceSelectionView(), residencePlaceList = me.getResidencePlaceList();
        residenceSelectionView.down('#headerbar').setTitle(field.getLabel());

        residencePlaceList.on('itemtap', function(list, index, item, record){
            field.modifyText(record.get('name'));
            field.setValue(record.get('id') +',' + record.get('name'));
            Ext.ComponentManager.get('MainView').pop();
        });

        var options = {
            config:{
                model: 'YourTour.model.PlaceModel',
                grouper:{
                    groupFn: function(record) {
                        return record.get('code').substr(0, 1);
                    },
                    sortProperty: 'code'
                }
            },
            url: '/place/residence/query',
            success: function (store) {
                residencePlaceList.setStore(null);
                residencePlaceList.setStore(store);
            }
        };
        me.getApplication().query(options);
    }
});