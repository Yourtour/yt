Ext.define('YourTour.controller.ServiceMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{
           expertServiceListView:'#ExpertServiceListView',
           expertServiceList:'#ExpertServiceListView #ExpertServiceList',

           expertServiceFormView:'#ExpertServiceFormView',

           routeServiceMainView:'#RouteServiceMainView',
           routeServiceList:'#RouteServiceMainView #RouteServiceList',

           routeServiceFormView:'#RouteServiceFormView'
       },
       
       control:{
            expertServiceList:{
               itemtap:'onListItemTap'
            },

            '#ExpertServiceFormView #btnBook':{
                tap:'onServiceBookTap'
            },

            '#ExpertServiceFormView #btnCancel':{
               tap:'onServiceCancelTap'
            },

           routeServiceList:{
               itemtap:'showRouteServiceInfo'
           }
       }
    },
    
    init: function(){
    },

    /**
     * 获取达人提供的服务
     * @param expertId
     */
    showExpertServices:function(expertId){
        var me = this;
        expertId = 282;
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.expert.ExpertServiceListView'));

        var store = Ext.create('YourTour.store.AjaxStore', {model:'YourTour.model.ExpertServiceModel'});
        var proxy = store.getProxy();
        proxy.setUrl(YourTour.util.Context.getContext('/services/expert/' + expertId));
        store.load(function(){
            me.getExpertServiceList().setStore(store);
        })
    },

    /**
     * 点击服务列表触发事件处理函数
     * @param dataview
     * @param index
     * @param item
     * @param record
     * @param e
     */
    onListItemTap:function(dataview, index, item, record,e){
        this.showExpertService(record, 'book');
    },

    /**
     * 显示达人服务明细
     * @param record
     * @param action
     */
    showExpertService:function(record, action){
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
        if(imageUrls != null && imageUrls != ''){
            var urls = imageUrls.split('|');

            var images = view.down('#images');
            urls.forEach(function(url){
                var image = Ext.create('YourTour.view.widget.XImage',{url : url});
                images.add(image);
            });

            images.setActiveItem(0);
        }

        var btn;
        if(action == 'book'){
            btn = view.down('#btnBook');
            btn.show();
        }else if(action == 'cancel'){
            btn = view.down('#btnAlert');
            btn.show();

            btn = view.down('#btnCancel');
            btn.show();
        }else if(action == 'comment'){
            btn = view.down('#btnComment');
            btn.show();
        }
    },

    /**
     * 保存服务
     */
    onServiceBookTap:function(){
        var view = this.getExpertServiceFormView();
        var service = view.getRecord();

        var controller = this.getApplication().getController('route.RouteSchedulePlanCtrl');
        controller.bookService(service, function(){
            Ext.ComponentManager.get('MainView').pop(2);
        });
    },

    /**
     * 取消服务
     */
    onServiceCancelTap:function(){
        var view = this.getExpertServiceFormView();
        var service = view.getRecord();

        var controller = this.getApplication().getController('route.RouteSchedulePlanCtrl');
        controller.cancelService(service, function(){
            Ext.ComponentManager.get('MainView').pop();
        });
    },

    showRouteService:function(route){
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

    showRouteServiceInfo:function(dataview, index, item, record,e){
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.service.RouteServiceFormView'));
        var me = this, view = me.getRouteServiceFormView();
        view.bindData(record);

        var expertService = record.expertServiceStore.first();

        var headerbar = view.down('#headerbar');
        headerbar.setTitle(expertService.get('title'));

        var memo = view.down('#memo');
        memo.setText(expertService.get('memo'));

        var withdraw = view.down('#withdraw');
        withdraw.setText(expertService.get('withdraw'));

        var feeIncluding = view.down('#feeIncluding');
        feeIncluding.setText(expertService.get('feeIncluding'));

        var feeExcluding = view.down('#feeExcluding');
        feeExcluding.setText(expertService.get('feeExcluding'));

        var imageUrls = expertService.get('imageUrls');
        if(imageUrls != null && imageUrls != ''){
            var urls = imageUrls.split('|');

            var images = view.down('#images');
            urls.forEach(function(url){
                var image = Ext.create('YourTour.view.widget.XImage',{url : url});
                images.add(image);
            });

            images.setActiveItem(0);
        }
    }
});
