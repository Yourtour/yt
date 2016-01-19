Ext.define('YourTour.controller.PlaceSelectionCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
        refs: {
            placeSelectionView: '#PlaceSelectionView',

            placeType: '#PlaceSelectionView #placeType',
            placeList: '#PlaceSelectionView #placeList',

            recommendItem: '#PlaceSelectionView #recommendItem',
            homeItem: '#PlaceSelectionView #homeItem',
            abroadItem: '#PlaceSelectionView #abroadItem'
        },

        control: {
            placeType: {
                itemtap: 'onPlaceTypeItemTapHandler'
            },

            '#PlaceSelectionView #placeCarousel': {
                activateitem: 'onPlacesItemActiveHandler'
            },

            '#PlaceSelectionView #placeList': {
                itemtap: 'onPlaceListItemTapHandler'
            },
        },

        placeStore: null,

        callback: null,
    },

    init: function () {
        this.placeStore = Ext.create('YourTour.store.PlaceStore');
    },

    showPage: function (callback) {
        var me = this;
        me.callback = callback;

        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.place.PlaceSelectionView'));
        var store = Ext.create('Ext.data.Store', {
            data: [
                {code: 'All', name: '全部'},
                {code: 'GAT', name: '港澳台'},
                {code: 'Aisa', name: '亚洲'},
                {code: 'Europe', name: '欧洲'},
                {code: 'Africa', name: '非洲'},
                {code: 'America', name: '美洲'},
                {code: 'Ocean', name: '大洋洲'}
            ],

            fields: [
                {name: 'code', type: 'string'},
                {name: 'name', type: 'string'}
            ]
        });

        this.getPlaceType().setStore(store);
    },

    /**
     *
     * @param carousel
     * @param value
     */
    onPlacesItemActiveHandler: function (carousel, value) {
        /*if(! value.getRecord()){
         var itemId = value.getItemId();

         if(itemId == 'routePlaceRecommendItem'){
         this.getRouteRecommendPlace(value);
         }else if(itemId == 'routePlaceHomeItem'){
         this.getRouteHomePlace(value);
         }else if(itemId == 'routePlaceAbroadItem'){
         this.getRouteAbroadPlace(value);
         }
         }*/

        var itemId = value.getItemId();
        if (itemId == 'recommendItem') {
            this.getRecommendPlaces(value);
        } else if (itemId == 'homeItem') {
            this.getHomePlaces(value);
        } else if (itemId == 'abroadItem') {
            this.getAbroadPlaces(value);
        }
    },

    /**
     * 获取用户推荐目的地
     * @param carouselItem
     */
    getRecommendPlaces: function (carouselItem) {
        var me = this;
        var options = {
            model: 'YourTour.model.PlaceModel',
            url: '/place/recommend/query',
            success: function (store) {
                carouselItem.updateData(store);
            }
        };
        me.getApplication().query(options);
    },

    /**
     * 获取国内荐目的地
     * @param carouselItem
     */
    getHomePlaces: function (carouselItem) {
        var me = this;
        var options = {
            model: 'YourTour.model.PlaceModel',
            url: '/place/China/query',
            success: function (store) {
                store.each(function(place){
                    place.set('hidden', place.get('leaf') == 'true');
                });
                carouselItem.updateData(store);
            }
        };
        me.getApplication().query(options);
    },

    /**
     * 获取国际/港澳台目的地
     * @param carouselItem
     */
    getAbroadPlaces: function (carouselItem) {
        var me = this;
        var options = {
            model: 'YourTour.model.PlaceModel',
            url: '/place/GAT,Aisa,Europe,Africa,America,Ocean/query',
            success: function (store) {
                store.each(function(place){
                    place.set('hidden', place.get('leaf') == 'true');
                });
                carouselItem.updateData(store);
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
     * @param e
     */
    onPlaceListItemTapHandler: function (dataview, index, item, record, e) {
        if (record.get('leaf') == 'true') {

        } else {
            var store = dataview.getStore();
            var len = store.getAllCount();

            var hidden = !item.getExpandable();
            var data;
            for (var i = index + 1; i < len; i++) {
                data = store.getAt(i);

                if (data.get('leaf') == 'false') {
                    break;
                }

                data.set('hidden', hidden)
            }

            item.setExpandable(hidden)
        }
    },

    /**
     *
     */
    onPlaceTypeItemTapHandler: function (dataview, index, item, record, e) {
        var me = this;
        var parentCode = record.get('code');

        var placeList = this.getAbroadItem().down('#placeList');
        var placeStore = placeList.getStore();

        var hidden = false
        placeStore.each(function (place) {
            if(parentCode == 'All') {
                hidden = place.get('leaf') == 'true';
            }else{
                hidden = place.get('parentCode') != parentCode;
            }

            place.set('hidden', hidden);
        })
    },

    /**
     *
     */
    onTap4BtnOk: function () {
        var me = this;
        var selection = this.getSelection();
        Ext.ComponentManager.get('MainView').pop();
        selection.getItems().each(function (item) {
            me.getApplication().getController('route.RouteSchedulePlanCtrl').addPlace(item.model);
        });
    }
});
