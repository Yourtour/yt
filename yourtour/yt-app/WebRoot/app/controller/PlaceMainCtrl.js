Ext.define('YourTour.controller.PlaceMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
        refs: {
            placeMainView: '#PlaceMainView'
        },

        control: {
            '#PlaceMainView #placeExpertList': {
                itemtap: 'showExpertInfo'
            },

            '#PlaceMainView #placeMoreExperts': {
                tap: 'showMoreExperts'
            },

            '#PlaceMainView #placeLineList': {
                itemtap: 'showLineInfo'
            },

            '#PlaceMainView #placeMoreLines': {
                tap: 'showMorelines'
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
        this.placeId = placeId;

        this.refreshHomeData();
    },

    /**
     * 刷新目的地首页数据
     */
    refreshHomeData: function () {
        var me = this, placeId = me.placeId,
            mainview = me.getPlaceMainView(),
            expertItem = mainview.down('#expertItem'),
            expertList = expertItem.down('#expertList'),
            lineItem = mainview.down('#lineItem'),
            lineList = expertItem.down('#lineList'),
            alongItem = mainview.down('#alongItem'),
            alongList = expertItem.down('#alongList');

        var options = {
            model: 'YourTour.model.PlaceModel',
            url: '/place/' + placeId + '/main',
            success: function (store) {
                var place = store.first();
                mainview.setData(place);

                var expertStore = place.expertsStore;
                if (expertStore.getAllCount() > 0) {
                    expertItem.show();
                    expertList.setStore(expertStore);
                }

                var lineStore = place.linesStore;
                if (lineStore.getAllCount() > 0) {
                    lineItem.show();
                    lineList.setStore(lineStore);
                }

                var alongStore = place.alongsStore;
                if (alongStore.getAllCount() > 0) {
                    alongItem.hide();
                    alongList.setStore(alongStore);
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
    showLineInfo:function(dataview, index, item, record){
        var controller = this.getApplication().getController('LineMainCtrl');
        controller.showLineInfo(record);
    },

    /**
     * 显示更多目的地线路
     */
    showMoreLines:function(){
        var me = this,
            placeId = me.placeId,
            mainview = me.getPlaceMainView(),
            place = mainview.getData();

        var controller = this.getApplication().getController('LineMainCtrl');
        controller.showLineList(placeId, place.linesStore);
    },

    /**
     * 显示结伴信息
     * @param dataview
     * @param index
     * @param item
     * @param record
     */
    showAlongInfo:function(dataview, index, item, record){
        var controller = this.getApplication().getController('AlongMainCtrl');
        controller.showLineInfo(record);
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
