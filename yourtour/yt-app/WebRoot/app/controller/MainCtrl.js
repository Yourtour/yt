Ext.define('YourTour.controller.MainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
        refs: {
            welcomeview: '#WelcomeView',

            mainView: '#MainView',
            menuTab: '#MainView #menuTab'
        },

        control: {
            menuTab: {
                activeitemchange: 'onActiveItemChange'
            },

            '#WelcomeView #enter': {
                tap: 'doEnter'
            }
        }
    },

    /**
     * APP应用启动函数，实现检查是否首次登录，如果是，那么显示欢迎页，否则调用远程服务获取数据。
     */
    startup: function () {
        var me = this, index;

        var welcomeVisited = me.getApplication().getCached('welcome.visited');
        if (!welcomeVisited) { //首次安装访问，显示欢迎页
            Ext.Viewport.add(Ext.create('YourTour.view.WelcomeView'));
        } else { //启动后台接口调用
            me.doStartup();
        }
    },

    /**
     * 显示客户端升级提示页面
     * @param store
     */
    showAppUpgrade: function (store) {
        Ext.Viewport.add(Ext.create('YourTour.view.UpgradeView'));
    },

    doEnter: function () {
        this.getApplication().storeCached({key: 'welcome.visited', value: '1'});

        this.doStartup();
    },

    /**
     * 调用远程接口获取数据，包括：基础数据，首页数据以及升级数据
     */
    doStartup: function () {
        var me = this,
            deviceTpe = me.getApplication().getDeviceType(),
            version = me.getApplication().getVersion(),
            appType = me.getApplication().getAppType();

        var options = {
            model: 'YourTour.model.LaunchModel',
            url: Ext.String.format('/{0}/{1}/{2}/launch', deviceTpe, appType, version),
            success: function (store) {
                var data = store.first();
                if (data.deviceStore) { //检查是否需要升级
                    me.showAppUpgrade(data.deviceStore);
                } else if (data.activityStore) { //检查是否有活动数据，如果有显示活动页面
                    me.showActivityPage(data.activityStore);
                } else {
                    me.showMainPage();
                }
            }
        };
        me.getApplication().query(options);
    },

    /**
     * 显示APP主页
     */
    showActivityPage: function (store) {
        Ext.Viewport.setActiveItem(Ext.create('YourTour.view.ActivityView'));
    },

    /**
     * 显示APP主页
     */
    showMainPage: function () {
        Ext.Viewport.setActiveItem(Ext.create('YourTour.view.MainView'));

        this.showHomeView();
    },

    onActiveItemChange: function (tabBar, newTab, oldTab) {
        if (newTab.getItemId() == 'btnHome') {
            this.showHomeView();
        } else if (newTab.getItemId() == 'btnRoute') {
            this.showRouteView();
        } else if (newTab.getItemId() == 'btnPersonal') {
            this.showUserView();
        } else if (newTab.getItemId() == 'btnPlace') {
            this.showPlaceView();
        }
    },

    /**
     * 显示首页
     */
    showHomeView: function () {
        var controller = this.getApplication().getController('HomeMainCtrl');
        controller.showMainPage();
    },

    /**
     * 显示行程
     */
    showRouteView: function () {
        var controller = this.getApplication().getController('route.RouteMainCtrl');
        controller.showMainPage();
    },

    /**
     * 显示目的地
     */
    showPlaceView: function () {
        var controller = this.getApplication().getController('PlaceMainCtrl');
        controller.showMainPage(6, '黄山');
    },

    /**
     * 显示个人中心
     */
    showUserView: function () {
        var controller = this.getApplication().getController('UserMainCtrl');
        controller.showMainPage();
    }
});
