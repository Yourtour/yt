Ext.define('YourTour.controller.ServiceMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
        refs: {
            expertServiceListView: '#ExpertServiceListView',
            expertServiceList: '#ExpertServiceListView #ExpertServiceList',

            expertServiceFormView: '#ExpertServiceFormView',

            routeServiceMainView: '#RouteServiceMainView',
            routeServiceList: '#RouteServiceMainView #RouteServiceList',

            routeServiceFormView: '#RouteServiceFormView',

            placeServiceMainView:'#PlaceServiceMainView',
            placeServiceList:'#PlaceServiceMainView #PlaceServiceList',

            routeServiceBookView:'#RouteServiceBookView'
        },

        control: {
            '#RouteServiceMainView #btnAdd': {
                tap: 'showPlaceServiceView'
            },

            '#RouteServiceFormView #btnCancel':{
                tap:'cancelRouteService'
            },

            placeServiceList:{
                itemtap: 'showExpertServiceView'
            },

            expertServiceList: {
                itemtap: 'onListItemTap'
            },

            '#ExpertServiceFormView #btnBook': {
                tap: 'showServiceBookView'
            },

            '#ExpertServiceFormView #btnCancel': {
                tap: 'deleteExpertServiceFavorite'
            },

            '#ExpertServiceFormView #btnFavorite': {
                tap: 'saveExpertServiceFavorite'
            },

            routeServiceList: {
                itemtap: 'showRouteServiceInfo'
            },

            '#RouteServiceBookView #btnSave':{
                tap:'saveServiceBookingInfo'
            }
        }
    },

    init: function () {
    },

    /**
     * 获取达人提供的服务
     * @param expertId
     */
    showExpertServices: function (expertId) {
        var me = this;
        expertId = 282;
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.expert.ExpertServiceListView'));

        var store = Ext.create('YourTour.store.AjaxStore', {model: 'YourTour.model.ExpertServiceModel'});
        var proxy = store.getProxy();
        proxy.setUrl(YourTour.util.Context.getContext('/services/expert/' + expertId));
        store.load(function () {
            me.getExpertServiceList().setStore(store);
        })
    },

    /**
     * 达人服务收藏
     */
    saveExpertServiceFavorite:function(){
        var me = this, view = me.getExpertServiceFormView(), service = view.getData();

        me.getApplication().callService({
            url: '/services/favorite/' + service.get('id') + '/save',
            method: "GET",
            success: function (response) {
                var btnFavorite = view.down('#btnFavorite'), btnCancel = view.down('#btnCancel');

                btnFavorite.hide();
                btnCancel.show();

                service.set('favorited', '1');
            }
        });
    },

    /**
     * 取消达人服务
     */
    deleteExpertServiceFavorite:function(){
        var me = this, view = me.getExpertServiceFormView(), service = view.getData();

        me.getApplication().callService({
            url: '/services/favorite/' + service.get('id') + '/delete',
            method: "GET",
            success: function (response) {
                var btnFavorite = view.down('#btnFavorite'), btnCancel = view.down('#btnCancel');

                btnFavorite.show();
                btnCancel.hide();

                service.set('favorited', '0');
            }
        });
    },

    /**
     * 点击服务列表触发事件处理函数
     * @param dataview
     * @param index
     * @param item
     * @param record
     * @param e
     */
    onListItemTap: function (dataview, index, item, record, e) {
        this.showExpertService(record, 'none');
    },

    /**
     * 显示达人服务明细
     * @param record
     * @param action
     */
    showExpertService: function (record, action) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.expert.ExpertServiceFormView'));
        var view = this.getExpertServiceFormView();
        view.setData(record);

        var headerbar = view.down('#headerbar');
        headerbar.setTitle(record.get('title'));

        var memo = view.down('#memo');
        memo.setText(record.get('memo'));

        var withdraw = view.down('#withdraw');
        withdraw.setText(record.get('withdraw'));

        var feeIncluding = view.down('#feeIncluding');
        feeIncluding.setText(record.get('feeIncluding'));

        var feeExcluding = view.down('#feeExcluding');
        feeExcluding.setText(record.get('feeExcluding'));

        var imageUrls = record.get('imageUrls');
        if (imageUrls != null && imageUrls != '') {
            var urls = imageUrls.split('|');

            var images = view.down('#images');
            urls.forEach(function (url) {
                var image = Ext.create('YourTour.view.widget.XImage', {url: url});
                images.add(image);
            });

            images.setActiveItem(0);
        }

        var btnBook = view.down('#btnBook'), btnFavorite = view.down('#btnFavorite'), btnCancel = view.down('#btnCancel');
        if (action == 'book') {
            btnBook.show();
        } else {
            btnBook.hide();
        }

        if(record.get('favorited') == '1'){
            btnCancel.show();
            btnFavorite.hide();
        }else{
            btnCancel.hide();
            btnFavorite.show();
        }
    },

    /**
     * 保存服务
     */
    showServiceBookView: function () {
        var me = this, view = me.getExpertServiceFormView(), service = view.getData();

        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.service.RouteServiceBookView'));
        var bookview = me.getRouteServiceBookView();

        var serviceName = bookview.down('#serviceName'), serviceFee = bookview.down('#serviceFee');

        serviceName.setText(service.get('title'));
        serviceFee.setText(service.get('fee'));
    },

    /**
     * 显示行程预订的服务
     * @param route
     * @status
     */
    showRouteService: function (route) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.service.RouteServiceMainView'));
        var me = this, view = me.getRouteServiceMainView(), routeServiceList = me.getRouteServiceList();
        view.bindData(route);

        var options = {
            model: 'YourTour.model.RouteServiceModel',
            url: '/services/route/' + route.get('id'),
            success: function (store) {
                routeServiceList.setStore(store);
            }
        };
        me.getApplication().query(options);
    },

    showRouteServiceInfo: function (dataview, index, item, record, e) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.service.RouteServiceFormView'));
        var me = this, view = me.getRouteServiceFormView();
        view.bindData(record);

        var expertService = record.expertServiceStore.first();

        var headerbar = view.down('#headerbar');
        headerbar.setTitle(expertService.get('title'));

        var fromDate = view.down('#fromDate');
        fromDate.setText(record.get('fromDateStr'));

        var endDate = view.down('#endDate');
        endDate.setText(record.get('endDateStr'));

        var place = view.down('#place');
        place.setText(record.get('place'));

        var fee = view.down('#fee');
        fee.setText(record.get('fee'));

        var memberNum = view.down('#memberNum');
        memberNum.setText(Ext.String.format('<span>成人:{0}</span><span style="margin-left:20px">老人:{1}</span><span style="margin-left:20px">儿童:{2}</span>', record.get('adultNum'), record.get('oldNum'), record.get('childNum')));

        var bookMemo = view.down('#bookMemo');
        bookMemo.setText(record.get('memo'));

        var memo = view.down('#memo');
        memo.setText(expertService.get('memo'));

        var withdraw = view.down('#withdraw');
        withdraw.setText(expertService.get('withdraw'));

        var feeIncluding = view.down('#feeIncluding');
        feeIncluding.setText(expertService.get('feeIncluding'));

        var feeExcluding = view.down('#feeExcluding');
        feeExcluding.setText(expertService.get('feeExcluding'));

        var imageUrls = expertService.get('imageUrls');
        if (imageUrls != null && imageUrls != '') {
            var urls = imageUrls.split('|');

            var images = view.down('#images');
            urls.forEach(function (url) {
                var image = Ext.create('YourTour.view.widget.XImage', {url: url});
                images.add(image);
            });

            images.setActiveItem(0);
        }
    },

    /**
     * 显示目的地服务列表页面
     */
    showPlaceServiceView:function(){
        var me = this, mainview = me.getRouteServiceMainView(), route = mainview.getData(), toPlaces = route.get('toPlaces');
        var placeIds = '', places = toPlaces.split('|'), placeOptions=[];
        Ext.Array.each(places, function(place, index){
            if(index > 0){
                placeIds += ',';
            }
            placeIds += place.split(',')[0];

            var option = {};
            option.text=place.split(',')[1];
            option.value=place.split(',')[0];
            placeOptions.push(option);
        });

        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.service.PlaceServiceMainView'));
        var placemainview = me.getPlaceServiceMainView(), placeSelect = placemainview.down('#placeSelect');
        placeSelect.setOptions(placeOptions);

        var placeServiceList = me.getPlaceServiceList();
        var options = {
            model: 'YourTour.model.ExpertServiceModel',
            url: '/services/place/' + placeIds,
            success: function (store) {
                placeServiceList.setStore(store);
            }
        };
        me.getApplication().query(options);
    },

    showExpertServiceView:function(dataview, index, item, record, e){
        this.showExpertService(record, 'book');
    },

    /**
     * 保存服务预订
     */
    saveServiceBookingInfo:function(){
        var me = this, bookview = me.getRouteServiceBookView();
        var view = me.getExpertServiceFormView(), service = view.getData();
        var routeserviceview = me.getRouteServiceMainView(), routeServiceList = me.getRouteServiceList(), route = routeserviceview.getData();

        var data = {};
        data.adultNum = bookview.down('#adultNum').getValue();
        data.oldNum = bookview.down('#oldNum').getValue();
        data.childNum = bookview.down('#childNum').getValue();
        data.place = bookview.down('#place').getValue();
        data.memo = bookview.down('#memo').getValue();
        data.fee = bookview.down('#fee').getValue();

        var fromDate = bookview.down('#fromDate').getValue(), fromTime = bookview.down('#fromTime').getValue(),
            endDate = bookview.down('#endDate').getValue(), endTime = bookview.down('#endTime').getValue();

        var fullFromDate='', fullEndDate='';
        if(fromDate != null){
            fullFromDate = Ext.Date.format(new Date(fromDate), 'Y/m/d') + ' ' + (fromTime == null?'00:00:00':Ext.Date.format(fromTime, 'H:i:s'));
            data.fromDate = new Date(fullFromDate).getTime();
        }

        if(endDate != null){
            fullEndDate = Ext.Date.format(new Date(endDate), 'Y/m/d') + ' ' + (endTime == null?'00:00:00':Ext.Date.format(endTime, 'H:i:s'));
            data.endDate = new Date(fullEndDate).getTime();
        }

        this.getApplication().callService({
            url: '/services/' + route.get('id') + '/' + service.get('id') + '/save',
            method: "POST",
            params: data,
            success: function (response) {
                model = Ext.create('YourTour.model.RouteServiceModel', response)
                var store = routeServiceList.getStore();
                store.add(model);

                Ext.ComponentManager.get('MainView').pop(routeserviceview);
            }
        });
    },

    /**
     * 取消服务
     */
    cancelRouteService: function () {
        var me = this, view = me.getRouteServiceFormView(), routeServiceList = me.getRouteServiceList();
        var service = view.getData();

        this.getApplication().callService({
            url: '/services/' + service.get('id') + '/delete',
            method: "GET",
            success: function (response) {
                var store = routeServiceList.getStore();
                store.remove(service);

                Ext.ComponentManager.get('MainView').pop();
            }
        });
    }
});
