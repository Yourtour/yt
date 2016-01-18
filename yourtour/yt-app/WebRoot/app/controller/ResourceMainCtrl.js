Ext.define('YourTour.controller.ResourceMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
        refs: {
            resourceSceneView: '#ResourceSceneView',

            resourceSelectionView: '#ResourceSelectionView',
            resourceCategory: '#ResourceSelectionView #resourceCategory',

            resourcePlayListView: '#ResourceSelectionView #ResourcePlayListView #ResourceList',
            resourceFoodListView: '#ResourceSelectionView #ResourceFoodListView #ResourceList',

            resourceFormView: '#ResourceFormView',
            btnResourceAddTap: '#ResourceFormView #btnResourceAddTap',

            resourceActivityItemListView: '#ResourceActivityItemListView',
            activityList: '#ResourceActivityItemListView #activityList',

            resourceActivityItemFormView: '#ResourceActivityItemFormView'
        },

        control: {
            resourceCategory: {
                activeitemchange: 'onActiveItemChange'
            },

            resourcePlayListView: {
                itemtap: 'onPlayItemTap'
            },

            btnResourceAddTap:{
                tap:'onResourceAddTap'
            },

            '#ResourceActivityItemListView #btnAddToRoute': {
                tap: 'onActivityItemAddTap'
            },

            '#ResourceActivityItemFormView #btnCancel': {
                tap: 'onActivityItemCancelTap'
            },

            '#ResourceFormView #comment': {
                tap: 'onCommentTap'
            }
        },

        routes: {
            '/resource/:resourceType/:resourceId': 'showResourcePage',
        },

        playStore: null,
        foodStore: null,

        record: null,
    },

    init: function () {
    },

    showSelectionPage: function () {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.resource.ResourceSelectionView'));

        if (this.playStore != null) {
            this.playStore.setData('');
            this.playStore.loaded = false;
        }

        if (this.foodStore != null) {
            this.foodStore.setData('');
            this.foodStore.loaded = false;
        }

        this.getResourceCategory().fireEvent('activeitemchange', this.getResourceCategory(), this.getResourceCategory().getAt(0), this.getResourceCategory().getAt(0));
    },

    /**
     * 显示具体某项资源明细信息
     * @param resourceType
     * @param resourceId
     */
    showResourcePage: function (resourceType, resourceId) {
        if (resourceType == 'SCENE') {
            this.showSceneResource(resourceId);
        }
    },

    /**
     * 显示景点资源明细
     * @param resourceId
     */
    showSceneResource: function (resourceId) {
        var me = this;
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.resource.ResourceFormView'));
        var view = me.getResourceFormView();

        var options = {
            model:'YourTour.model.ResourceModel',
            url:'/scenes/' + resourceId,
            success:function(store){
                var form = Ext.create('YourTour.view.resource.ResourceSceneView');
                view.insert(1, form);
                var resource = store.first();
                view.setData(resource);
                var headerbar = view.down('#headerbar');
                headerbar.setTitle(resource.get('name'));
            }
        };
        me.getApplication().query(options);
    },

    onActiveItemChange: function (tabBar, newTab, oldTab) {
        if (newTab.getItemId() == 'ResourcePlayListView') {
            this.loadPlayResource(newTab);
        } else if (newTab.getItemId() == 'ResourceFoodListView') {
            this.loadFoodResource(newTab);
        }
    },

    /**
     * 查询游玩资源
     */
    loadPlayResource: function (view) {
        var me = this;
        var resourceList = view.down('#ResourceList');

        var store = resourceList.getStore();
        if (store == null) {
            me.playStore = Ext.create('YourTour.store.ResourcePlayStore');
            store = me.playStore;
        }

        if (!store.loaded) {
            var proxy = store.getProxy();
            proxy.setUrl(YourTour.util.Context.getContext('/scenes/query'));
            proxy.extraParams = {placeId: '1111', name: '九寨沟'};
            store.load(function () {
                resourceList.setStore(store);
            });
        }
    },

    /**
     * 获取餐饮资源
     */
    loadFoodResource: function (view) {
        var me = this;
        var resourceList = view.down('#ResourceList');

        var store = resourceList.getStore();
        if (store == null) {
            me.foodStore = Ext.create('YourTour.store.ResourceFoodStore');
            store = me.foodStore;
        }

        if (!store.loaded) {
            var proxy = store.getProxy();
            proxy.setUrl(YourTour.util.Context.getContext('/restaurants/query'));
            proxy.extraParams = {placeId: '1111', name: '九寨沟'};
            store.load(function () {
                resourceList.setStore(store);
            });
        }
    },

    onPlayItemTap: function (dataview, index, item, record, e) {
        this.showSceneResource(record.get('id'));
    },

    onResourceAddTap:function(){
        var view = this.getResourceFormView();
        var resource = view.getData();

        var controller = this.getApplication().getController('route.RouteSchedulePlanCtrl');
        controller.addScheduleActivity(resource, function () {
            Ext.ComponentManager.get('MainView').pop();
        });
    },

    /**
     * 显示某项资源的活动项信息
     * @param title
     * @param resource
     * @param expertId
     */
    showResourceActivities: function (title, resource, expertId) {
        var me = this;
        var resId = resource.get('id');
        var resType = resource.get('type');

        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.resource.ResourceActivityItemListView'));
        var view = this.getResourceActivityItemListView();
        var headerbar = view.down('#headerbar');
        headerbar.setTitle(title);

        var store = this.getResourceStore(resId, resType);
        store.load(function () {
            var resource = store.first();

            var activityList = me.getActivityList();
            var activityStore = resource.activityItemsStore;
            activityStore.each(function (item) {
                var itemPanel = Ext.create('YourTour.view.resource.ResourceActivityItem', {
                    itemId: item.get('Id'),
                    record: item
                });
                activityList.add(itemPanel);
            });
        }, this);
    },

    getResourceStore: function (resId, resType) {
        var store = Ext.create('YourTour.store.AjaxStore', {model: 'YourTour.model.ResourceModel'});
        if (resType == 'SCENE') {
            store.getProxy().setUrl(YourTour.util.Context.getContext('/scenes/' + resId));
        }

        return store;
    },

    /**
     * 显示具体某项资源活动明细
     * @param record
     */
    showResourceActivity: function (record) {
        var me = this;

        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.resource.ResourceActivityItemFormView'));
        var view = this.getResourceActivityItemFormView();
        view.setData(record);

        var headerbar = view.down('#headerbar');
        headerbar.setTitle(record.get('title'));

        var image = view.down('#image');
        image.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "' style='width:100%'>");

        var memo = view.down('#memo');
        memo.setHtml(record.get('memo'));

    },

    onActivityItemAddTap: function () {
        var activityList = this.getActivityList();
        var item = activityList.getActiveItem();

        var controller = this.getApplication().getController('route.RouteSchedulePlanCtrl');
        controller.addScheduleActivityItem(item.getRecord(), function () {
            Ext.ComponentManager.get('MainView').pop();
        });
    },

    onActivityItemCancelTap: function () {
        var view = this.getResourceActivityItemFormView();
        var record = view.getData();

        var controller = this.getApplication().getController('route.RouteSchedulePlanCtrl');
        controller.cancelScheduleActivityItem(record, function () {
            Ext.ComponentManager.get('MainView').pop();
        });
    },

    onCommentTap:function(){
        var me = this;

        var view = me.getResourceFormView();
        var resource = view.getData();

        var controller = this.getApplication().getController('CommonMainCtrl');
        controller.showCommentListView(resource.get('id'), resource.get('type'), function(commentView){
            if(resource.get('type') == 'SCENE'){
                var score = commentView.down('#commentScore');
                score.updateRecord(resource);

                var healthScore = commentView.down('#healthScore');
                healthScore.updateRecord(resource);
                healthScore.show();

                var environmentScore = commentView.down('#environmentScore');
                environmentScore.updateRecord(resource);
                environmentScore.show();

                var serviceScore = commentView.down('#serviceScore');
                serviceScore.updateRecord(resource);
                serviceScore.show();

                var facilityScore = commentView.down('#facilityScore');
                facilityScore.updateRecord(resource);
                facilityScore.show();

                var commentNum = commentView.down('#commentNum');
                commentNum.setText('全部<br/> ' + resource.get('commentNum'));

                var goodNum = commentView.down('#goodNum');
                goodNum.setText('好评<br/> ' + resource.get('goodNum'));

                var mediumNum = commentView.down('#mediumNum');
                mediumNum.setText('中评<br/> ' + resource.get('mediumNum'));

                var badNum = commentView.down('#badNum');
                badNum.setText('差评<br/> ' + resource.get('badNum'));

                var imageNum = commentView.down('#imageNum');
                imageNum.setText('晒图<br/> ' + resource.get('imageNum'));
            }
        });
    }
});
