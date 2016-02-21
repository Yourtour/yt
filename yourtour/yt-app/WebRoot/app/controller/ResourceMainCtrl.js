Ext.define('YourTour.controller.ResourceMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
        refs: {
            resourceSceneView: '#ResourceSceneView',

            resourceSelectionView: '#ResourceSelectionView',
            resourceList: '#ResourceSelectionView #resourceList',

            resourceFormView: '#ResourceFormView',
            btnResourceAddTap: '#ResourceFormView #btnResourceAddTap',

            resourceActivityItemListView: '#ResourceActivityItemListView',
            activityList: '#ResourceActivityItemListView #activityList',

            resourceActivityItemFormView: '#ResourceActivityItemFormView'
        },

        control: {
            resourcePlayListView: {
                itemtap: 'onPlayItemTap'
            },

            resourceList: {
                itemtap:'showResource'
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

            '#ResourceFormView #commentPanel': {
                tap: 'showCommentView'
            }
        }
    },

    showSelectionPage: function () {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.resource.ResourceSelectionView'));

        var me = this, resourceList = me.getResourceList();
        var options = {
            model:'YourTour.model.ResourceModel',
            url:'/scenes/query',
            success:function(store){
                resourceList.setStore(store);
            }
        };
        me.getApplication().query(options);
    },

    /**
     * 显示具体某项资源明细信息
     * @param resourceType
     * @param resourceId
     */
    showResource: function (dataview, index, item, record, e) {
        this.showResourceFormView(record);
    },

    /**
     *
     * @param resource
     */
    showResourceFormView:function(resource){
        var type = resource.get('type'), url, view;
        if (type == 'SCENE') {
            url = '/scenes/' + resource.get('id');
            viewName = 'YourTour.view.resource.ResourceSceneView';
        }

        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.resource.ResourceFormView'));
        var me = this, view = me.getResourceFormView(), pagebody = view.down('#pagebody');

        var headerbar = view.down('#headerbar');
        headerbar.setTitle(resource.get('name'));

        var options = {
            model:'YourTour.model.ResourceModel',
            url:url,
            success:function(store){
                var record = store.first();

                var resview = Ext.create(viewName);
                pagebody.insert(0, resview);

                view.updateData(record);
            }
        };
        me.getApplication().query(options);
    },

    onResourceFilterHandler: function () {
        if (newTab.getItemId() == 'ResourcePlayListView') {
            this.loadPlayResource(newTab);
        } else if (newTab.getItemId() == 'ResourceFoodListView') {
            this.loadFoodResource(newTab);
        }
    },

    onResourceAddTap:function(){
        var view = this.getResourceFormView();
        var resource = view.getData();

        var controller = this.getApplication().getController('route.RouteSchedulePlanCtrl');
        controller.addScheduleActivity(resource);
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

    showCommentView:function(){
        var me = this;

        var view = me.getResourceFormView();
        var resource = view.getData();

        var controller = this.getApplication().getController('CommonMainCtrl');
        controller.showCommentMainView(resource.get('id'), resource.get('type'), function(commentView){
            if(resource.get('type') == 'SCENE'){
                var commentScore = commentView.down('#commentScore');
                commentScore.setText('<span class="tab-space-right-min">推荐:</span>' + resource.get('commentScore'));

                var healthScore = commentView.down('#healthScore');
                healthScore.setText('<span class="tab-space-right-min">卫生:</span>' + resource.get('healthScore'));

                var environmentScore = commentView.down('#environmentScore');
                environmentScore.setText('<span class="tab-space-right-min">环境:</span>' + resource.get('environmentScore'));

                var serviceScore = commentView.down('#serviceScore');
                serviceScore.setText('<span class="tab-space-right-min">服务:</span>' + resource.get('serviceScore'));

                var facilityScore = commentView.down('#facilityScore');
                facilityScore.setText('<span class="tab-space-right-min">卫生:</span>' + resource.get('facilityScore'));

                var commentNum = commentView.down('#commentNum');
                commentNum.setText('<span class="tab-space-right-min">全部:</span>' + resource.get('commentNum'));

                var goodNum = commentView.down('#goodNum');
                goodNum.setText('<span class="tab-space-right-min">好评:</span>' + resource.get('goodNum'));

                var mediumNum = commentView.down('#mediumNum');
                mediumNum.setText('<span class="tab-space-right-min">中评:</span>' + resource.get('mediumNum'));

                var badNum = commentView.down('#badNum');
                badNum.setText('<span class="tab-space-right-min">差评:</span>' + resource.get('badNum'));

                var imageNum = commentView.down('#imageNum');
                imageNum.setText('<span class="tab-space-right-min">晒图:</span>' + resource.get('imageNum'));
            }
        });
    }
});
