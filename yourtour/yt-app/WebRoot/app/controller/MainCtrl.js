Ext.define('YourTour.controller.MainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
        refs: {
            welcomeview: '#WelcomeView',

            mainView: '#MainView',
            menuTab: '#MainView #menuTab',

            upgradeView : '#UpgradeView',

            activityView : '#ActivityView',
        },

        control: {
            menuTab: {
                activeitemchange: 'onActiveItemChange'
            },

            '#WelcomeView #enter': {
                tap: 'doEnter'
            },

            '#UpgradeView #btnCancel':{
                tap:'cancelUpgrade'
            },

            '#UpgradeView #btnUpgrade':{
                tap:'doUpgrade'
            },

            '#ActivityView #btnSkip':{
                tap:'skipActivity'
            }
        }
    },

    /**
     * APP应用启动函数，实现检查是否首次登录，如果是，那么显示欢迎页，否则调用远程服务获取数据。
     */
    startup: function () {
        var me = this, index;

        var welcomeVisited = me.getApplication().getLocalStorage().get('welcome.visited');
        if (!welcomeVisited) { //首次安装访问，显示欢迎页
            Ext.Viewport.add(Ext.create('YourTour.view.WelcomeView'));
        } else { //启动后台接口调用
            me.doStartup();
        }
    },

    doEnter: function () {
        this.getApplication().getLocalStorage().save({key: 'welcome.visited', value: '1'});

        this.doStartup();
    },

    /**
     * 调用远程接口获取数据，包括：基础数据，首页数据以及升级数据
     */
    doStartup: function () {
        var me = this,
            devType = me.getApplication().getDeviceType(),
            version = me.getApplication().getVersion(),
            appType = me.getApplication().getAppType();

        var options = {
            model: 'YourTour.model.LaunchModel',
            url: 'launch',
            params:[{name:'devType', value:devType},{name:'appType', value:appType}, {name:'version', value:version}],
            success: function (store) {
                var data = store.first();

                var accessToken = me.getApplication().getLocalStorage().get('access-token');
                if(! accessToken){
                    me.getApplication().getLocalStorage().save({key:'access-token', value:data.get('accessToken')});
                }

                me.getApplication().getSessionStorage().save({key:'session-token', value:data.get('sessionToken')})

                if (data.versionStore) { //检查是否需要升级
                    me.showAppUpgrade(data.versionStore);
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
    showMainPage: function () {
        Ext.Viewport.setActiveItem(Ext.create('YourTour.view.MainView'));

        this.showHomeView();
    },

    /**
     * 显示客户端升级提示页面
     * @param store
     */
    showAppUpgrade: function (store) {
        Ext.Viewport.setActiveItem(Ext.create('YourTour.view.UpgradeView'));

        var version = store.first();
        var me = this, upgradeView = me.getUpgradeView(), memo = upgradeView.down('#memo');

        upgradeView.bindData(version);

        memo.setText(version.get('releaseNotes'));
    },

    cancelUpgrade:function(){
        this.showMainPage();
    },

    /**
     * 客户端升级
     */
    doUpgrade:function(){
        var me = this, upgradeView = me.getUpgradeView(),
            version = upgradeView.getData(),
            memo = upgradeView.down('#memo'),
            progressbar = upgradeView.down('#progressbar'),
            btnCancel = upgradeView.down('#btnCancel'),
            btnUpgrade = upgradeView.down('#btnUpgrade');

        btnCancel.hide();
        btnUpgrade.hide();
        progressbar.show();

        var fileTransfer = new FileTransfer();
        fileTransfer.onprogress = function(progressEvent) {
            if (progressEvent.lengthComputable) {
                progressbar.setPercent(progressEvent.loaded / progressEvent.total);
            }
        };

        var uri = 'http://' + YourTour.util.Context.getRemoteServer() + '/yt-web/' + encodeURI(version.get('newVersionUrl')),
            filePath= '/mnt/sdcard/android-debug.apk';

        fileTransfer.download(
            uri,
            filePath,
            function(entry) {
                App.install(filePath, function(message){
                },function(message){
                    me.getApplication().info('升级失败。');
                });
            },
            function(error) {
                me.getApplication().info('下载异常，请稍后再试。');
                memo.setText("download error code=" + error.source);
            },
            false,
            {
                headers: {
                    "Authorization": "Basic dGVzdHVzZXJuYW1lOnRlc3RwYXNzd29yZA=="
                }
            }
        );
    },

    /**
     * 显示APP主页
     */
    showActivityPage: function (store) {
        Ext.Viewport.setActiveItem(Ext.create('YourTour.view.ActivityView'));

        var me = this,
            activity = store.first(),
            activityView = me.getActivityView(),
            image = activityView.down('#img');

        image.setSrc(YourTour.util.Context.getImageResource(activity.get('imageUrl')));

        me.startTimer(10);
    },

    /**
     * 定时控制活动页面显示
     */
    startTimer:function(count){
        var me = this,
            activityView = me.getActivityView(),
            button = activityView.down('#btnSkip');

        if(count < 10)
            button.setText('跳过>>0' + count + '秒');
        else
            button.setText('跳过>>' + count + '秒');

        button.show();

        me.task = Ext.create('Ext.util.DelayedTask', function () {
            if(count > 0){
                me.startTimer(count - 1);
            }else {
                me.showMainPage();
            }
        });

        me.task.delay(1000); //5秒后执行调用updateClock函数
    },

    /**
     * 跳过活动显示页面
     */
    skipActivity:function(){
        this.showMainPage();
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
        var me = this, task = me.task;
        if(task){
            task.cancel();
        }

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
